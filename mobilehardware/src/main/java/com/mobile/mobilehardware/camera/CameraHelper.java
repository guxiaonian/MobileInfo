package com.mobile.mobilehardware.camera;

import android.content.Context;

import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/1/5
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
