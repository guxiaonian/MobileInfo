package com.mobile.mobilehardware.local;


import android.util.Log;

import org.json.JSONObject;

import java.util.Locale;

/**
 * @author gunaonian
 * @date 2018/3/28
 */

class LocalInfo {

    public static final String TAG = LocalInfo.class.getSimpleName();

    static JSONObject getMobLocal() {
        LocalBean localBean = new LocalBean();
        try {
            localBean.setCountry(Locale.getDefault().getCountry());
            localBean.setLanguage(Locale.getDefault().getLanguage());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return localBean.toJSONObject();
    }
}
