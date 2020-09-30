package com.mobile.mobilehardware.debug;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author guxiaonian
 */
public class DebugHelper extends DebugInfo {

    /**
     * 调试模式判断
     *
     * @return
     */
    public static JSONObject getDebuggingData() {
        return getDebugData(MobileHardWareHelper.getContext());
    }

}


