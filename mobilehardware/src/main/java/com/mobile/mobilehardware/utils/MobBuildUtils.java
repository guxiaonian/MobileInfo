package com.mobile.mobilehardware.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gunaonian on 2018/3/28.
 */

public class MobBuildUtils {

    private static final String TAG = "MobBuildUtils";

    public static JSONObject mobGetBuildInfo(Context context) {
        JSONObject jsonObject = new JSONObject();
        getBuildInfo(context, jsonObject);
        return jsonObject;
    }


    @SuppressLint("HardwareIds")
    private static void getBuildInfo(Context context, JSONObject jsonObject) {
        try {
            //主板名称
            jsonObject.put("board", MobDataUtils.chargeValueIsEmpty(Build.BOARD));
            //系统引导程序版本号
            jsonObject.put("bootloader", MobDataUtils.chargeValueIsEmpty(Build.BOOTLOADER));
            //系统定制商
            jsonObject.put("brand", MobDataUtils.chargeValueIsEmpty(Build.BRAND));
            //设备参数
            jsonObject.put("device", MobDataUtils.chargeValueIsEmpty(Build.DEVICE));
            //显示屏参数
            jsonObject.put("display", MobDataUtils.chargeValueIsEmpty(Build.DISPLAY));
            //硬件名
            jsonObject.put("fingerprint", MobDataUtils.chargeValueIsEmpty(Build.FINGERPRINT));
            //内核命令行中的硬件名
            jsonObject.put("hardware", MobDataUtils.chargeValueIsEmpty(Build.HARDWARE));
            //
            jsonObject.put("host", MobDataUtils.chargeValueIsEmpty(Build.HOST));
            //标签
            jsonObject.put("id", MobDataUtils.chargeValueIsEmpty(Build.ID));
            //硬件厂商
            jsonObject.put("manufacturer", MobDataUtils.chargeValueIsEmpty(Build.MANUFACTURER));
            //版本
            jsonObject.put("model", MobDataUtils.chargeValueIsEmpty(Build.MODEL));
            //手机厂商
            jsonObject.put("product", MobDataUtils.chargeValueIsEmpty(Build.PRODUCT));
            //返回无线电固件的版本字符串
            jsonObject.put("radio", MobDataUtils.chargeValueIsEmpty(Build.getRadioVersion()));
            //获取硬件序列号
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//                    jsonObject.put("serial", MobDataUtils.chargeValueIsEmpty(Build.getSerial()));
//                } else {
//                    jsonObject.put("serial", "$unknown");
//                }
//            } else {
//
//            }
            jsonObject.put("serial", MobDataUtils.chargeValueIsEmpty(android.os.Build.SERIAL));
            //描述Build的标签
            jsonObject.put("tags", MobDataUtils.chargeValueIsEmpty(Build.TAGS));
            //time
            jsonObject.put("time", Build.TIME + "");
            //type
            jsonObject.put("type", MobDataUtils.chargeValueIsEmpty(Build.TYPE));
            //user
            jsonObject.put("user", MobDataUtils.chargeValueIsEmpty(Build.USER));
            //os版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                jsonObject.put("osVersion", MobDataUtils.chargeValueIsEmpty(Build.VERSION.BASE_OS));
            } else {
                jsonObject.put("osVersion", "$unknown");
            }
            //版本
            jsonObject.put("releaseVersion", MobDataUtils.chargeValueIsEmpty(Build.VERSION.RELEASE));
            //当前开发代码名称
            jsonObject.put("codeName", MobDataUtils.chargeValueIsEmpty(Build.VERSION.CODENAME));
            //基础源代码控件用于表示此构建的内部值
            jsonObject.put("incremental", MobDataUtils.chargeValueIsEmpty(Build.VERSION.INCREMENTAL));

            //SDK的预览版本
            jsonObject.put("sdkInt", Build.VERSION.SDK_INT + "");
            //SDK的预览版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                jsonObject.put("previewSdkInt", Build.VERSION.PREVIEW_SDK_INT + "");
            } else {
                jsonObject.put("previewSdkInt", "$unknown");
            }
            //用户可见的安全补丁程序级别
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                jsonObject.put("securityPatch", MobDataUtils.chargeValueIsEmpty(Build.VERSION.SECURITY_PATCH));
            } else {
                jsonObject.put("securityPatch", "$unknown");
            }

        } catch (JSONException e) {
            Log.i(TAG, e.toString());
        }
    }
}
