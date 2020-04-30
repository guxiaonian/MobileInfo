package com.mobile.mobilehardware.wifilist;

import org.json.JSONObject;

public interface WifiScanListener {
    void onResult(JSONObject jsonObject);
}
