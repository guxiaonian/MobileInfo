package com.mobile.mobilehardware.applist;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

import java.util.List;

/**
 * @author guxiaonian
 */
public class ListAppHelper extends ListAppInfo {

    /**
     * 获取安装软件列表
     *
     * @return 应用列表
     */
    public static List<JSONObject> mobListApp() {
        return getMobListApp(MobileHardWareHelper.getContext());
    }
}
