package com.mobile.mobilehardware.cpu;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class CpuHelper extends CpuInfo {

    /**
     * CPU
     * @return
     */
    public static JSONObject mobGetCpuInfo() {
        return getCpuInfo();
    }


}
