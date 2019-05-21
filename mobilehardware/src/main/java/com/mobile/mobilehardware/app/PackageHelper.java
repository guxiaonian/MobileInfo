package com.mobile.mobilehardware.app;

import android.content.Context;

import org.json.JSONObject;

/**
 * @author 谷闹年
 * @date 2018/1/12
 */
public class PackageHelper extends PackageInfo {


    /**
     * 获取包类信息
     *
     * @param context
     * @return
     */
    public static JSONObject getPackageInfo(Context context) {
        return packageInfo(context);
    }


}
