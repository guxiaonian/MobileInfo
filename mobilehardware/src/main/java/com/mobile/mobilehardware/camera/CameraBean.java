package com.mobile.mobilehardware.camera;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * @author gunaonian
 */
public class CameraBean extends BaseBean {
    private static final String TAG = CameraBean.class.getSimpleName();

    /**
     * 摄像头信息
     */
    private JSONArray cameraInfo;

    public JSONArray getCameraInfo() {
        return cameraInfo;
    }

    public void setCameraInfo(JSONArray cameraInfo) {
        this.cameraInfo = cameraInfo;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Camera.CAMERA_INFO, cameraInfo);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }

    public static class CameraInfoBean extends BaseBean {

        /**
         * 摄像头的位置
         */
        private String cameraFacing;

        /**
         * 摄像头支持水平
         */
        private String cameraLevel;

        /**
         * 摄像头支持的格式
         */
        private JSONArray outputFormats;

        /**
         * 是否有闪光灯
         */
        private String cameraFlashInfo;

        public String getCameraFlashInfo() {
            return cameraFlashInfo;
        }

        public void setCameraFlashInfo(String cameraFlashInfo) {
            this.cameraFlashInfo = cameraFlashInfo;
        }

        public String getCameraFacing() {
            return cameraFacing;
        }

        public void setCameraFacing(String cameraFacing) {
            this.cameraFacing = cameraFacing;
        }

        public String getCameraLevel() {
            return cameraLevel;
        }

        public void setCameraLevel(String cameraLevel) {
            this.cameraLevel = cameraLevel;
        }

        public JSONArray getOutputFormats() {
            return outputFormats;
        }

        public void setOutputFormats(JSONArray outputFormats) {
            this.outputFormats = outputFormats;
        }

        @Override
        protected JSONObject toJSONObject() {
            try {
                jsonObject.put(BaseData.Camera.CameraInfo.CAMERA_FACING, isEmpty(cameraFacing));
                jsonObject.put(BaseData.Camera.CameraInfo.CAMERA_LEVEL, isEmpty(cameraLevel));
                jsonObject.put(BaseData.Camera.CameraInfo.CAMERA_FLASH_INFO, isEmpty(cameraFlashInfo));
                jsonObject.put(BaseData.Camera.CameraInfo.OUTPUT_FORMATS, outputFormats);

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            return super.toJSONObject();
        }
    }
}
