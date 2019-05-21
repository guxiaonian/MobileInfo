package com.mobile.mobilehardware.bluetooth;


import android.content.Context;

import org.json.JSONObject;


/**
 * @author gunaonian
 * @date 2018/3/28
 */

public class BluetoothHelper extends BluetoothInfo {

    /**
     * 获取蓝牙信息
     * @param context
     * @return
     */
    public static JSONObject mobGetMobBluetooth(Context context) {
        return getMobBluetooth(context);
    }


}
