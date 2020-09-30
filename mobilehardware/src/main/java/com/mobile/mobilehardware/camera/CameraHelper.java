package com.mobile.mobilehardware.camera;


import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author guxiaonian
 */
public class CameraHelper extends CameraInfo {

    /**
     * 摄像头信息
     *
     * @return
     */
    public static JSONObject getCameraInfo() {
        return cameraInfo(MobileHardWareHelper.getContext());
    }

}
