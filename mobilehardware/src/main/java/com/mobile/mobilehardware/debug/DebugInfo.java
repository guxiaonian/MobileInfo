package com.mobile.mobilehardware.debug;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author 谷闹年
 * @date 2018/11/20
 */
class DebugInfo {
    private static final String TAG = DebugInfo.class.getSimpleName();

    /**
     * 调试模式判断
     *
     * @return
     */
    static JSONObject getDebugData(Context context) {
        DebugBean debugBean = new DebugBean();
        try {
            debugBean.setOpenDebug(isOpenDebug(context));
            debugBean.setDebugVersion(checkIsDebugVersion(context));
            debugBean.setDebugging(checkIsDebuggerConnected());
            debugBean.setReadProcStatus(readProcStatus());
            debugBean.setAllowMockLocation(isAllowMockLocation(context));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return debugBean.toJSONObject();
    }

    /**
     * 判断是否打开了允许虚拟位置,如果打开了 则弹窗让他去关闭
     */
    private static boolean isAllowMockLocation(Context context) {
        try {
            boolean isOpen = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION, 0) != 0;
            /**
             * 该判断API是androidM以下的API,由于Android M中已经没有了关闭允许模拟位置的入口,所以这里一旦检测到开启了模拟位置,并且是android M以上,则
             * 默认设置为未有开启模拟位置
             */
            if (isOpen && Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                isOpen = false;
            }
            return isOpen;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 开启了调试模式
     *
     * @param context
     * @return
     */
    private static boolean isOpenDebug(Context context) {
        try {
            return (Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 0) > 0);
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 判斷是debug版本
     *
     * @param context
     * @return
     */
    private static boolean checkIsDebugVersion(Context context) {
        try {
            return (context.getApplicationInfo().flags
                    & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 是否正在调试
     *
     * @return
     */
    private static boolean checkIsDebuggerConnected() {
        try {
            return android.os.Debug.isDebuggerConnected();
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 读取TracePid
     *
     * @return
     */
    private static boolean readProcStatus() {
        try {
            BufferedReader localBufferedReader =
                    new BufferedReader(new FileReader("/proc/" + android.os.Process.myPid() + "/status"));
            String tracerPid = "";
            for (; ; ) {
                String str = localBufferedReader.readLine();
                if (str.contains("TracerPid")) {
                    tracerPid = str.substring(str.indexOf(":") + 1, str.length()).trim();
                    break;
                }
                if (str == null) {
                    break;
                }
            }
            localBufferedReader.close();
            if ("0".equals(tracerPid)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception fuck) {
            return false;
        }
    }
}


