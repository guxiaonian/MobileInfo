package com.mobile.mobilehardware.simcard;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author guxiaonian
 */
public class SimCardHelper extends SimCardInfo {

    /**
     * 获取网络卡信息
     *
     * @return 运营商返回
     */
    public static JSONObject mobileSimInfo() {
        return getMobSimInfo(MobileHardWareHelper.getContext());
    }
}
