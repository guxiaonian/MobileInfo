package com.mobile.mobilehardware.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;


import com.mobile.mobilehardware.exceptions.MobException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 谷闹年 on 2018/1/4.
 */
public class MobCardUtils {

    private static final String TAG = "MobCardUtils";


    /**
     * mobile info
     *
     * @param context
     * @return
     */
    @SuppressLint("HardwareIds")
    public static JSONObject mobGetCardInfo(Context context) {

        JSONObject jsonObject = new JSONObject();
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        if (telephonyManager == null) {
            return null;
        }
        boolean sim1Ready = telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY;
        boolean sim2Ready = false;
        try {
            sim1Ready = getSIMStateBySlot(telephonyManager, "getSimStateGemini", 0);
            sim2Ready = getSIMStateBySlot(telephonyManager, "getSimStateGemini", 1);
        } catch (MobException e) {
            try {
                sim1Ready = getSIMStateBySlot(telephonyManager, "getSimState", 0);
                sim2Ready = getSIMStateBySlot(telephonyManager, "getSimState", 1);
            } catch (MobException e1) {
                Log.i(TAG, e1.toString());
            }

        }
        String sim1Imei = "$unknown";
        String sim2Imei = "$unknown";
        String sim1Imsi = "$unknown";
        String sim2Imsi = "$unknown";
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            sim1Imei = telephonyManager.getDeviceId();
            sim1Imsi = telephonyManager.getSubscriberId();
            try {
                sim1Imei = getSIMOperator(telephonyManager, "getDeviceIdGemini", 0);
                sim2Imei = getSIMOperator(telephonyManager, "getDeviceIdGemini", 1);
                sim1Imsi = getSIMOperator(telephonyManager, "getSubscriberIdGemini", 0);
                sim2Imsi = getSIMOperator(telephonyManager, "getSubscriberIdGemini", 1);
            } catch (MobException e) {
                try {
                    sim1Imei = getSIMOperator(telephonyManager, "getDeviceId", 0);
                    sim2Imei = getSIMOperator(telephonyManager, "getDeviceId", 1);
                    sim1Imsi = getSIMOperator(telephonyManager, "getSubscriberId", 0);
                    sim2Imsi = getSIMOperator(telephonyManager, "getSubscriberId", 1);
                } catch (MobException e1) {
                    Log.i(TAG, e1.toString());
                }
            }

        }

