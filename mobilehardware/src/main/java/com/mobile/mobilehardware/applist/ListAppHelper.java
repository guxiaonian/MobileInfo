package com.mobile.mobilehardware.applist;

import android.content.Context;

import org.json.JSONObject;

import java.util.List;

/**
 * @author gunaonian
 * @date 2018/5/10
 */

public class ListAppHelper extends ListAppInfo {

    /**
     * 获取安装软件列表
     *
     * @param context
     * @return
     */
    public static List<JSONObject> mobListApp(Context context) {
        return getMobListApp(context);
    }
}
