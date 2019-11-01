package com.mobile.mobilehardware.bluetooth;



import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author gunaonian
 * @date 2018/3/28
 */

public class BluetoothHelper extends BluetoothInfo {

    /**
     * 获取蓝牙信息
     * @return
     */
    public static JSONObject mobGetMobBluetooth() {
        return getMobBluetooth(MobileHardWareHelper.getContext());
    }


}