        try {
            jsonObject.put("sim1Ready", sim1Ready + "");
            jsonObject.put("sim2Ready", sim2Ready + "");
            jsonObject.put("isTwoCard", (sim1Ready && sim2Ready) + "");
            jsonObject.put("isHaveCard", (sim1Ready || sim2Ready) + "");
            jsonObject.put("sim1Imei", sim1Imei);
            jsonObject.put("sim2Imei", sim2Imei);
            jsonObject.put("sim1Imsi", sim1Imsi);
            jsonObject.put("sim1ImsiOperator", getOperators(sim1Imsi));
            jsonObject.put("sim2Imsi", sim2Imsi);
            jsonObject.put("sim2ImsiOperator", getOperators(sim2Imsi));
            int simSub = getDefaultDataSub(context);
            jsonObject.put("simHaveFlowCard", simSub + "");
            if (simSub == 1) {
                jsonObject.put("simHaveFlowCardOperator", getOperators(sim2Imsi));
            } else {
                jsonObject.put("simHaveFlowCardOperator", getOperators(sim1Imsi));
            }
        } catch (JSONException e) {
            Log.i(TAG, e.toString());
        }
        return jsonObject;
    }


    /**
     * 判断卡是否已经准备好
     *
     * @param predictedMethodName
     * @param slotID
     * @return
     */
    private static boolean getSIMStateBySlot(TelephonyManager telephony, String predictedMethodName, int slotID) throws MobException {

        boolean isReady = false;

        try {

            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());

            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimStateGemini = telephonyClass.getMethod(predictedMethodName, parameter);

            Object[] obParameter = new Object[1];
            obParameter[0] = slotID;
            Object ob_phone = getSimStateGemini.invoke(telephony, obParameter);

            if (ob_phone != null) {
                int simState = Integer.parseInt(ob_phone.toString());
                if (simState == TelephonyManager.SIM_STATE_READY) {
                    isReady = true;
                }
            }
        } catch (Exception e) {
            throw new MobException(predictedMethodName);
        }

        return isReady;
    }


    /**
     * 获取哪张卡开启的运营商
     *
     * @param context con
     * @return int
     */
    private static int getDefaultDataSub(Context context) {
        int num = -1;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            SubscriptionManager sm = SubscriptionManager.from(context);
            try {
                Method getSubId = sm.getClass().getDeclaredMethod("getDefaultDataSubscriptionId");
                if (getSubId != null) {
                    try {
                        num = (int) getSubId.invoke(sm);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NoSuchMethodException e) {
                try {
                    @SuppressLint("PrivateApi") Method getSubId = sm.getClass().getDeclaredMethod("getDefaultDataSubId");
                    if (getSubId != null) {
                        try {
                            num = (int) getSubId.invoke(sm);
                        } catch (IllegalAccessException | InvocationTargetException e1) {
                            e1.printStackTrace();
                        }
                    }
                } catch (NoSuchMethodException e1) {
                    /**
                     * 新加一个方案，此方案也是用于拿到获取的运营商，跟getDefaultDataSubscriptionId平级
                     */
                    try {
                        Method slot = sm.getClass().getMethod("getDefaultDataSubscriptionInfo");
                        try {
                            SubscriptionInfo subscriptionInfo = (SubscriptionInfo) slot.invoke(sm);
                            num = subscriptionInfo.getSimSlotIndex();
                        } catch (IllegalAccessException | InvocationTargetException e2) {
                            e2.printStackTrace();
                        }
                    } catch (NoSuchMethodException e3) {
                        e3.printStackTrace();
                    }
                }

            }
        }

        return num;
    }

    /**
     * 获取卡的imei信息
     */
    private static String getSIMOperator(TelephonyManager telephony, String predictedMethodName, int slotID) throws MobException {

        String imei = "$unknown";


        try {

            Class<?> telephonyClass = Class.forName(telephony.getClass().getName());

            Class<?>[] parameter = new Class[1];
            parameter[0] = int.class;
            Method getSimStateGemini = telephonyClass.getMethod(predictedMethodName, parameter);

            Object[] obParameter = new Object[1];
            obParameter[0] = slotID;
            Object phone = getSimStateGemini.invoke(telephony, obParameter);

            if (phone != null) {
                imei = phone.toString();
            }
        } catch (Exception e) {
            throw new MobException(predictedMethodName);
        }

        return imei;
    }

    private static final String CM_MOBILE1 = "46000";
    private static final String CM_MOBILE2 = "46002";
    private static final String CM_MOBILE3 = "46004";
    private static final String CM_MOBILE4 = "46007";
    private static final String CU_MOBILE1 = "46001";
    private static final String CU_MOBILE2 = "46006";
    private static final String CU_MOBILE3 = "46009";
    private static final String CT_MOBILE1 = "46003";
    private static final String CT_MOBILE2 = "46005";
    private static final String CT_MOBILE3 = "46011";

    /**
     * 获取网络运营商，CM是移动，CU是联通，CT是电信
     *
     * @param data str
     * @return str
     */
    private static String getOperators(String data) {
        if (data != null) {
            if (data.startsWith(CM_MOBILE1) || data.startsWith(CM_MOBILE2) || data.startsWith(CM_MOBILE3) || data.startsWith(CM_MOBILE4)) {
                return "CM";
            } else if (data.startsWith(CU_MOBILE1) || data.startsWith(CU_MOBILE2) || data.startsWith(CU_MOBILE3)) {
                return "CU";
            } else if (data.startsWith(CT_MOBILE1) || data.startsWith(CT_MOBILE2) || data.startsWith(CT_MOBILE3)) {
                return "CT";
            } else {
                return "$unknown";
            }
        } else {
            return "$unknown";
        }

    }


}
