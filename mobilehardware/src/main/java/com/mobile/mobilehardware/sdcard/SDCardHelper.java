package com.mobile.mobilehardware.sdcard;


import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class SDCardHelper extends SDCardInfo {

    /**
     * 获取卡信息
     *
     * @return
     */
    public static JSONObject mobGetSdCard() {
        return getSdCard();
    }


}