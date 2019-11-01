package com.mobile.mobilehardware.sdcard;

import android.os.Environment;
import android.util.Log;

import org.json.JSONObject;


/**
 * @author gunaonian
 */
class SDCardInfo {

    private static final String TAG = SDCardInfo.class.getSimpleName();

    static JSONObject getSdCard() {
        SDCardBean sdCardBean = new SDCardBean();
        try {
            sdCardBean.setSDCardEnable(isSDCardEnableByEnvironment());
            sdCardBean.setsDCardPath(getSDCardPathByEnvironment());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return sdCardBean.toJSONObject();
    }

    private static String getSDCardPathByEnvironment() {
        if (isSDCardEnableByEnvironment()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }


    private static boolean isSDCardEnableByEnvironment() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

}
