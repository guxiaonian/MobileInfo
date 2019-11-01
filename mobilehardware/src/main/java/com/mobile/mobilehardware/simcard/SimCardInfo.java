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
            simCardBean.setHaveCard(hasSimCard(context));
//            SimCardUtils.SimCardInfo simCardInfo = SimCardUtils.instance().getmSimCardInfo(context.getApplicationContext());
//            simCardBean.setSim1Imsi(simCardInfo.getSim1Imsi());
//            simCardBean.setSim2Imsi(simCardInfo.getSim2Imsi());
//            simCardBean.setOperator(getOperators(simCardInfo.getOperator(simSlotIndex)));
//            simCardBean.setSim1ImsiOperator(getOperators(simCardInfo.getSim1Imsi()));
//            simCardBean.setSim2ImsiOperator(getOperators(simCardInfo.getSim2Imsi()));

            MobCardUtils.mobGetCardInfo(context, simCardBean);


        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return simCardBean.toJSONObject();

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
