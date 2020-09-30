package com.mobile.mobilehardware.build;


import org.json.JSONObject;

/**
 * @author guxiaonian
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
