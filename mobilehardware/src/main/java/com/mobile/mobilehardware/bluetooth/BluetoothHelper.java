package com.mobile.mobilehardware.bluetooth;



import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author guxiaonian
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
