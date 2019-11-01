package com.mobile.mobilehardware.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Log;

import com.mobile.mobilehardware.utils.PackageSignUtil;

import org.json.JSONObject;

import java.security.MessageDigest;

/**
 * @author 谷闹年
 * @date 2018/1/12
 */
class PackageInfo {

    private static final String TAG = PackageInfo.class.getSimpleName();

    static JSONObject packageInfo(Context context) {
        PackageBean packageBean = new PackageBean();
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo=context.getApplicationInfo();
            packageBean.setAppName(packageManager.getApplicationLabel(applicationInfo).toString());
            android.content.pm.PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            String packageName = packageInfo.packageName;
            packageBean.setPackageName(packageName);
            packageBean.setPackageSign(PackageSignUtil.getSign(context, packageName));
            packageBean.setDescription(applicationInfo.loadDescription(packageManager));
            packageBean.setIcon(applicationInfo.loadIcon(packageManager));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageBean.setAppVersionCode(packageInfo.getLongVersionCode());
            }else {
                packageBean.setAppVersionCode(packageInfo.versionCode);
            }
            packageBean.setAppVersionName(packageInfo.versionName);
            packageBean.setTargetSdkVersion(applicationInfo.targetSdkVersion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                packageBean.setMinSdkVersion(applicationInfo.minSdkVersion);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return packageBean.toJSONObject();
    }

}
