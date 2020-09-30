package com.mobile.mobilehardware.memory;



import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author guxiaonian
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
