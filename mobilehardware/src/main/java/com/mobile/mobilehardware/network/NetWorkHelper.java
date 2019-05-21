package com.mobile.mobilehardware.network;

import android.content.Context;

import org.json.JSONObject;

/**
 * @author gunaonian
 * @date 2018/3/28
 */

public class NetWorkHelper extends NetWorkInfo {

    public static JSONObject mobGetMobNetWork(Context context) {
        return getMobNetWork(context);
    }

}
