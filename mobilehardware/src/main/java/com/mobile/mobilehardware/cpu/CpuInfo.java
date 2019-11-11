package com.mobile.mobilehardware.cpu;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/**
 * @author 谷闹年
 * @date 2018/1/5
 */
class CpuInfo {
    private static final String TAG = CpuInfo.class.getSimpleName();

    static JSONObject getCpuInfo() {
        CpuBean cpuBean = new CpuBean();
        try {
            cpuBean.setCpuName(getCpuName());
            cpuBean.setCpuFreq(getCurCpuFreq() + "KHZ");
            cpuBean.setCpuMaxFreq(getMaxCpuFreq() + "KHZ");
            cpuBean.setCpuMinFreq(getMinCpuFreq() + "KHZ");
            cpuBean.setCpuHardware(Build.HARDWARE);
            cpuBean.setCpuCores(getHeart());
            cpuBean.setCpuTemp(getCpuTemp() + "℃");
            cpuBean.setCpuAbi(putCpuAbi());
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return cpuBean.toJSONObject();
    }

    private static String putCpuAbi() {
        String[] abis;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            abis = Build.SUPPORTED_ABIS;
        } else {
            abis = new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String abi : abis) {
            stringBuilder.append(abi);
            stringBuilder.append(",");
        }

        try {
            return stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return null;

    }

    private static String getCpuTemp() {
        String temp = null;
        try {
            FileReader fr = new FileReader("/sys/class/thermal/thermal_zone9/subsystem/thermal_zone9/temp");
            BufferedReader br = new BufferedReader(fr);
            temp = br.readLine();
            br.close();
        } catch (IOException e) {
            Log.i(TAG, e.toString());
        }
        return TextUtils.isEmpty(temp)?null:temp.length()>=5?(Integer.valueOf(temp)/1000)+"":temp;
    }

    private static int getHeart() {

        int cores;
        try {
            cores = new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
        } catch (SecurityException e) {
            cores = 0;
        }
        return cores;
    }

    private static final FileFilter CPU_FILTER = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return Pattern.matches("cpu[0-9]", pathname.getName());
//            String path = pathname.getName();
//            //regex is slow, so checking char by char.
//            if (path.startsWith("cpu")) {
//                for (int i = 3; i < path.length(); i++) {
//                    if (path.charAt(i) < '0' || path.charAt(i) > '9') {
//                        return false;
//                    }
//                }
//                return true;
//            }
//            return false;
        }
    };

    /**
     * 获取CPU的名字
     *
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    private static String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
//            //noinspection StatementWithEmptyBody
//            for (String anArray : array) {
//            }
            return array[1];
        } catch (IOException e) {
            Log.i(TAG, e.toString());
        }
        return "";
    }


    private static String getCurCpuFreq() {

        String result = "N/A";

        try {

            FileReader fr = new FileReader(

                    "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");

            BufferedReader br = new BufferedReader(fr);

            String text = br.readLine();

            result = text.trim();

        } catch (FileNotFoundException e) {

            Log.i(TAG, e.toString());

        } catch (IOException e) {
            Log.i(TAG, e.toString());

        }

        return result;

    }


    private static String getMaxCpuFreq() {

        String result = "";

        ProcessBuilder cmd;

        try {

            String[] args = {"/system/bin/cat",

                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};

            cmd = new ProcessBuilder(args);

            Process process = cmd.start();

            InputStream in = process.getInputStream();

            byte[] re = new byte[24];

            while (in.read(re) != -1) {

                result = result + new String(re);

            }

            in.close();

        } catch (IOException ex) {

            Log.i(TAG, ex.toString());

            result = "N/A";

        }

        return result.trim();

    }


    private static String getMinCpuFreq() {

        String result = "";

        ProcessBuilder cmd;

        try {

            String[] args = {"/system/bin/cat",

                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"};

            cmd = new ProcessBuilder(args);

            Process process = cmd.start();

            InputStream in = process.getInputStream();

            byte[] re = new byte[24];

            while (in.read(re) != -1) {

                result = result + new String(re);

            }

            in.close();

        } catch (IOException ex) {

            Log.i(TAG, ex.toString());

            result = "N/A";

        }

        return result.trim();

    }
}
