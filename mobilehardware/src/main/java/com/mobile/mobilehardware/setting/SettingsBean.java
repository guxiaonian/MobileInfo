package com.mobile.mobilehardware.setting;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class SettingsBean extends BaseBean {
    private static final String TAG = SettingsBean.class.getSimpleName();

    /**
     * android id
     */
    private String androidId;
    private String screenOffTimeout;
    private String soundEffectsEnabled;
    private String screenBrightnessMode;
    private String developmentSettingsEnabled;
    private String accelerometerRotation;
    private String lockPatternVisiblePattern;
    private String lockPatternAutolock;
    private String usbMassStorageEnabled;

    public String getUsbMassStorageEnabled() {
        return usbMassStorageEnabled;
    }

    public void setUsbMassStorageEnabled(String usbMassStorageEnabled) {
        this.usbMassStorageEnabled = usbMassStorageEnabled;
    }

    public String getScreenOffTimeout() {
        return screenOffTimeout;
    }

    public void setScreenOffTimeout(String screenOffTimeout) {
        this.screenOffTimeout = screenOffTimeout;
    }

    public String getSoundEffectsEnabled() {
        return soundEffectsEnabled;
    }

    public void setSoundEffectsEnabled(String soundEffectsEnabled) {
        this.soundEffectsEnabled = soundEffectsEnabled;
    }

    public String getScreenBrightnessMode() {
        return screenBrightnessMode;
    }

    public void setScreenBrightnessMode(String screenBrightnessMode) {
        this.screenBrightnessMode = screenBrightnessMode;
    }

    public String getDevelopmentSettingsEnabled() {
        return developmentSettingsEnabled;
    }

    public void setDevelopmentSettingsEnabled(String developmentSettingsEnabled) {
        this.developmentSettingsEnabled = developmentSettingsEnabled;
    }

    public String getAccelerometerRotation() {
        return accelerometerRotation;
    }

    public void setAccelerometerRotation(String accelerometerRotation) {
        this.accelerometerRotation = accelerometerRotation;
    }

    public String getLockPatternVisiblePattern() {
        return lockPatternVisiblePattern;
    }

    public void setLockPatternVisiblePattern(String lockPatternVisiblePattern) {
        this.lockPatternVisiblePattern = lockPatternVisiblePattern;
    }

    public String getLockPatternAutolock() {
        return lockPatternAutolock;
    }

    public void setLockPatternAutolock(String lockPatternAutolock) {
        this.lockPatternAutolock = lockPatternAutolock;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Settings.ANDROID_ID, isEmpty(androidId));
            jsonObject.put(BaseData.Settings.SCREEN_OFF_TIMEOUT, isEmpty(screenOffTimeout));
            jsonObject.put(BaseData.Settings.SOUND_EFFECTS_ENABLED, isEmpty(soundEffectsEnabled));
            jsonObject.put(BaseData.Settings.SCREEN_BRIGHTNESS_MODE, isEmpty(screenBrightnessMode));
            jsonObject.put(BaseData.Settings.DEVELOPMENT_SETTINGS_ENABLED, isEmpty(developmentSettingsEnabled));
            jsonObject.put(BaseData.Settings.ACCELEROMETER_ROTATION, isEmpty(accelerometerRotation));
            jsonObject.put(BaseData.Settings.LOCK_PATTERN_VISIBLE_PATTERN, isEmpty(lockPatternVisiblePattern));
            jsonObject.put(BaseData.Settings.LOCK_PATTERN_AUTOLOCK, isEmpty(lockPatternAutolock));
            jsonObject.put(BaseData.Settings.USB_MASS_STORAGE_ENABLED, isEmpty(usbMassStorageEnabled));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
