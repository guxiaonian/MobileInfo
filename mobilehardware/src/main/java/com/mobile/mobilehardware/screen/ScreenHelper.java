package com.mobile.mobilehardware.screen;

import android.view.Window;

import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class ScreenHelper extends ScreenInfo{

    /**
     * 获取屏幕信息
     * @return
     */
    public static JSONObject mobGetMobScreen( Window window) {
        return getMobScreen(MobileHardWareHelper.getContext(),window);
    }



}