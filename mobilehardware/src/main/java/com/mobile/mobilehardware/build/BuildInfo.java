package com.mobile.mobilehardware.build;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import org.json.JSONObject;

/**
 * @author gunaonian
 * @date 2018/3/28
 */

class BuildInfo {

    private static final String TAG = BuildInfo.class.getSimpleName();

    @SuppressLint({"HardwareIds", "MissingPermission"})
    static JSONObject getBuildInfo() {
        BuildBean buildBean = new BuildBean();
        try {
            buildBean.setBoard(Build.BOARD);
            buildBean.setBootloader(Build.BOOTLOADER);
            buildBean.setBrand(Build.BRAND);
            buildBean.setDevice(Build.DEVICE);
            buildBean.setDisplay(Build.DISPLAY);
            buildBean.setFingerprint(Build.FINGERPRINT);
            buildBean.setHardware(Build.HARDWARE);
            buildBean.setHost(Build.HOST);
            buildBean.setId(Build.ID);
            buildBean.setManufacturer(Build.MANUFACTURER);
            buildBean.setModel(Build.MODEL);
            buildBean.setProduct(Build.PRODUCT);
            buildBean.setRadio(Build.getRadioVersion());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    buildBean.setSerial(Build.getSerial());
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            } else {
                buildBean.setSerial(Build.SERIAL);
            }
            buildBean.setTags(Build.TAGS);
            buildBean.setTime(Build.TIME);
            buildBean.setType(Build.TYPE);
            buildBean.setUser(Build.USER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                buildBean.setOsVersion(Build.VERSION.BASE_OS);
                buildBean.setPreviewSdkInt(Build.VERSION.PREVIEW_SDK_INT);
                buildBean.setSecurityPatch(Build.VERSION.SECURITY_PATCH);
            }
            buildBean.setReleaseVersion(Build.VERSION.RELEASE);
            buildBean.setIncremental(Build.VERSION.INCREMENTAL);
            buildBean.setSdkInt(Build.VERSION.SDK_INT);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return buildBean.toJSONObject();
    }
}
