package com.mobile.mobilehardware.memory;

import android.content.Context;


import org.json.JSONObject;

/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class MemoryHelper extends MemoryInfo {

    /**
     * info
     *
     * @param context
     * @return
     */
    public static JSONObject getMemoryInfo(Context context) {
        return memoryInfo(context);
    }

}
