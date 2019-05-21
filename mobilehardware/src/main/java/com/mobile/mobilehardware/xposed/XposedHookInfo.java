package com.mobile.mobilehardware.xposed;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author 谷闹年
 * @date 2018/8/8
 */
class XposedHookInfo {
    private static final String TAG = XposedHookInfo.class.getSimpleName();

    /**
     * 检测是否有需要的关键字
     * 如果有 则表示APP被劫持
     * 当发现有Xposed工具时，下面数据显示为：
     * "xposedInfo":{//Xposed的详细信息
     * "xposedApp"："flase",//是否hook了本APP
     * "xposedImei"："flase",//是否hook了IMEI号
     * "xposedSerial"："flase",//是否hook了序列号
     * "xposedSsid"："flase",//是否hook了SSID
     * "xposedMac"："flase",//是否hook了本MAC地址
     * "xposedAddress"："flase",//是否hook了蓝牙地址
     * "xposedAndroidId"："flase",//是否hook了AndroidId
     * "xposedImsi"："flase",//是否hook了IMSI
     * "xposedLatitude"："flase",//是否hook了纬度
     * "xposedLongitude"："flase"//是否hook了经度
     * }
     *
     * @return
     */
    static JSONObject xposedInjet(Context context) {
        XposedHookBean xposedHookBean = new XposedHookBean();
        String packageName = getPackageName(context);
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Object clsXposedHtlpers = classLoader.loadClass("de.robv.android.xposed.XposedHelpers").newInstance();
            checkKeyWordInFiled(clsXposedHtlpers, "fieldCache", xposedHookBean, packageName);
            checkKeyWordInFiled(clsXposedHtlpers, "methodCache", xposedHookBean, packageName);
            checkKeyWordInFiled(clsXposedHtlpers, "constructorCache", xposedHookBean, packageName);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return xposedHookBean.toJSONObject();
    }

    /**
     * 关键字 只是表明hook了这些函数 并不能代表使用
     * getDeviceId 修改IMEI号
     * SERIAL  修改序列号
     * getSSID 修改WIFI名称
     * getMacAddress 修改 WIFI MAC地址
     * BluetoothAdapter#getAddress 修改蓝牙MAC地址
     * Secure#getString 修改androidId
     * getSubscriberId 修改IMSI
     * getLatitude 纬度
     * getLongitude 经度
     *
     * @param cls
     * @param fieldName
     * @return
     */
    private static boolean checkKeyWordInFiled(Object cls, String fieldName, XposedHookBean xposedHookBean, String packageName) {
        Map map;
        try {
            Field field = cls.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            map = (Map) field.get(cls);
            if (!map.isEmpty()) {
                for (Object aKeySet : map.keySet()) {
                    if (aKeySet.toString().contains(packageName)) {
                        xposedHookBean.setXposedApp(true);
                    }
                    if (aKeySet.toString().toLowerCase().contains("getDeviceId".toLowerCase())) {
                        xposedHookBean.setXposedImei(true);
                    }
                    if (aKeySet.toString().toLowerCase().contains("SERIAL".toLowerCase())) {
                        xposedHookBean.setXposedSerial(true);
                    }
                    if (aKeySet.toString().toLowerCase().contains("getSSID".toLowerCase())) {
                        xposedHookBean.setXposedSsid(true);
                    }
                    if (aKeySet.toString().toLowerCase().contains("getMacAddress".toLowerCase())) {
                        xposedHookBean.setXposedMac(true);
                    }
                    if (aKeySet.toString().toLowerCase().contains("BluetoothAdapter#getAddress".toLowerCase())) {
                        xposedHookBean.setXposedAddress(true);
                    }
                    if (aKeySet.toString().toLowerCase().contains("Secure#getString".toLowerCase())) {
                        xposedHookBean.setXposedAndroidId(true);
                    }
                    if (aKeySet.toString().toLowerCase().contains("getSubscriberId".toLowerCase())) {
                        xposedHookBean.setXposedImsi(true);
                    }
                    if (aKeySet.toString().toLowerCase().contains("getLatitude".toLowerCase())) {
                        xposedHookBean.setXposedLatitude(true);
                    }
                    if (aKeySet.toString().toLowerCase().contains("getLongitude".toLowerCase())) {
                        xposedHookBean.setXposedLongitude(true);
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    private static String getPackageName(Context context) {
        PackageInfo packageInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                return packageInfo.packageName;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
