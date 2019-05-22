package com.mobile.mobilehardware.signal;

import android.content.Context;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/1/3
 */
public class SignalHelper extends SignalInfo {


    /**
     * 信号强度获取
     *
     * @param context
     * @return
     */
    public static JSONObject mobGetNetRssi(Context context) {
        return getNetRssi(context);
    }


}
