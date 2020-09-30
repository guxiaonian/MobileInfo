package com.mobile.mobilehardware.emulator;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;


/**
 * @author guxiaonian
 */
class EmulatorInfo {
    /**
     * log TAG
     */
    private static final String TAG = EmulatorInfo.class.getSimpleName();

    static JSONObject checkEmulator(Context context) {
        EmulatorBean emulatorBean = new EmulatorBean();
        try {
            boolean checkBuild = isEmulatorAbsoluly();
            boolean checkPkg = isPkgInstalled(context, "com.example.android.apis") && isPkgInstalled(context, "com.android.development");
            boolean checkPipes = checkPipes();
            boolean checkQEmuDriverFile = checkQEmuDriverFile("/proc/tty/drivers") || checkQEmuDriverFile("/proc/cpuinfo");
            boolean checkCpuInfo = readCpuInfo();
            emulatorBean.setCheckBuild(checkBuild);
            emulatorBean.setCheckPkg(checkPkg);
            emulatorBean.setCheckPipes(checkPipes);
            emulatorBean.setCheckQEmuDriverFile(checkQEmuDriverFile);
            emulatorBean.setCheckCpuInfo(checkCpuInfo);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return emulatorBean.toJSONObject();

    }

    /**
     * 通过cpu的信息是否包含amd和intel来判断
     *
     * @return true为模拟器
     */
    private static boolean readCpuInfo() {
        String result = "unknown";
        try {
            String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
            ProcessBuilder cmd = new ProcessBuilder(args);

            Process process = cmd.start();
            StringBuilder sb = new StringBuilder();
            String readLine;
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            while ((readLine = responseReader.readLine()) != null) {
                sb.append(readLine);
            }
            responseReader.close();
            result = sb.toString().toLowerCase(Locale.getDefault());
        } catch (Exception ex) {
            Log.i(TAG, ex.toString());
        }
        return (result.contains("intel") || result.contains("amd"));
    }


    /**
     * qemu特有的驱动列表
     */
    private static final String[] KNOWN_QEMU_DRIVERS = {
            "goldfish"
    };

    /**
     * 驱动程序的列表
     *
     * @return true为模拟器
     */
    private static boolean checkQEmuDriverFile(String name) {
        File driverFile = new File(name);
        if (driverFile.exists() && driverFile.canRead()) {
            byte[] data = new byte[1024];
            try {
                InputStream inStream = new FileInputStream(driverFile);
                inStream.read(data);
                inStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String driverData = new String(data);
            for (String knownQemuDriver : KNOWN_QEMU_DRIVERS) {
                if (driverData.contains(knownQemuDriver)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 设备通道文件，只兼容了qemu模拟器
     */
    private static final String[] KNOWN_PIPES = {
            "/dev/socket/qemud",
            "/dev/qemu_pipe"
    };


    /**
     * 检测“/dev/socket/qemud”，“/dev/qemu_pipe”这两个通道设备文件特征
     *
     * @return true为模拟器
     */
    private static boolean checkPipes() {
        for (String pipes : KNOWN_PIPES) {
            File qemuSocket = new File(pipes);
            if (qemuSocket.exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否有安装默认程序
     *
     * @param context 上下文
     * @param pkgName 包名
     * @return true为模拟器
     */
    private static boolean isPkgInstalled(Context context, String pkgName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
        }

        return packageInfo != null;
    }

    /**
     * 根据build静态资源来进行判断
     *
     * @return true为模拟器
     */
    private static boolean isEmulatorAbsoluly() {
        if (Build.PRODUCT.contains("sdk") ||
                Build.PRODUCT.contains("sdk_x86") ||
                Build.PRODUCT.contains("sdk_google") ||
                Build.PRODUCT.contains("Andy") ||
                Build.PRODUCT.contains("Droid4X") ||
                Build.PRODUCT.contains("nox") ||
                Build.PRODUCT.contains("vbox86p") ||
                Build.PRODUCT.contains("aries")) {
            return true;
        } else if (Build.MANUFACTURER.contains("Genymotion") ||
                Build.MANUFACTURER.contains("Andy") ||
                Build.MANUFACTURER.contains("nox") ||
                Build.MANUFACTURER.contains("TiantianVM")) {
            return true;
        } else if (Build.BRAND.contains("Andy")) {
            return true;
        } else if (Build.DEVICE.contains("Andy") ||
                Build.DEVICE.contains("Droid4X") ||
                Build.DEVICE.contains("nox") ||
                Build.DEVICE.contains("vbox86p") ||
                Build.DEVICE.contains("aries")) {
            return true;
        } else if (Build.MODEL.contains("Emulator") ||
                Build.MODEL.contains("google_sdk") ||
                Build.MODEL.contains("Droid4X") ||
                Build.MODEL.contains("TiantianVM") ||
                Build.MODEL.contains("Andy") ||
                Build.MODEL.contains("Android SDK built for x86_64") ||
                Build.MODEL.contains("Android SDK built for x86")) {
            return true;
        } else if (Build.HARDWARE.contains("vbox86") ||
                Build.HARDWARE.contains("nox") ||
                Build.HARDWARE.contains("ttVM_x86")) {
            return true;
        } else if (Build.FINGERPRINT.contains("generic/sdk/generic") ||
                Build.FINGERPRINT.contains("generic_x86/sdk_x86/generic_x86") ||
                Build.FINGERPRINT.contains("Andy") ||
                Build.FINGERPRINT.contains("ttVM_Hdragon") ||
                Build.FINGERPRINT.contains("generic/google_sdk/generic") ||
                Build.FINGERPRINT.contains("vbox86p") ||
                Build.FINGERPRINT.contains("generic/vbox86p/vbox86p")) {
            return true;
        } else {
            return false;
        }

    }
}
