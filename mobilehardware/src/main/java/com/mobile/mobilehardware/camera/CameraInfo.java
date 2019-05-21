package com.mobile.mobilehardware.camera;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/1/5
 */
class CameraInfo {

    private static final String TAG = CameraInfo.class.getSimpleName();

    static JSONObject cameraInfo(Context context) {
        CameraBean cameraBean = new CameraBean();
        try {
            JSONArray jsonArray = new JSONArray();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
                for (String cameraId : manager.getCameraIdList()) {
                    CameraBean.CameraInfoBean cameraInfoBean = new CameraBean.CameraInfoBean();
                    CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                    Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                    cameraInfoBean.setCameraFacing(facing == null ? null : (facing == CameraCharacteristics.LENS_FACING_FRONT ? "front" : (facing == CameraCharacteristics.LENS_FACING_BACK ? "back" : "external")));
                    Integer level = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                    cameraInfoBean.setCameraLevel(level == null ? null : (level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_FULL ? "full" : (level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED ? "limited" : "legacy")));
                    StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                    if (map != null) {
                        int[] ints = map.getOutputFormats();
                        JSONArray jsonArray1 = new JSONArray();
                        for (int i : ints) {
                            jsonArray1.put(getFormat(i));
                        }
                        cameraInfoBean.setOutputFormats(jsonArray1);
                    }
                    // 检查闪光灯是否支持。
                    Boolean available = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    cameraInfoBean.setCameraFlashInfo(available + "");
                    jsonArray.put(cameraInfoBean.toJSONObject());
                }
            }
            cameraBean.setCameraInfo(jsonArray);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return cameraBean.toJSONObject();
    }


    private static String getFormat(int format) {
        switch (format) {
            case ImageFormat.DEPTH16:
                return "depth16";
            case ImageFormat.DEPTH_POINT_CLOUD:
                return "depth_point_cloud";
            case ImageFormat.FLEX_RGBA_8888:
                return "flex_rgba_8888";
            case ImageFormat.FLEX_RGB_888:
                return "flex_rgb_888";
            case ImageFormat.JPEG:
                return "jpeg";
            case ImageFormat.NV16:
                return "nv16";
            case ImageFormat.NV21:
                return "nv21";
            case ImageFormat.PRIVATE:
                return "private";
            case ImageFormat.RAW10:
                return "raw10";
            case ImageFormat.RAW12:
                return "raw12";
            case ImageFormat.RAW_PRIVATE:
                return "raw_private";
            case ImageFormat.RAW_SENSOR:
                return "raw_sensor";
            case ImageFormat.RGB_565:
                return "rgb_565";
            case ImageFormat.YUV_420_888:
                return "yuv_420_888";
            case ImageFormat.YUV_422_888:
                return "yuv_422_888";
            case ImageFormat.YUV_444_888:
                return "yuv_444_888";
            case ImageFormat.YUY2:
                return "yuy2";
            case ImageFormat.YV12:
                return "yv12";
            default:
                return "unknown";
        }
    }

}
