package com.mobile.mobilehardware.applist;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.mobile.mobilehardware.utils.PackageSignUtil;

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
                listAppBean.setAppVersionName(packageInfo.versionName);
                listAppBean.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                listAppBean.setDescription(packageInfo.applicationInfo.loadDescription(packageManager));
                listAppBean.setIcon(packageInfo.applicationInfo.loadIcon(packageManager));
                listAppBean.setTargetSdkVersion(packageInfo.applicationInfo.targetSdkVersion);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    listAppBean.setMinSdkVersion(packageInfo.applicationInfo.minSdkVersion);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    listAppBean.setAppVersionCode(packageInfo.getLongVersionCode());
                } else {
                    listAppBean.setAppVersionCode(packageInfo.versionCode);
                }
                listAppBean.setPackageSign(PackageSignUtil.getSign(context, listAppBean.getPackageName()));
                listAppBean.setSystem(!((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) == 0));
                appLists.add(listAppBean.toJSONObject());
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return appLists;
    }
}
