package com.mobile.mobilehardware.setting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
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
            settingsBean.setAllowMockLocation(isAllowMockLocation(context));
        } catch (Exception e) {
            Log.e(TAG, e.toString());

        }
        return settingsBean.toJSONObject();
    }


    /**
     * 判断是否打开了允许虚拟位置,如果打开了 则弹窗让他去关闭
     */
    private static boolean isAllowMockLocation(Context context) {
        try {
            boolean isOpen = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION, 0) != 0;
            /**
             * 该判断API是androidM以下的API,由于Android M中已经没有了关闭允许模拟位置的入口,所以这里一旦检测到开启了模拟位置,并且是android M以上,则
             * 默认设置为未有开启模拟位置
             */
            if (isOpen && Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                isOpen = false;
            }
            return isOpen;
        } catch (Exception e) {

        }
        return false;
    }
}
