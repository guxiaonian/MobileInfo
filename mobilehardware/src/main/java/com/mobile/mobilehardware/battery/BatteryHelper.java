package com.mobile.mobilehardware.battery;

import android.content.Context;

import org.json.JSONObject;

/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class BatteryHelper extends BatteryInfo {

    /**
     * 获取电池信息
     *
     * @param context 上下文
     * @return 电池JSON
     */
    public static JSONObject mobGetBattery(Context context) {
        return getBattery(context);
    }

}
