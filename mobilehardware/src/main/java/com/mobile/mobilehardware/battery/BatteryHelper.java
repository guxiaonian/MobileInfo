package com.mobile.mobilehardware.battery;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class BatteryHelper extends BatteryInfo {

    /**
     * 获取电池信息
     *
     * @return 电池JSON
     */
    public static JSONObject mobGetBattery() {
        return getBattery(MobileHardWareHelper.getContext());
    }

}
