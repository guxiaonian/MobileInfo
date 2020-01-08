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
            settingsBean.setScreenOffTimeout( Settings.System.getString(context.getContentResolver(), "screen_off_timeout"));
            settingsBean.setSoundEffectsEnabled(Settings.System.getString(context.getContentResolver(), "sound_effects_enabled"));
            settingsBean.setScreenBrightnessMode(Settings.System.getString(context.getContentResolver(), "screen_brightness_mode"));
            settingsBean.setDevelopmentSettingsEnabled(Settings.Secure.getString(context.getContentResolver(), "development_settings_enabled"));
            settingsBean.setAccelerometerRotation(Settings.System.getString(context.getContentResolver(), "accelerometer_rotation"));
            settingsBean.setLockPatternVisiblePattern( Settings.System.getString(context.getContentResolver(), "lock_pattern_visible_pattern"));
            settingsBean.setLockPatternAutolock( Settings.System.getString(context.getContentResolver(), "lock_pattern_autolock"));
            settingsBean.setUsbMassStorageEnabled(Settings.System.getString(context.getContentResolver(), "usb_mass_storage_enabled"));
        } catch (Exception e) {
            Log.e(TAG, e.toString());

        }
        return settingsBean.toJSONObject();
    }

}
