package com.mobile.mobilehardware.simcard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 卡信息获取工具类
 *
 * @author gunaonian
 * @date 2019-03-21
 */
public class MidInfo {

    /**
     * mobile info
     *
     * @param context
     * @return
     */
    public static String getMeid(Context context) {

        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        String meid = null;
        try {
            if (telephonyManager != null) {
                @SuppressLint("MissingPermission") String deviceId = telephonyManager.getDeviceId();
                if (!TextUtils.isEmpty(deviceId)) {
                    if (!isNumeric(deviceId)) {
                        meid = deviceId;
                    }
                }
                try {
                    Method getMeid = telephonyManager.getClass().getDeclaredMethod("getMeid");
                    getMeid.setAccessible(true);
                    String meid8 = (String) getMeid.invoke(telephonyManager);
                    if (!TextUtils.isEmpty(meid8)) {
                        if (!isNumeric(meid8)) {
                            meid = meid8;
                        }
                    }
                } catch (Exception e) {
                    String result = chooseImeiOrMeid(telephonyManager, false);
                    if (!TextUtils.isEmpty(result)) {
                        meid = result;
                    }
                }
            }
        } catch (Exception e) {

        }
        return meid;

    }


    private static String chooseImeiOrMeid(TelephonyManager telephonyManager, boolean isImei) {
        String imei0 = null;
        String imei1 = null;
        try {
            imei0 = getSIMOperator(telephonyManager, "getDeviceId", 0);
        } catch (Exception e1) {
            try {
                imei0 = getSIMOperator(telephonyManager, "getDeviceIdGemini", 0);
            } catch (Exception e2) {
            }
        }
        try {
            imei1 = getSIMOperator(telephonyManager, "getDeviceId", 1);
        } catch (Exception e1) {
            try {
                imei1 = getSIMOperator(telephonyManager, "getDeviceIdGemini", 1);
            } catch (Exception e2) {
            }
        }
        return verifyImeiOrMeid(imei0, imei1, isImei);
    }

    private static String verifyImeiOrMeid(String imei0, String imei1, boolean isImei) {
        if (!TextUtils.isEmpty(imei0) && !TextUtils.isEmpty(imei1)) {
            boolean imei0IsNum = isNumeric(imei0);
            boolean imei1IsNum = isNumeric(imei1);
            if (isImei) {
                return imei0IsNum ? imei0 : (imei1IsNum ? imei1 : null);
            } else {
                return imei0IsNum ? (imei1IsNum ? null : imei1) : imei0;
            }
        } else {
            return null;
        }
    }

    private static Pattern pattern = Pattern.compile("[0-9]*");

    public static boolean isNumeric(String str) {

        return pattern.matcher(str).matches();
    }

    /**
     * 获取卡的imei信息
     */
    private static String getSIMOperator(TelephonyManager telephony, String predictedMethodName, int slotID) throws Exception {
        String imei = null;
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
            throw new Exception(predictedMethodName);
        }

        return imei;
    }


}
