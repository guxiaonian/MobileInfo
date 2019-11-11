package com.mobile.mobilehardware.simcard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
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
        if (!TextUtils.isEmpty(data)) {
            if (data.startsWith(CM_MOBILE1) || data.startsWith(CM_MOBILE2) || data.startsWith(CM_MOBILE3) || data.startsWith(CM_MOBILE4)) {
                return "CM";
            } else if (data.startsWith(CU_MOBILE1) || data.startsWith(CU_MOBILE2) || data.startsWith(CU_MOBILE3)) {
                return "CU";
            } else if (data.startsWith(CT_MOBILE1) || data.startsWith(CT_MOBILE2) || data.startsWith(CT_MOBILE3)) {
                return "CT";
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    /**
     * mobile info
     *
     * @param context
     * @return
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
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
        simCardBean.setSim1Ready(sim1Ready);
        simCardBean.setSim2Ready(sim2Ready);
        simCardBean.setTwoCard((sim1Ready && sim2Ready));

        String sim1Imei = null;
        String sim2Imei = null;
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
        if (!TextUtils.isEmpty(sim1Imei)) {
            simCardBean.setSim1Imei(MidInfo.isNumeric(sim1Imei) ? sim1Imei : null);
        }
        if (!TextUtils.isEmpty(sim2Imei)) {
            simCardBean.setSim2Imei(MidInfo.isNumeric(sim2Imei) ? sim2Imei : null);
        }

        String sim1Imsi = null;
        String sim2Imsi = null;
        try {
            sim1Imsi = getSIMOperator(telephonyManager, "getSubscriberIdGemini", 0);
            sim2Imsi = getSIMOperator(telephonyManager, "getSubscriberIdGemini", 1);
        } catch (MobException e) {
            try {
                sim1Imsi = getSIMOperator(telephonyManager, "getSubscriberId", 0);
                sim2Imsi = getSIMOperator(telephonyManager, "getSubscriberId", 1);

            } catch (MobException e1) {
                Log.i(TAG, e1.toString());
            }
        }
        simCardBean.setSim1Imsi(sim1Imsi);
        simCardBean.setSim2Imsi(sim2Imsi);

        String sim1Operator = null;
        String sim2Operator = null;
        try {
            sim1Operator = getOperators(getSIMOperator(telephonyManager, "getSimOperatorGemini", 0));
            sim2Operator = getOperators(getSIMOperator(telephonyManager, "getSimOperatorGemini", 1));
        } catch (MobException e) {
            try {
                sim1Operator = getOperators(getSIMOperator(telephonyManager, "getSimOperator", 0));
                sim2Operator = getOperators(getSIMOperator(telephonyManager, "getSimOperator", 1));

            } catch (MobException e1) {
                Log.i(TAG, e1.toString());
            }
        }

        simCardBean.setSimSlotIndex(getDefaultDataSub(context));
        simCardBean.setMeid(MidInfo.getMeid(context));
        simInfoQuery(context, simCardBean);
        if (TextUtils.isEmpty(sim1Operator)) {
            if (TextUtils.isEmpty(simCardBean.getSim1carrierName())) {
                simCardBean.setSim1ImsiOperator(getIccidOperators(simCardBean.getSim1IccId()));
            } else {
                simCardBean.setSim1ImsiOperator(getCarrierOperators(simCardBean.getSim1carrierName()));
            }
        } else {
            simCardBean.setSim1ImsiOperator(sim1Operator);
        }


        if (TextUtils.isEmpty(sim2Operator)) {
            if (TextUtils.isEmpty(simCardBean.getSim2carrierName())) {
                simCardBean.setSim2ImsiOperator(getIccidOperators(simCardBean.getSim2IccId()));
            } else {
                simCardBean.setSim2ImsiOperator(getCarrierOperators(simCardBean.getSim2carrierName()));
            }
        } else {
            simCardBean.setSim2ImsiOperator(sim2Operator);
        }

        simCardBean.setOperator(simCardBean.getSimSlotIndex() == 0 ? simCardBean.getSim1ImsiOperator() : simCardBean.getSim2ImsiOperator());
        if (TextUtils.isEmpty(simCardBean.getOperator())) {
            String operator = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(operator) && Build.VERSION.SDK_INT <= 28 && checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                String thisOperator = null;
                try {
                    thisOperator = telephonyManager.getSubscriberId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(thisOperator) && thisOperator.length() >= 5) {
                    operator = thisOperator.substring(0, 5);

                }
            }
            simCardBean.setOperator(getOperators(operator));
        }
    }

    private static boolean checkPermission(Context context, String permission) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(permission, context.getPackageName());
    }

    private static String getIccidOperators(String data) {
        if (!TextUtils.isEmpty(data)) {
            if (data.startsWith("898600") || data.startsWith("898602") || data.startsWith("898604") || data.startsWith("898607")) {
                return "CM";
            } else if (data.startsWith("898601") || data.startsWith("898606") || data.startsWith("898609")) {
                return "CU";
            } else if (data.startsWith("898603") || data.startsWith("898611")) {
                return "CT";
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    private static String getCarrierOperators(String data) {
        if (!TextUtils.isEmpty(data)) {
            if (data.startsWith("中国移动")) {
                return "CM";
            } else if (data.startsWith("中国联通")) {
                return "CU";
            } else if (data.startsWith("中国电信")) {
                return "CT";
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    private static void simInfoQuery(Context context, SimCardBean simCardBean) {
        Uri uri = Uri.parse("content://telephony/siminfo");
        Cursor cursor = null;
        ContentResolver var4 = context.getContentResolver();

        try {
            cursor = var4.query(uri, new String[]{"_id", "icc_id", "sim_id", "mcc", "mnc", "carrier_name", "number"}, "sim_id>=?", new String[]{"0"}, (String) null);
            if (null != cursor) {
                while (cursor.moveToNext()) {
                    String iccId = cursor.getString(cursor.getColumnIndex("icc_id"));
                    int simId = cursor.getInt(cursor.getColumnIndex("sim_id"));
                    int subId = cursor.getInt(cursor.getColumnIndex("_id"));
                    String mcc = cursor.getString(cursor.getColumnIndex("mcc"));
                    String mnc = cursor.getString(cursor.getColumnIndex("mnc"));
                    String carrierName = cursor.getString(cursor.getColumnIndex("carrier_name"));
                    if (simId == 0) {
                        simCardBean.setSim1IccId(iccId);
                        simCardBean.setSim1SimId(simId);
                        simCardBean.setSim1subId(subId);
                        simCardBean.setSim1mcc(mcc);
                        simCardBean.setSim1mnc(mnc);
                        simCardBean.setSim1carrierName(carrierName);
                    } else if (simId == 1) {
                        simCardBean.setSim2IccId(iccId);
                        simCardBean.setSim2SimId(simId);
                        simCardBean.setSim2subId(subId);
                        simCardBean.setSim2mcc(mcc);
                        simCardBean.setSim2mnc(mnc);
                        simCardBean.setSim2carrierName(carrierName);
                    }
                }
            }
        } catch (Exception var15) {
            var15.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

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
