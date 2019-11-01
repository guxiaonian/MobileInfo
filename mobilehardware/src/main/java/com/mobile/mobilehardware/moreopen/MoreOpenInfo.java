package com.mobile.mobilehardware.moreopen;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.mobile.mobilehardware.utils.CommandUtil;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

/**
 * @author 谷闹年
 * @date 2018/11/20
 */
class MoreOpenInfo {
    private static final String TAG = MoreOpenInfo.class.getSimpleName();

    static JSONObject checkVirtualInfo(Context context) {
        MoreOpenBean moreOpenBean = new MoreOpenBean();
        try {
            moreOpenBean.setCheckByPrivateFilePath(checkByPrivateFilePath(context) );
            moreOpenBean.setCheckByMultiApkPackageName(checkByMultiApkPackageName() );
            moreOpenBean.setCheckByHasSameUid(checkByHasSameUid());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return moreOpenBean.toJSONObject();
    }

    /**
     * 检测私有路径
     *
     * @param context
     * @return
     */
    private static boolean checkByPrivateFilePath(Context context) {
        try {
            String path = context.getFilesDir().getPath();
            for (String virtualPkg : virtualPkgs) {
                if (path.contains(virtualPkg)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    // 多开App包名列表
    private static String[] virtualPkgs = {
            "com.bly.dkplat",//多开分身
            "com.lbe.parallel",//平行空间
            "com.excelliance.dualaid",//双开助手
            "com.lody.virtual",//VirtualXposed，VirtualApp
            "com.qihoo.magic"//360分身大师
    };

    /**
     * maps检测
     *
     * @return
     */
    private static boolean checkByMultiApkPackageName() {
        BufferedReader bufr = null;
        try {
            bufr = new BufferedReader(new FileReader("/proc/self/maps"));
            String line;
            while ((line = bufr.readLine()) != null) {
                for (String pkg : virtualPkgs) {
                    if (line.contains(pkg)) {
                        return true;
                    }
                }
            }
        } catch (Exception ignore) {

        } finally {
            if (bufr != null) {
                try {
                    bufr.close();
                } catch (IOException e) {

                }
            }
        }
        return false;
    }

    /**
     * ps检测
     *
     * @return
     */
    private static boolean checkByHasSameUid() {
        try {
            String filter = getUidStrFormat();
            if (TextUtils.isEmpty(filter)) {
                return false;
            }

            String result = CommandUtil.getSingleInstance().exec("ps");
            if (TextUtils.isEmpty(result)) {
                return false;
            }

            String[] lines = result.split("\n");
            if (lines == null || lines.length <= 0) {
                return false;
            }

            int exitDirCount = 0;

            for (int i = 0; i < lines.length; i++) {
                if (lines[i].contains(filter)) {
                    int pkgStartIndex = lines[i].lastIndexOf(" ");
                    String processName = lines[i].substring(pkgStartIndex <= 0
                            ? 0 : pkgStartIndex + 1, lines[i].length());
                    File dataFile = new File(String.format("/data/data/%s", processName, Locale.CHINA));
                    if (dataFile.exists()) {
                        exitDirCount++;
                    }
                }
            }
            return exitDirCount > 1;
        } catch (Exception e) {
            return false;
        }
    }

    private static String getUidStrFormat() {
        try {
            String filter = CommandUtil.getSingleInstance().exec("cat /proc/self/cgroup");
            if (filter == null || filter.length() == 0) {
                return null;
            }

            int uidStartIndex = filter.lastIndexOf("uid");
            int uidEndIndex = filter.lastIndexOf("/pid");
            if (uidStartIndex < 0) {
                return null;
            }
            if (uidEndIndex <= 0) {
                uidEndIndex = filter.length();
            }

            filter = filter.substring(uidStartIndex + 4, uidEndIndex);
            String strUid = filter.replaceAll("\n", "");
            if (isNumber(strUid)) {
                int uid = Integer.valueOf(strUid);
                filter = String.format("u0_a%d", uid - 10000);
                return filter;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isNumber(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
