package com.mobile.mobilehardware.signal;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author guxiaonian
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
