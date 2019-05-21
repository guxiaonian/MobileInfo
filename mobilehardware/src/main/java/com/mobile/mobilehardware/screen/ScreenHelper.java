package com.mobile.mobilehardware.screen;

import android.content.Context;
import org.json.JSONObject;

/**
 *
 * @author gunaonian
 * @date 2018/3/28
 */

public class ScreenHelper extends ScreenInfo{

    public static JSONObject mobGetMobScreen(Context context) {
        return getMobScreen(context);
    }



}