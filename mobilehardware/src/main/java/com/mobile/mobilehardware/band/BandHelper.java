package com.mobile.mobilehardware.band;


import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class BandHelper extends BandInfo {

    /**
     * bandInfo
     *
     * @return
     */
    public static JSONObject mobGetBandInfo() {
        return getBandInfo();
    }


}
