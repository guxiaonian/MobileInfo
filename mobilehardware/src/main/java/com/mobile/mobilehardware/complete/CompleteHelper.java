package com.mobile.mobilehardware.complete;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class CompleteHelper {
    private static final String TAG = CompleteHelper.class.getSimpleName();

    public static JSONObject getCompleteData() {
        JSONObject jsonObject = new JSONObject();
        try {
            Context context = MobileHardWareHelper.getContext();
            getSign(context, jsonObject);
            jsonObject.put("fileCRC32", getCrc32(context));
            jsonObject.put("apkSHA1", getAppHash(context));

        } catch (Exception e) {
        }
        return jsonObject;
    }

    private static void getSign(Context context, JSONObject jsonObject) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            MessageDigest md1 = MessageDigest.getInstance("MD5");
            md1.update(sign.toByteArray());
            BigInteger biMd5 = new BigInteger(1, md1.digest());
            jsonObject.put("signMD5", biMd5.toString(16));
            MessageDigest md2 = MessageDigest.getInstance("SHA1");
            md2.update(sign.toByteArray());
            BigInteger biSHA1 = new BigInteger(1, md2.digest());
            jsonObject.put("signSHA1", biSHA1.toString(16));
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private static String getCrc32(Context context) {
        String apkPath = getApkPath(context);
        ZipFile zipfile;
        ZipEntry dexentry = null;
        try {
            zipfile = new ZipFile(apkPath);
            dexentry = zipfile.getEntry("classes.dex");
        } catch (Exception e) {
            // e.printStackTrace();
        }
        if (dexentry != null) {
            return dexentry.getCrc() + "";
        }
        return null;
    }

    private static String getApkPath(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            return applicationInfo.publicSourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
        }
        return null;
    }

    private static String getAppHash(Context context) {
        MessageDigest msgDigest;

        String apkPath = getApkPath(context);
        FileInputStream fis = null;
        try {
            msgDigest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = new byte[1024];
            int byteCount;
            fis = new FileInputStream(new File(apkPath));
            while ((byteCount = fis.read(bytes)) > 0) {
                msgDigest.update(bytes, 0, byteCount);
            }
            BigInteger bi = new BigInteger(1, msgDigest.digest());
            return bi.toString(16);
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
