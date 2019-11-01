package com.mobile.mobilehardware.audio;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class AudioHelper extends AudioInfo {

    /**
     * 获取电量信息
     *
     * @return 电量JSON
     */
    public static JSONObject mobGetMobAudio() {
        return getAudio(MobileHardWareHelper.getContext());
    }


}
