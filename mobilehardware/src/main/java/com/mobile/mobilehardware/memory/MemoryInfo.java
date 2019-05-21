package com.mobile.mobilehardware.memory;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author 谷闹年
 * @date 2018/1/5
 */
class MemoryInfo {
    private static final String TAG = MemoryInfo.class.getSimpleName();

    /**
     * info
     *
     * @param context
     * @return
     */
    static JSONObject memoryInfo(Context context) {
        MemoryBean memoryBean = new MemoryBean();
        try {
            memoryBean.setRamMemory(getTotalMemory(context));
            memoryBean.setRamAvailMemory(getAvailMemory(context));
            memoryBean.setRomMemoryAvailable(getRomSpace(context));
            memoryBean.setRomMemoryTotal(getRomSpaceTotal(context));
            memoryBean.setSdCardMemoryAvailable(getSdcardSize(context));
            memoryBean.setSdCardMemoryTotal(getSdcardSizeTotal(context));
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return memoryBean.toJSONObject();
    }

    /**
     * total
     *
     * @param context
     * @return
     */
    private static String getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader;

            localFileReader = new FileReader(str1);

            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();

            arrayOfString = str2.split("\\s+");
            //noinspection StatementWithEmptyBody
            for (String num : arrayOfString) {
            }

            initial_memory = Long.valueOf(arrayOfString[1]) * 1024;
            localBufferedReader.close();

        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return Formatter.formatFileSize(context, initial_memory);
    }

    /**
     * 获取android当前可用内存大小
     *
     * @param context
     * @return
     */
    private static String getAvailMemory(Context context) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        if (am != null) {
            am.getMemoryInfo(mi);
        }
        //mi.availMem; 当前系统的可用内存
        return Formatter.formatFileSize(context, mi.availMem);
    }

    /**
     * rom
     *
     * @param context
     * @return
     */
    private static String getRomSpace(Context context) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(context, availableBlocks * blockSize);
    }

    private static String getRomSpaceTotal(Context context) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(context, totalBlocks * blockSize);
    }


    /**
     * sd is null ==rom
     *
     * @param context
     * @return
     */
    private static String getSdcardSize(Context context) {

        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(context, availableBlocks * blockSize);
    }

    private static String getSdcardSizeTotal(Context context) {

        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockCount = stat.getBlockCount();
        long blockSize = stat.getBlockSize();
        return Formatter.formatFileSize(context, blockCount * blockSize);
    }
}
