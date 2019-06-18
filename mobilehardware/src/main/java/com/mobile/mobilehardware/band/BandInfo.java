package com.mobile.mobilehardware.band;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * @author 谷闹年
 * @date 2018/1/5
 */
@SuppressWarnings("ALL")
class BandInfo {
    private static final String TAG = BandInfo.class.getSimpleName();

    /**
     * bandInfo
     *
     * @return
     */
    static JSONObject getBandInfo() {
        BandBean bandBean = new BandBean();
        try {
            bandBean.setBaseBand(getBaseband());
            bandBean.setInnerBand(getInner());
            String linuxBand = getLinuxKernalInfo();
            bandBean.setLinuxBand(TextUtils.isEmpty(linuxBand) ? System.getProperty("os.version") : linuxBand);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return bandBean.toJSONObject();
    }

    /**
     * BASEBAND-VER
     * 基带版本
     * return String
     */

    @SuppressWarnings("unchecked")
    private static String getBaseband() {
        String version = null;
        try {
            @SuppressLint("PrivateApi") Class cl = Class.forName("android.os.SystemProperties");
            Object invoker = cl.newInstance();
            //noinspection unchecked
            Method m = cl.getMethod("get", String.class, String.class);
            Object result = m.invoke(invoker, "gsm.version.baseband", "no message");
            version = (String) result;
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return TextUtils.isEmpty(version) ? Build.getRadioVersion() : version;
    }

    /**
     * INNER-VER
     * 内部版本
     * return String
     */

    private static String getInner() {
        String ver;
        if (null != android.os.Build.VERSION.INCREMENTAL && android.os.Build.DISPLAY.contains(android.os.Build.VERSION.INCREMENTAL)) {
            ver = android.os.Build.DISPLAY;
        } else {
            ver = android.os.Build.VERSION.INCREMENTAL;
        }
        return ver;

    }

    /***
     * 获取Android Linux内核版本信息
     */
    private static String getLinuxKernalInfo() {
        Process process = null;
        String mLinuxKernal;
        try {
            process = Runtime.getRuntime().exec("cat /proc/version");
        } catch (IOException e) {
            Log.i(TAG, e.toString());
        }

        if (process != null) {
            InputStream outs = process.getInputStream();
            InputStreamReader isrout = new InputStreamReader(outs);
            BufferedReader brout = new BufferedReader(isrout, 8 * 1024);

            StringBuilder result = new StringBuilder();
            String line;
            // get the whole standard output string
            try {
                while ((line = brout.readLine()) != null) {
                    result.append(line);
                    // result += "\n";
                }
            } catch (IOException e) {
                Log.i(TAG, e.toString());
            }

            if (!"".equals(result.toString())) {
                String keyword = "version ";
                int index = result.indexOf(keyword);
                line = result.substring(index + keyword.length());
                index = line.indexOf(" ");
                mLinuxKernal = line.substring(0, index);
                return mLinuxKernal;
            }
        }
        return null;
    }

}
