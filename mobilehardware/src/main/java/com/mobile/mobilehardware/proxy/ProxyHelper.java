package com.mobile.mobilehardware.proxy;


import android.text.TextUtils;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class ProxyHelper {
    private static final String TAG = ProxyHelper.class.getSimpleName();

    public static JSONObject getProxyData() {
        JSONObject jsonObject = new JSONObject();
        try {
            String proxyAddress = System.getProperty("http.proxyHost");
            String portStr = System.getProperty("http.proxyPort");
            int proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
            jsonObject.put("isProxy",(!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1));
            jsonObject.put("proxyHost",proxyAddress);
            jsonObject.put("proxyPort",proxyPort);
        } catch (Exception e) {
//            e.printStackTrace();

        }
        return jsonObject;
    }

}
