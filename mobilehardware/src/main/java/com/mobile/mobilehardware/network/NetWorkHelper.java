package com.mobile.mobilehardware.network;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author gunaonian
 * @date 2018/3/28
 */

public class NetWorkHelper extends NetWorkInfo {

    public static JSONObject mobGetMobNetWork() {
        return getMobNetWork(MobileHardWareHelper.getContext());
    }

}
