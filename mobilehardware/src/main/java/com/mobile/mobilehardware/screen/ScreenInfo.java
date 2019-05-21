package com.mobile.mobilehardware.screen;

import android.content.Context;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;

import org.json.JSONObject;

/**
 * @author gunaonian
 * @date 2018/3/28
 */

class ScreenInfo {
    private static final String TAG = ScreenInfo.class.getSimpleName();

    static JSONObject getMobScreen(Context context) {
        ScreenBean screenBean = new ScreenBean();
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            screenBean.setDensityScale(displayMetrics.density + "");
            screenBean.setDensityDpi(displayMetrics.densityDpi + "");
            screenBean.setWidth(displayMetrics.widthPixels + "");
            screenBean.setHeight(displayMetrics.heightPixels + "");
            int screenMode = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
            int screenBrightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            int screenChange = Settings.System.getInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION);
            screenBean.setIsScreenAuto((screenMode == 1) + "");
            screenBean.setScreenBrightness(screenBrightness + "");
            screenBean.setIsScreenAutoChange((screenChange == 1) + "");
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return screenBean.toJSONObject();
    }


}