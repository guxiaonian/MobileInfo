package com.mobile.mobilehardware.setting;

import android.content.Context;

import org.json.JSONObject;

/**
 * @author gunaonian
 * @date 2018/3/28
 */

public class SettingsHelper extends SettingsInfo {

    /**
     * 获取设置信息
     *
     * @param context
     * @return
     */
    public static JSONObject mobGetMobSettings(Context context) {
        return getMobSettings(context);
    }

}
