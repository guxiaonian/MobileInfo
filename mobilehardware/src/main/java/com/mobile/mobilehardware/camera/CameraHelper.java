package com.mobile.mobilehardware.camera;

import android.content.Context;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class CameraHelper extends CameraInfo {

    /**
     * 摄像头信息
     *
     * @param context
     * @return
     */
    public static JSONObject getCameraInfo(Context context) {
        return cameraInfo(context);
    }

}
