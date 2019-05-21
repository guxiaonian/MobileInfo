package com.mobile.mobilehardware.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 卡信息获取工具类
 *
 * @author gunaonian
 * @date 2019-03-21
 */
public class GtMobCardUtils {

    /**
     * mobile info
     *
     * @param context
     * @return
     */
    public static JSONObject getImei(Context context) {

        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        String imei = null;
        String meid = null;
        try {
            if (telephonyManager != null) {
                @SuppressLint("MissingPermission") String deviceId = telephonyManager.getDeviceId();
//            LogUtils.e("6.0以下获取开始" + deviceId);
                if (!TextUtils.isEmpty(deviceId)) {
                    if (isNumeric(deviceId)) {
                        imei = deviceId;
                    } else {
                        meid = deviceId;
                    }
                }

                // IMEI 获取
                try {
                    //8.0之上
                    Method getImei = telephonyManager.getClass().getDeclaredMethod("getImei");
                    getImei.setAccessible(true);
                    String imei8 = (String) getImei.invoke(telephonyManager);
                    if (!TextUtils.isEmpty(imei8)) {
                        if (isNumeric(imei8)) {
                            imei = imei8;
                        }
                    }
//                LogUtils.e("8.0以上获取的IMEI" + imei);
                } catch (Exception e) {
                    //6.0-8.0
                    String result = chooseImeiOrMeid(telephonyManager, true);
                    if (!TextUtils.isEmpty(result)) {
                        imei = result;
                    }
                }

                //MEID 获取
                try {
                    //8.0之上
                    Method getMeid = telephonyManager.getClass().getDeclaredMethod("getMeid");
                    getMeid.setAccessible(true);
                    String meid8 = (String) getMeid.invoke(telephonyManager);
                    if (!TextUtils.isEmpty(meid8)) {
                        if (!isNumeric(meid8)) {
                            meid = meid8;
                        }
                    }
//                LogUtils.e("8.0以上获取的MEID" + meid);
                } catch (Exception e) {
                    //6.0-8.0
                    String result = chooseImeiOrMeid(telephonyManager, false);
                    if (!TextUtils.isEmpty(result)) {
                        meid = result;
                    }
                }
            }
        } catch (Exception e) {

        }

        JSONObject jsonObject = new JSONObject();
        try {
            String endImei = TextUtils.isEmpty(imei) ? "$unknown" : imei;
            String endMeid = TextUtils.isEmpty(meid) ? "$unknown" : meid;
//            LogUtils.e("最终获取的IMEI" + endImei);
//            LogUtils.e("最终获取的MEID" + endMeid);
            jsonObject.put("a", endImei);
            jsonObject.put("b", endMeid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

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
//        LogUtils.e("6.0-8.0获取的IMEI0" + imei0);
//        LogUtils.e("6.0-8.0获取的IMEI1" + imei1);
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

    private static boolean isNumeric(String str) {

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
