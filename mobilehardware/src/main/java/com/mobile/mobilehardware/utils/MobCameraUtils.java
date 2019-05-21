package com.mobile.mobilehardware.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by 谷闹年 on 2018/1/5.
 */
public class MobCameraUtils {

    private static final String TAG = "MobCameraUtils";

    public static JSONObject getCameraInfo(Context context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return putJsonError();
        } else {
            return putJson(context);
        }
    }


    private static JSONObject putJsonError() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cameraError", "CAMERA not authorized");
        } catch (JSONException e) {
            Log.i(TAG, e.toString());
        }
        return jsonObject;

    }


    /**
     * 是否有摄像设备
     *
     * @param ctx
     * @return
     */
    private static boolean hasCameraDevice(Context ctx) {
        return ctx.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }


    private static JSONObject putJson(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (hasCameraDevice(context)) {
                int numberOfCameras = Camera.getNumberOfCameras();
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                for (int i = 0; i < numberOfCameras; i++) {
                    Camera.getCameraInfo(i, cameraInfo);
                    if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                        jsonObject.put("backCamera_" + i, getCameraPixels(i));
                    } else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                        jsonObject.put("frontCamera_" + i, getCameraPixels(i));
                    } else {
                        jsonObject.put("unknownCamera_" + i, getCameraPixels(i));
                    }
                }
            } else {
                jsonObject.put("cameraError", "CAMERA not supported");
            }
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return jsonObject;

    }


    private static JSONObject getCameraPixels(int paramInt) {
        JSONObject jsonObject = new JSONObject();
        Camera localCamera = null;
        try {
            localCamera = Camera.open(paramInt);
            Camera.Parameters localParameters = localCamera.getParameters();
            localCamera.setParameters(localParameters);
            //是否支持变焦
            jsonObject.put("isZoomSupported",localParameters.isZoomSupported()+"");
            List<String> modes = localParameters.getSupportedFocusModes();
            //是否支持自动对焦
            jsonObject.put("isAutoFocusSupported", modes.contains(Camera.Parameters.FOCUS_MODE_AUTO) + "");
            List<Camera.Size> localList = localParameters.getSupportedPictureSizes();
            if (localList != null) {
                int[] heights = new int[localList.size()];
                int[] widths = new int[localList.size()];
                for (int i = 0; i < localList.size(); i++) {
                    Camera.Size size = localList.get(i);
                    int sizehieght = size.height;
                    int sizewidth = size.width;
                    heights[i] = sizehieght;
                    widths[i] = sizewidth;
                }
                int pixels = getMaxNumber(heights) * getMaxNumber(widths);
                jsonObject.put("cameraPixels", String.valueOf(pixels / 10000));
            }
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }finally {
            if(localCamera!=null){
                localCamera.release();
            }
        }
        return jsonObject;

    }

    private static int getMaxNumber(int[] paramArray) {
        int temp = paramArray[0];
        for (int aParamArray : paramArray) {
            if (temp < aParamArray) {
                temp = aParamArray;
            }
        }
        return temp;
    }
}
