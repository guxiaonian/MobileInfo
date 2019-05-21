package com.mobile.mobilehardware.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 谷闹年 on 2018/8/6.
 * 运营商获取类
 */
public class SimOperator {
    private static final String TAG = "SimOperator";

    /**
     * 获取网络运营商，CM是移动，CU是联通，CT是电信（接口运营商）
     *
     * @return 运营商返回
     */
    public static String getSimOperator(Context context, String phoneNumber) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return phoneNumberOperator(phoneNumber);
        } else {
            return simOperator(context);
        }
    }

    public static JSONObject getMobSimInfo(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                jsonObject.put("status", "fail");
            } else {
                jsonObject.put("status", "success");
                SimCardUtils.SimCardInfo simCardInfo = SimCardUtils.instance().getmSimCardInfo(context.getApplicationContext());
                jsonObject.put("sim1Imei", TextUtils.isEmpty(simCardInfo.getSim1Imei()) ? "unknown" : simCardInfo.getSim1Imei());
                jsonObject.put("sim2Imei", TextUtils.isEmpty(simCardInfo.getSim2Imei()) ? "unknown" : simCardInfo.getSim2Imei());
                jsonObject.put("sim1Imsi", TextUtils.isEmpty(simCardInfo.getSim1Imsi()) ? "unknown" : simCardInfo.getSim1Imsi());
                jsonObject.put("sim2Imsi", TextUtils.isEmpty(simCardInfo.getSim2Imsi()) ? "unknown" : simCardInfo.getSim2Imsi());
                jsonObject.put("simSlotIndex", simCardInfo.getSimSub()+"");
            }
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return jsonObject;
    }

    /**
     * 利用手机卡开启流量的运营商来判断
     *
     * @param context
     * @return
     */
    private static String simOperator(Context context) {
        SimCardUtils.SimCardInfo simCardInfo = SimCardUtils.instance().getmSimCardInfo(context.getApplicationContext());
        Log.i(TAG, simCardInfo.toString());
        int simSlotIndex = simCardInfo.getSimSub();
        String operator = simCardInfo.getOperator(simSlotIndex);
        if (TextUtils.isEmpty(operator)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            operator = telephonyManager.getSimOperator();
        }
        return getOperators(operator);
    }

    /**
     * 没有权限判断手机号  移动的没有权限过不了 所以走电信
     *
     * @param phoneNumber
     * @return
     */
    private static String phoneNumberOperator(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            return "there is no permission or phoneNumber is null";
        }
        if (chargeCU(phoneNumber)) {
            return "CU";
        } else if (chargeCM(phoneNumber)) {
            return "CM";
        } else if (chargeCT(phoneNumber)) {
            return "CT";
        } else {
            return "phoneNumber is unknown";
        }
    }

    /**
     * 移动
     *
     * @param phoneNumber
     * @return
     */
    private static boolean chargeCM(String phoneNumber) {
        String regExp = "^((13[4-9])|(15[0-2,7-9])|(18[2-4,7-8])|(172)|(178)|(14[7-8])|(198))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 联通
     *
     * @param phoneNumber
     * @return
     */
    private static boolean chargeCU(String phoneNumber) {
        String regExp = "^((13[0-2])|(15[5-6])|(18[5-6])|(17[1,5-6])|(166)|(14[5-6]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 电信
     *
     * @param phoneNumber
     * @return
     */
    private static boolean chargeCT(String phoneNumber) {
        String regExp = "^((133)|(153)|(18[0-1,9])|(17[3-4,7])|(149)|(199))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
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
        if (!TextUtils.isEmpty(data)) {
            if (data.startsWith(CM_MOBILE1) || data.startsWith(CM_MOBILE2) || data.startsWith(CM_MOBILE3) || data.startsWith(CM_MOBILE4)) {
                return "CM";
            } else if (data.startsWith(CU_MOBILE1) || data.startsWith(CU_MOBILE2) || data.startsWith(CU_MOBILE3)) {
                return "CU";
            } else if (data.startsWith(CT_MOBILE1) || data.startsWith(CT_MOBILE2) || data.startsWith(CT_MOBILE3)) {
                return "CT";
            } else {
                return "unknown";
            }
        } else {
            return "unknown";
        }

    }
}
