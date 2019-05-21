package com.mobile.mobilehardware.setting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by gunaonian on 2018/3/28.
 */

class SettingsInfo {
    private static final String TAG = SettingsInfo.class.getSimpleName();

    @SuppressLint("HardwareIds")
    static JSONObject getMobSettings(Context context) {
        SettingsBean settingsBean = new SettingsBean();
        try {
            settingsBean.setAndroidId(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        } catch (Exception e) {
            Log.e(TAG, e.toString());

        }
        return settingsBean.toJSONObject();
    }

}
