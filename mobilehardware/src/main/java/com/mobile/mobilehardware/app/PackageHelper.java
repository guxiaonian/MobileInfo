package com.mobile.mobilehardware.app;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author guxiaonian
 */
public class PackageHelper extends PackageInfo {


    /**
     * 获取包类信息
     *
     * @return 包信息
     */
    public static JSONObject getPackageInfo() {
        return packageInfo(MobileHardWareHelper.getContext());
    }


}
