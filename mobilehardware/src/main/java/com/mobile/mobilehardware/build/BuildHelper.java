package com.mobile.mobilehardware.build;


import org.json.JSONObject;

/**
 * @author gunaonian
 * @date 2018/3/28
 */

public class BuildHelper extends BuildInfo {

    /**
     * build信息
     *
     * @return
     */
    public static JSONObject mobGetBuildInfo() {
        return getBuildInfo();
    }
}
