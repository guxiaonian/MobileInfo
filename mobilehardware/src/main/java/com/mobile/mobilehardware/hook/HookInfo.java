package com.mobile.mobilehardware.hook;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 谷闹年
 * @date 2018/8/8
 * Xposed  CydiaSubstrate 框架检测工具类
 */
class HookInfo {
    private static final String TAG = HookInfo.class.getSimpleName();

    /**
     * 判断是否有xposed等hook工具
     *
     * @param context
     * @return
     */
    static JSONObject getXposedHook(Context context) {
        HookParamBean hookParamBean = new HookParamBean();
        HookBean hookBean = new HookBean();
        try {
            chargeXposedPackage(context, hookParamBean);
            chargeXposedHookMethod(hookParamBean);
            chargeXposedJars(hookParamBean);
            hookBean.setIsHaveXposed((hookParamBean.isChargeXposedPackage() || hookParamBean.isChargeXposedHookMethod() || hookParamBean.isChargeXposedJars()) + "");
            hookBean.setIsHaveSubstrate((hookParamBean.isChargeSubstratePackage() || hookParamBean.isChargeSubstrateHookMethod() || hookParamBean.isChargeSubstrateJars()) + "");
            hookBean.setIsHaveFrida((checkRunningProcesses(context) || hookParamBean.isChargeFrida()) + "");

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return hookBean.toJSONObject();
    }

    private static boolean checkRunningProcesses(Context context) {
        boolean returnValue = false;
        // Get currently running application processes
        ActivityManager activityManager = (ActivityManager) (context.getSystemService(Context.ACTIVITY_SERVICE));

        List<ActivityManager.RunningServiceInfo> list = activityManager.getRunningServices(300);
        if (list != null) {
            String tempName;
            for (int i = 0; i < list.size(); ++i) {
                tempName = list.get(i).process;
                if (tempName.contains("fridaserver")) {
                    returnValue = true;
                }
            }
        }
        return returnValue;
    }

    /**
     * 检查包名是否存在
     *
     * @param context
     * @return
     */
    private static void chargeXposedPackage(Context context, HookParamBean hookParamBean) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        List<ApplicationInfo> appliacationInfoList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        if (appliacationInfoList == null) {
            return;
        }
        for (ApplicationInfo item : appliacationInfoList) {
            if ("de.robv.android.xposed.installer".equals(item.packageName)) {
                hookParamBean.setChargeXposedPackage(true);
            }
            if ("com.saurik.substrate".equals(item.packageName)) {
                hookParamBean.setChargeSubstratePackage(true);
            }
        }
    }

    /**
     * 检测调用栈中的可疑方法
     */
    private static void chargeXposedHookMethod(HookParamBean hookParamBean) {

        try {

            throw new Exception("Deteck hook");

        } catch (Exception e) {

            int zygoteInitCallCount = 0;
            for (StackTraceElement item : e.getStackTrace()) {
                // 检测"com.android.internal.os.ZygoteInit"是否出现两次，如果出现两次，则表明Substrate框架已经安装
                if ("com.android.internal.os.ZygoteInit".equals(item.getClassName())) {
                    zygoteInitCallCount++;
                    if (zygoteInitCallCount == 2) {
//                        Log.i(TAG, "Substrate is active on the device.");
                        hookParamBean.setChargeSubstrateHookMethod(true);
                    }
                }

                if ("com.saurik.substrate.MS$2".equals(item.getClassName()) && "invoke".equals(item.getMethodName())) {
//                    Log.i(TAG, "A method on the stack trace has been hooked using Substrate.");
                    hookParamBean.setChargeSubstrateHookMethod(true);
                }

                if ("de.robv.android.xposed.XposedBridge".equals(item.getClassName())
                        && "main".equals(item.getMethodName())) {
//                    Log.i(TAG, "Xposed is active on the device.");
                    hookParamBean.setChargeXposedHookMethod(true);
                }
                if ("de.robv.android.xposed.XposedBridge".equals(item.getClassName())
                        && "handleHookedMethod".equals(item.getMethodName())) {
//                    Log.i(TAG, "A method on the stack trace has been hooked using Xposed.");
                    hookParamBean.setChargeXposedHookMethod(true);
                }

            }

        }
    }

    /**
     * 检测内存中可疑的jars
     */
    private static void chargeXposedJars(HookParamBean hookParamBean) {
        Set<String> libraries = new HashSet<String>();
        String mapsFilename = "/proc/" + android.os.Process.myPid() + "/maps";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mapsFilename));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains("frida")) {
                    hookParamBean.setChargeFrida(true);
                }
                if (line.endsWith(".so") || line.endsWith(".jar")) {
                    int n = line.lastIndexOf(" ");
                    libraries.add(line.substring(n + 1));
                }


            }
            for (String library : libraries) {
                if (library.contains("com.saurik.substrate")) {
//                    Log.i(TAG, "Substrate shared object found: " + library);
                    hookParamBean.setChargeSubstrateJars(true);
                }
                if (library.contains("XposedBridge.jar")) {
//                    Log.i(TAG, "Xposed JAR found: " + library);
                    hookParamBean.setChargeXposedJars(true);
                }
            }

            reader.close();
        } catch (Exception e) {
//            Log.i(TAG, e.toString());
        }
    }
}
