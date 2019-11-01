package com.mobile.mobilehardware.battery;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author 谷闹年
 * @date 2018/1/5
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
