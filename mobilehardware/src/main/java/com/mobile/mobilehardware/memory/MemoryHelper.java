package com.mobile.mobilehardware.memory;



import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class MemoryHelper extends MemoryInfo {

    /**
     * info
     *
     * @return
     */
    public static JSONObject getMemoryInfo() {
        return memoryInfo(MobileHardWareHelper.getContext());
    }

}
