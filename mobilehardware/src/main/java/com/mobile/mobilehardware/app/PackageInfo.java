package com.mobile.mobilehardware.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;

import com.mobile.mobilehardware.utils.PackageSignUtil;

import org.json.JSONObject;


/**
 * @author guxiaonian
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
            packageBean.setLauncherAppName(getLauncherPackageName(context));
            packageBean.setLastUpdateTime(packageInfo.lastUpdateTime);
            packageBean.setFirstInstallTime(packageInfo.firstInstallTime);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return packageBean.toJSONObject();
    }

   private static String getLauncherPackageName(Context context) {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final ResolveInfo res = context.getPackageManager().resolveActivity(intent, 0);
        if (res.activityInfo == null) {
            return "unknown";
        }
        if (res.activityInfo.packageName.equals("android")) {
            return "unknown";
        } else {
            return res.activityInfo.packageName;
        }
    }

}
