package com.mobile.mobilehardware.moreopen;


import android.content.Context;


import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/11/20
 */
public class MoreOpenHelper extends MoreOpenInfo {

    public static JSONObject checkVirtual(Context context) {
        return checkVirtualInfo(context);
    }

}
