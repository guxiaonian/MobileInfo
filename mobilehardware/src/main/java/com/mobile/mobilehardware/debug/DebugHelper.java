package com.mobile.mobilehardware.debug;

import android.content.Context;

import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/11/20
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


