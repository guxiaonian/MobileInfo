package com.mobile.mobilehardware.utils;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;


/**
 * Created by gunaonian on 2018/3/12.
 */

public class MobEmulatorUtils {
    /**
     * log TAG
     */
    private static final String TAG = "MobEmulatorUtils";

    /**
     * 判断是否是模拟器
     * 通过静态资源，设备特征参数来判断
     * //        if (isEmulatorAbsoluly()) {
     * //            return true;
     * //        } else if
     * //            //通过是否同时包含两个模拟器特有的app来判断
     * //                (isPkgInstalled(context, "com.example.android.apis") && isPkgInstalled(context, "com.android.development")) {
     * //            return true;
     * //        } else if
     * //            //通过判断mac地址来判断
     * //                ("$unknown".equals(getMacAddress(context))) {
     * //            return true;
     * //        } else if
     * //            //通过设备文件特征来判断（Qemu）
     * //                (checkPipes()) {
     * //            return true;
     * //        } else if
     * //            //通过驱动程序列表来判断（Qemu）
     * //                (checkQEmuDriverFile("/proc/tty/drivers") || checkQEmuDriverFile("/proc/cpuinfo")) {
     * //            return true;
     * //        } else if
     * //            //通过获取蓝牙名称来判断
     * //                (notHasBlueTooth(context)) {
     * //            return true;
     * //        } else if
     * //            //通过判断是否有光传感器来判断
     * //                (notHasLightSensorManager(context)) {
     * //            return true;
     * //        } else if
     * //            //通过判断cpu的信息来判断
     * //                (readCpuInfo()) {
     * //            return true;
     * //        } else {
     * //            return false;
     * //        }
     *
     * @param context 上下文
     * @return true为模拟器
     */

    public static JSONObject mobCheckEmulator(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            Boolean checkBuild = isEmulatorAbsoluly();
            Boolean checkPkg = isPkgInstalled(context, "com.example.android.apis") && isPkgInstalled(context, "com.android.development");
            Boolean checkPipes = checkPipes();
            Boolean checkQEmuDriverFile = checkQEmuDriverFile("/proc/tty/drivers") || checkQEmuDriverFile("/proc/cpuinfo");
            Boolean checkHasBlueTooth = notHasBlueTooth(context);
            Boolean checkHasLightSensorManager = notHasLightSensorManager(context);
            Boolean checkCpuInfo = readCpuInfo();
            Boolean checkEmulator = checkBuild || checkPkg ||  checkPipes || checkQEmuDriverFile || checkHasBlueTooth || checkHasLightSensorManager || checkCpuInfo;
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("checkBuild", checkBuild + "");
            jsonObject1.put("checkPkg", checkPkg + "");
            jsonObject1.put("checkPipes", checkPipes + "");
            jsonObject1.put("checkQEmuDriverFile", checkQEmuDriverFile + "");
            jsonObject1.put("checkHasBlueTooth", checkHasBlueTooth + "");
            jsonObject1.put("checkHasLightSensorManager", checkHasLightSensorManager + "");
            jsonObject1.put("checkCpuInfo", checkCpuInfo + "");
            jsonObject.put("checkEmulator", checkEmulator + "");
            jsonObject.put("emulatorData", jsonObject1);

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return jsonObject;

    }

    /**
     * 通过cpu的信息是否包含amd和intel来判断
     *
     * @return true为模拟器
     */
    private static boolean readCpuInfo() {
        String result = "$unknown";
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
     * 判断是否存在光传感器来判断是否为模拟器
     * 部分真机也不存在温度和压力传感器。其余传感器模拟器也存在。
     *
     * @return true 为模拟器
     */
    private static Boolean notHasLightSensorManager(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = null;
        if (sensorManager != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
        return null == sensor;
    }

    /**
     * 判断蓝牙是否有效来判断是否为模拟器
     *
     * @return true 为模拟器
     */
    private static boolean notHasBlueTooth(Context context) {
        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        if (ba == null) {
            return true;
        } else {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                // 如果有蓝牙不一定是有效的。获取蓝牙名称，若为null 则默认为模拟器
                String name = ba.getName();
                return TextUtils.isEmpty(name);
            }
        }

    }
//
//    /**
//     * /system/build.prop属性文件
//     * ro.product.name ro.build
//     *
//     * @param name 属性值
//     * @return systemProperty 的值
//     */
//    private static String getSystemProperty(String name) {
//
//        try {
//            @SuppressLint("PrivateApi") Class systemClass = Class.forName("android.os.SystemProperties");
//            Method method = systemClass.getMethod("get", new Class[]{String.class});
//            return (String) method.invoke(systemClass, new Object[]{name});
//        } catch (Exception e) {
//            Log.i(TAG,e.toString());
//        }
//        return "NULL";
//    }

    /**
     * qemu特有的驱动列表
     */
    private static final String[] known_qemu_drivers = {
            "goldfish"
    };

    /**
     * 驱动程序的列表
     *
     * @return true为模拟器
     */
    private static boolean checkQEmuDriverFile(String name) {
        File driver_file = new File(name);
        if (driver_file.exists() && driver_file.canRead()) {
            byte[] data = new byte[1024];
            try {
                InputStream inStream = new FileInputStream(driver_file);
                inStream.read(data);
                inStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String driver_data = new String(data);
            for (String known_qemu_driver : known_qemu_drivers) {
                if (driver_data.contains(known_qemu_driver)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 设备通道文件，只兼容了qemu模拟器
     */
    private static final String[] known_pipes = {
            "/dev/socket/qemud",
            "/dev/qemu_pipe"
    };


    /**
     * 检测“/dev/socket/qemud”，“/dev/qemu_pipe”这两个通道设备文件特征
     *
     * @return true为模拟器
     */
    private static boolean checkPipes() {
        for (String pipes : known_pipes) {
            File qemu_socket = new File(pipes);
            if (qemu_socket.exists()) {
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
