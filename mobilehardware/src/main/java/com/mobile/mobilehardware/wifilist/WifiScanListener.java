package com.mobile.mobilehardware.wifilist;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public interface WifiScanListener {
    void onResult(JSONObject jsonObject);
}
