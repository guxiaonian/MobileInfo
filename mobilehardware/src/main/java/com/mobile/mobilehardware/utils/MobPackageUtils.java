package com.mobile.mobilehardware.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import org.json.JSONObject;

import java.security.MessageDigest;

/**
 * Created by 谷闹年 on 2018/1/12.
 */
public class MobPackageUtils {

    public static final String TAG = "MobPackageUtils";

    public static JSONObject getPackageInfo(Context context) {
        JSONObject jsonObject = new JSONObject();
        getPackageName(context, jsonObject);
        return jsonObject;
    }

    private static void getPackageName(Context context, JSONObject jsonObject) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            jsonObject.put("appName", packageManager.getApplicationLabel(context.getApplicationInfo()).toString());
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            String packageName = packageInfo.packageName;
            jsonObject.put("packageName", packageName);
            jsonObject.put("packageSign", getSign(context, packageName));
            jsonObject.put("appVersionCode", packageInfo.versionCode + "");
            jsonObject.put("appVersionName", packageInfo.versionName);


        } catch (Exception e) {
            Log.i("TAG", e.toString());
        }
    }

    @SuppressLint("PackageManagerGetSignatures")
    private static Signature[] getRawSignature(Context paramContext, String paramString) {
        if ((paramString == null) || (paramString.length() == 0)) {
            return null;
        }
        PackageManager localPackageManager = paramContext.getPackageManager();
        PackageInfo localPackageInfo;
        try {
            localPackageInfo = localPackageManager.getPackageInfo(paramString, PackageManager.GET_SIGNATURES);
            if (localPackageInfo == null) {
                return null;
            }
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            return null;
        }
        return localPackageInfo.signatures;
    }

    private static String getSign(Context context, String packageName) {
        Signature[] arrayOfSignature = getRawSignature(context, packageName);
        if ((arrayOfSignature == null) || (arrayOfSignature.length == 0)) {

            return "$unknown";
        }
        return getMessageDigest(arrayOfSignature[0].toByteArray());
    }

    private static String getMessageDigest(byte[] paramArrayOfByte) {
        char[] arrayOfChar1 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            int i = arrayOfByte.length;
            char[] arrayOfChar2 = new char[i * 2];
            int j = 0;
            int k = 0;
            while (true) {
                if (j >= i) {
                    return new String(arrayOfChar2);
                }
                int m = arrayOfByte[j];
                int n = k + 1;
                arrayOfChar2[k] = arrayOfChar1[(0xF & m >>> 4)];
                k = n + 1;
                arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
                j++;
            }
        } catch (Exception e) {
            Log.i("TAG", e.toString());
        }
        return "$unknown";
    }
}
