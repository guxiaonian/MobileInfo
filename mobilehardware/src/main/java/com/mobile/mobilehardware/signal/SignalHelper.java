package com.mobile.mobilehardware.signal;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/1/3
 */
public class SignalHelper extends SignalInfo {


    /**
     * 信号强度获取
     *
     * @return
     */
    public static JSONObject mobGetNetRssi() {
        return getNetRssi(MobileHardWareHelper.getContext());
    }


}
