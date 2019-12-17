package com.mobile.mobileinfo.oaid;

import android.support.annotation.NonNull;

import org.json.JSONObject;

public interface AppIdsUpdater {
    void OnValidId(@NonNull JSONObject ids);
}