package com.mobile.mobilehardware.applist;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gunaonian
 * @date 2018/5/10
 */

class ListAppInfo {

    private static final String TAG = ListAppInfo.class.getSimpleName();

    static List<JSONObject> getMobListApp(Context context) {
        List<JSONObject> appLists = new ArrayList<>();
        try {
            PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
            for (PackageInfo packageInfo : packageInfoList) {
                ListAppBean listAppBean = new ListAppBean();
                listAppBean.setPackageName(packageInfo.packageName);
                listAppBean.setVersionName(packageInfo.versionName);
                listAppBean.setVersionCode(packageInfo.versionCode + "");
                if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) == 0) {
                    listAppBean.setIsSystem("false");
                } else {
                    listAppBean.setIsSystem("true");
                }
                appLists.add(listAppBean.toJSONObject());
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return appLists;
    }
}
