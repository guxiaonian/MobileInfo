package com.mobile.mobilehardware.simcard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;


import com.mobile.mobilehardware.exceptions.MobException;

import org.json.JSONException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 谷闹年
 * @date 2018/1/4
 */
public class MobCardUtils {

    private static final String TAG = MobCardUtils.class.getSimpleName();


    /**
     * mobile info
     *
     * @param context
     * @return
     */
    @SuppressLint("HardwareIds")
    public static void mobGetCardInfo(Context context, SimCardBean simCardBean) {
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        if (telephonyManager == null) {
            return;
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
        simCardBean.setSim1Ready(sim1Ready + "");
        simCardBean.setSim2Ready(sim2Ready + "");
        simCardBean.setIsTwoCard((sim1Ready && sim2Ready) + "");

        String sim1Imei = null;
        String sim2Imei = null;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            try {
                sim1Imei = getSIMOperator(telephonyManager, "getDeviceIdGemini", 0);
                sim2Imei = getSIMOperator(telephonyManager, "getDeviceIdGemini", 1);
            } catch (MobException e) {
                try {
                    sim1Imei = getSIMOperator(telephonyManager, "getDeviceId", 0);
                    sim2Imei = getSIMOperator(telephonyManager, "getDeviceId", 1);
                } catch (MobException e1) {
                    Log.i(TAG, e1.toString());
                }
            }

        }
        if(!TextUtils.isEmpty(sim1Imei)){
            simCardBean.setSim1Imei(MidInfo.isNumeric(sim1Imei)?sim1Imei:null);
        }
        if(!TextUtils.isEmpty(sim2Imei)){
            simCardBean.setSim2Imei(MidInfo.isNumeric(sim2Imei)?sim2Imei:null);
        }
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



}
