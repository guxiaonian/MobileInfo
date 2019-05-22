package com.mobile.mobilehardware.simcard;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;


import org.json.JSONObject;

/**
 * @author 谷闹年
 * @date 2018/8/6
 * 运营商获取类
 */
class SimCardInfo {
    private static final String TAG = SimCardInfo.class.getSimpleName();


    static JSONObject getMobSimInfo(Context context) {
        SimCardBean simCardBean = new SimCardBean();
        try {
            simCardBean.setIsHaveCard(hasSimCard(context) + "");
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return simCardBean.toJSONObject();
            }
            SimCardUtils.SimCardInfo simCardInfo = SimCardUtils.instance().getmSimCardInfo(context.getApplicationContext());
//            simCardBean.setSim1Imei(simCardInfo.getSim1Imei());
//            simCardBean.setSim2Imei(simCardInfo.getSim2Imei());
            simCardBean.setSim1Imsi(simCardInfo.getSim1Imsi());
            simCardBean.setSim2Imsi(simCardInfo.getSim2Imsi());
            int simSlotIndex = simCardInfo.getSimSub();
            simCardBean.setOperator(getOperators(simCardInfo.getOperator(simSlotIndex)));
            simCardBean.setSimSlotIndex(simSlotIndex + "");
            simCardBean.setSim1ImsiOperator(getOperators(simCardInfo.getSim1Imsi()));
            simCardBean.setSim2ImsiOperator(getOperators(simCardInfo.getSim2Imsi()));
            simCardBean.setMeid(MidInfo.getMeid(context));
            MobCardUtils.mobGetCardInfo(context, simCardBean);


        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return simCardBean.toJSONObject();

    }

//    /**
//     * 利用手机卡开启流量的运营商来判断
//     *
//     * @param context
//     * @return
//     */
//    private static String simOperator(Context context) {
//        SimCardUtils.SimCardInfo simCardInfo = SimCardUtils.instance().getmSimCardInfo(context.getApplicationContext());
//        Log.i(TAG, simCardInfo.toString());
//        int simSlotIndex = simCardInfo.getSimSub();
//        String operator = simCardInfo.getOperator(simSlotIndex);
//        if (TextUtils.isEmpty(operator)) {
//            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            operator = telephonyManager.getSimOperator();
//        }
//        return getOperators(operator);
//    }


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
     * 判断是否包含SIM卡
     *
     * @param context 上下文
     * @return 状态 是否包含SIM卡
     */
    private static boolean hasSimCard(Context context) {
        boolean result = true;
        try {
            TelephonyManager telMgr = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            int simState = telMgr.getSimState();
            switch (simState) {
                case TelephonyManager.SIM_STATE_ABSENT:
                    result = false;
                    break;
                case TelephonyManager.SIM_STATE_UNKNOWN:
                    result = false;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {

        }
        return result;
    }
}
