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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            byte[] signBytes = sign.toByteArray();
            jsonObject.put("signMD5", getMD5(signBytes));
            jsonObject.put("signSHA1", getSHA1(signBytes));
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    private static String getMD5(byte[] value) {
        if (value == null) {
            return null;
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(value);
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getSHA1(byte[] value) {
        MessageDigest md;
        String strDes;
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(value);
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }


    private static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp;
        for (byte bt : bts) {
            tmp = (Integer.toHexString(bt & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
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
            return bytes2Hex(msgDigest.digest());
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
