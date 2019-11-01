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
         * 设备支持的像差校正模式列表
         */
        private JSONArray aberrationModes;

        /**
         * 本相机设备支持的自动曝光防条纹模式列表
         */
        private JSONArray antiBandingModes;

        /**
         * 是否有闪光灯
         */
        private boolean cameraFlashInfo;

        /**
         * 本相机设备支持的自动曝光模式列表
         */
        private JSONArray aeAvailableModes;

        /**
         * 相机设备支持的帧频范围列表
         */
        private JSONArray fpsRanges;

        /**
         *  此相机设备支持的最大和最小曝光补偿值
         */
        private String compensationRange;

        /**
         * 可以更改曝光补偿的最小步长
         */
        private double compensationStep;

        /**
         * 是否锁定自动曝光
         */
        private boolean lockAvailable;

        /**
         * 相机设备支持的自动对焦（AF）模式列表
         */
        private JSONArray afAvailableModes;

        /**
         * 本相机设备支持的色彩效果列表
         */
        private JSONArray availableEffects;

        /**
         * 本相机设备支持的控制模式列表
         */
        private JSONArray availableModes;

        /**
         * 本相机设备支持的场景模式列表
         */
        private JSONArray availableSceneModes;

        /**
         * 本相机设备支持的视频稳定模式列表
         */
        private JSONArray videoStabilizationModes;

        /**
         * 本相机设备支持的自动白平衡模式列表
         */
        private JSONArray awbAvailableModes;

        /**
         * 是否支持自动白平衡
         */
        private boolean awbLockAvailable;

        private String sensorInfoSensitivityRange;

        private int maxRegionsAe;
        private int maxRegionsAf;
        private int maxRegionsAwb;
        private String rawSensitivityBoostRange;
        private boolean depthIsExclusive;
        private JSONArray correctionAvailableModes;
        private JSONArray availableEdgeModes;
        private JSONArray availableHotPixelModes;
        private JSONArray jpegAvailableThumbnailSizes;
        private JSONArray lensDistortion;
        private JSONArray lensInfoAvailableApertures;
        private JSONArray lensInfoAvailableFilterDensities;
        private JSONArray lensInfoAvailableFocalLengths;
        private JSONArray availableOpticalStabilization;
        private JSONArray lensIntrinsicCalibration;
        private JSONArray lensPoseRotation;
        private JSONArray lensPoseTranslation;
        private JSONArray requestAvailableCapabilities;
        private String supportedHardwareLevel;
        private String infoVersion;
        private String focusDistanceCalibration;
        private String lensPoseReference;
        private String cameraSensorSyncType;
        private float hyperFocalDistance;
        private float minimumFocusDistance;
        private JSONArray availableNoiseReductionModes;
        private JSONArray sensorAvailableTestPatternModes;
        private int maxCaptureStall;
        private int requestMaxNumInputStreams;
        private int requestMaxNumOutputProc;
        private int requestMaxNumOutputProcStalling;
        private int requestMaxNumOutputRaw;
        private int requestPartialResultCount;
        private int statisticsInfoMaxFaceCount;
        private byte requestPipelineMaxDepth;
        private float scalerAvailableMaxDigitalZoom;
        private String scalerCroppingType;
        private String sensorInfoColorFilterArrangement;
        private String sensorInfoExposureTimeRange;
        private String syncMaxLatency;
        private boolean sensorInfoLensShadingApplied;
        private long sensorInfoaxFrameDuration;
        private String sensorInfoTimestampSource;
        private String sensorReferenceIlluminant1;
        private JSONArray shadingAvailableModes;
        private JSONArray availableFaceDetectModes;
        private JSONArray availableLensShadingMapModes;
        private JSONArray availableOisDataModes;
        private JSONArray availableToneMapModes;
        private int sensorInfoWhiteLevel;
        private int sensorMaxAnalogSensitivity;
        private int sensorOrientation;
        private int tonemapMaxCurvePoints;

        public int getTonemapMaxCurvePoints() {
            return tonemapMaxCurvePoints;
        }

        public void setTonemapMaxCurvePoints(int tonemapMaxCurvePoints) {
            this.tonemapMaxCurvePoints = tonemapMaxCurvePoints;
        }

        public JSONArray getAvailableToneMapModes() {
            return availableToneMapModes;
        }

        public void setAvailableToneMapModes(JSONArray availableToneMapModes) {
            this.availableToneMapModes = availableToneMapModes;
        }

        public int getStatisticsInfoMaxFaceCount() {
            return statisticsInfoMaxFaceCount;
        }

        public void setStatisticsInfoMaxFaceCount(int statisticsInfoMaxFaceCount) {
            this.statisticsInfoMaxFaceCount = statisticsInfoMaxFaceCount;
        }

        public JSONArray getAvailableOisDataModes() {
            return availableOisDataModes;
        }

        public void setAvailableOisDataModes(JSONArray availableOisDataModes) {
            this.availableOisDataModes = availableOisDataModes;
        }

        public JSONArray getAvailableLensShadingMapModes() {
            return availableLensShadingMapModes;
        }

        public void setAvailableLensShadingMapModes(JSONArray availableLensShadingMapModes) {
            this.availableLensShadingMapModes = availableLensShadingMapModes;
        }

        public JSONArray getAvailableFaceDetectModes() {
            return availableFaceDetectModes;
        }

        public void setAvailableFaceDetectModes(JSONArray availableFaceDetectModes) {
            this.availableFaceDetectModes = availableFaceDetectModes;
        }

        public JSONArray getShadingAvailableModes() {
            return shadingAvailableModes;
        }

        public void setShadingAvailableModes(JSONArray shadingAvailableModes) {
            this.shadingAvailableModes = shadingAvailableModes;
        }

        public int getSensorInfoWhiteLevel() {
            return sensorInfoWhiteLevel;
        }

        public int getSensorOrientation() {
            return sensorOrientation;
        }

        public void setSensorOrientation(int sensorOrientation) {
            this.sensorOrientation = sensorOrientation;
        }

        public void setSensorInfoWhiteLevel(int sensorInfoWhiteLevel) {
            this.sensorInfoWhiteLevel = sensorInfoWhiteLevel;
        }

        public String getSensorInfoTimestampSource() {
            return sensorInfoTimestampSource;
        }

        public void setSensorInfoTimestampSource(String sensorInfoTimestampSource) {
            this.sensorInfoTimestampSource = sensorInfoTimestampSource;
        }

        public float getHyperFocalDistance() {
            return hyperFocalDistance;
        }

        public void setHyperFocalDistance(float hyperFocalDistance) {
            this.hyperFocalDistance = hyperFocalDistance;
        }

        public String getFocusDistanceCalibration() {
            return focusDistanceCalibration;
        }

        public void setFocusDistanceCalibration(String focusDistanceCalibration) {
            this.focusDistanceCalibration = focusDistanceCalibration;
        }

        public JSONArray getAvailableOpticalStabilization() {
            return availableOpticalStabilization;
        }

        public void setAvailableOpticalStabilization(JSONArray availableOpticalStabilization) {
            this.availableOpticalStabilization = availableOpticalStabilization;
        }

        public JSONArray getLensInfoAvailableFocalLengths() {
            return lensInfoAvailableFocalLengths;
        }

        public void setLensInfoAvailableFocalLengths(JSONArray lensInfoAvailableFocalLengths) {
            this.lensInfoAvailableFocalLengths = lensInfoAvailableFocalLengths;
        }

        public String getSupportedHardwareLevel() {
            return supportedHardwareLevel;
        }

        public JSONArray getLensInfoAvailableFilterDensities() {
            return lensInfoAvailableFilterDensities;
        }

        public void setLensInfoAvailableFilterDensities(JSONArray lensInfoAvailableFilterDensities) {
            this.lensInfoAvailableFilterDensities = lensInfoAvailableFilterDensities;
        }

        public JSONArray getLensInfoAvailableApertures() {
            return lensInfoAvailableApertures;
        }

        public void setLensInfoAvailableApertures(JSONArray lensInfoAvailableApertures) {
            this.lensInfoAvailableApertures = lensInfoAvailableApertures;
        }

        public void setSupportedHardwareLevel(String supportedHardwareLevel) {
            this.supportedHardwareLevel = supportedHardwareLevel;
        }

        public boolean isDepthIsExclusive() {
            return depthIsExclusive;
        }

        public void setDepthIsExclusive(boolean depthIsExclusive) {
            this.depthIsExclusive = depthIsExclusive;
        }

        public String getRawSensitivityBoostRange() {
            return rawSensitivityBoostRange;
        }

        public void setRawSensitivityBoostRange(String rawSensitivityBoostRange) {
            this.rawSensitivityBoostRange = rawSensitivityBoostRange;
        }

        public int getMaxRegionsAe() {
            return maxRegionsAe;
        }

        public void setMaxRegionsAe(int maxRegionsAe) {
            this.maxRegionsAe = maxRegionsAe;
        }

        public int getMaxRegionsAf() {
            return maxRegionsAf;
        }

        public void setMaxRegionsAf(int maxRegionsAf) {
            this.maxRegionsAf = maxRegionsAf;
        }

        public int getMaxRegionsAwb() {
            return maxRegionsAwb;
        }

        public void setMaxRegionsAwb(int maxRegionsAwb) {
            this.maxRegionsAwb = maxRegionsAwb;
        }

        public boolean isAwbLockAvailable() {
            return awbLockAvailable;
        }

        public void setAwbLockAvailable(boolean awbLockAvailable) {
            this.awbLockAvailable = awbLockAvailable;
        }

        public JSONArray getAwbAvailableModes() {
            return awbAvailableModes;
        }

        public void setAwbAvailableModes(JSONArray awbAvailableModes) {
            this.awbAvailableModes = awbAvailableModes;
        }

        public JSONArray getVideoStabilizationModes() {
            return videoStabilizationModes;
        }

        public void setVideoStabilizationModes(JSONArray videoStabilizationModes) {
            this.videoStabilizationModes = videoStabilizationModes;
        }

        public JSONArray getAvailableSceneModes() {
            return availableSceneModes;
        }

        public void setAvailableSceneModes(JSONArray availableSceneModes) {
            this.availableSceneModes = availableSceneModes;
        }

        public JSONArray getAvailableEffects() {
            return availableEffects;
        }

        public void setAvailableEffects(JSONArray availableEffects) {
            this.availableEffects = availableEffects;
        }

        public JSONArray getAfAvailableModes() {
            return afAvailableModes;
        }

        public void setAfAvailableModes(JSONArray afAvailableModes) {
            this.afAvailableModes = afAvailableModes;
        }

        public boolean isLockAvailable() {
            return lockAvailable;
        }

        public void setLockAvailable(boolean lockAvailable) {
            this.lockAvailable = lockAvailable;
        }

        public double getCompensationStep() {
            return compensationStep;
        }

        public void setCompensationStep(double compensationStep) {
            this.compensationStep = compensationStep;
        }

        public String getCompensationRange() {
            return compensationRange;
        }

        public void setCompensationRange(String compensationRange) {
            this.compensationRange = compensationRange;
        }

        public JSONArray getFpsRanges() {
            return fpsRanges;
        }

        public void setFpsRanges(JSONArray fpsRanges) {
            this.fpsRanges = fpsRanges;
        }

        public JSONArray getAeAvailableModes() {
            return aeAvailableModes;
        }

        public void setAeAvailableModes(JSONArray aeAvailableModes) {
            this.aeAvailableModes = aeAvailableModes;
        }

        public JSONArray getAntiBandingModes() {
            return antiBandingModes;
        }

        public void setAntiBandingModes(JSONArray antiBandingModes) {
            this.antiBandingModes = antiBandingModes;
        }

        public JSONArray getAberrationModes() {
            return aberrationModes;
        }

        public void setAberrationModes(JSONArray aberrationModes) {
            this.aberrationModes = aberrationModes;
        }

        public JSONArray getAvailableModes() {
            return availableModes;
        }

        public void setAvailableModes(JSONArray availableModes) {
            this.availableModes = availableModes;
        }

        public boolean isCameraFlashInfo() {
            return cameraFlashInfo;
        }

        public void setCameraFlashInfo(boolean cameraFlashInfo) {
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

        public JSONArray getCorrectionAvailableModes() {
            return correctionAvailableModes;
        }

        public void setCorrectionAvailableModes(JSONArray correctionAvailableModes) {
            this.correctionAvailableModes = correctionAvailableModes;
        }

        public JSONArray getAvailableEdgeModes() {
            return availableEdgeModes;
        }

        public void setAvailableEdgeModes(JSONArray availableEdgeModes) {
            this.availableEdgeModes = availableEdgeModes;
        }

        public JSONArray getAvailableHotPixelModes() {
            return availableHotPixelModes;
        }

        public void setAvailableHotPixelModes(JSONArray availableHotPixelModes) {
            this.availableHotPixelModes = availableHotPixelModes;
        }

        public String getInfoVersion() {
            return infoVersion;
        }

        public void setInfoVersion(String infoVersion) {
            this.infoVersion = infoVersion;
        }

        public JSONArray getJpegAvailableThumbnailSizes() {
            return jpegAvailableThumbnailSizes;
        }

        public void setJpegAvailableThumbnailSizes(JSONArray jpegAvailableThumbnailSizes) {
            this.jpegAvailableThumbnailSizes = jpegAvailableThumbnailSizes;
        }

        public JSONArray getLensDistortion() {
            return lensDistortion;
        }

        public void setLensDistortion(JSONArray lensDistortion) {
            this.lensDistortion = lensDistortion;
        }

        public float getMinimumFocusDistance() {
            return minimumFocusDistance;
        }

        public void setMinimumFocusDistance(float minimumFocusDistance) {
            this.minimumFocusDistance = minimumFocusDistance;
        }

        public JSONArray getLensIntrinsicCalibration() {
            return lensIntrinsicCalibration;
        }

        public void setLensIntrinsicCalibration(JSONArray lensIntrinsicCalibration) {
            this.lensIntrinsicCalibration = lensIntrinsicCalibration;
        }

        public String getLensPoseReference() {
            return lensPoseReference;
        }

        public void setLensPoseReference(String lensPoseReference) {
            this.lensPoseReference = lensPoseReference;
        }

        public JSONArray getLensPoseRotation() {
            return lensPoseRotation;
        }

        public void setLensPoseRotation(JSONArray lensPoseRotation) {
            this.lensPoseRotation = lensPoseRotation;
        }

        public JSONArray getLensPoseTranslation() {
            return lensPoseTranslation;
        }

        public void setLensPoseTranslation(JSONArray lensPoseTranslation) {
            this.lensPoseTranslation = lensPoseTranslation;
        }

        public String getCameraSensorSyncType() {
            return cameraSensorSyncType;
        }

        public void setCameraSensorSyncType(String cameraSensorSyncType) {
            this.cameraSensorSyncType = cameraSensorSyncType;
        }

        public JSONArray getAvailableNoiseReductionModes() {
            return availableNoiseReductionModes;
        }

        public void setAvailableNoiseReductionModes(JSONArray availableNoiseReductionModes) {
            this.availableNoiseReductionModes = availableNoiseReductionModes;
        }

        public int getRequestMaxNumInputStreams() {
            return requestMaxNumInputStreams;
        }

        public void setRequestMaxNumInputStreams(int requestMaxNumInputStreams) {
            this.requestMaxNumInputStreams = requestMaxNumInputStreams;
        }

        public int getMaxCaptureStall() {
            return maxCaptureStall;
        }

        public void setMaxCaptureStall(int maxCaptureStall) {
            this.maxCaptureStall = maxCaptureStall;
        }

        public float getScalerAvailableMaxDigitalZoom() {
            return scalerAvailableMaxDigitalZoom;
        }

        public void setScalerAvailableMaxDigitalZoom(float scalerAvailableMaxDigitalZoom) {
            this.scalerAvailableMaxDigitalZoom = scalerAvailableMaxDigitalZoom;
        }

        public JSONArray getRequestAvailableCapabilities() {
            return requestAvailableCapabilities;
        }

        public void setRequestAvailableCapabilities(JSONArray requestAvailableCapabilities) {
            this.requestAvailableCapabilities = requestAvailableCapabilities;
        }

        public int getRequestMaxNumOutputProc() {
            return requestMaxNumOutputProc;
        }

        public void setRequestMaxNumOutputProc(int requestMaxNumOutputProc) {
            this.requestMaxNumOutputProc = requestMaxNumOutputProc;
        }

        public int getRequestMaxNumOutputProcStalling() {
            return requestMaxNumOutputProcStalling;
        }

        public void setRequestMaxNumOutputProcStalling(int requestMaxNumOutputProcStalling) {
            this.requestMaxNumOutputProcStalling = requestMaxNumOutputProcStalling;
        }

        public int getRequestMaxNumOutputRaw() {
            return requestMaxNumOutputRaw;
        }

        public void setRequestMaxNumOutputRaw(int requestMaxNumOutputRaw) {
            this.requestMaxNumOutputRaw = requestMaxNumOutputRaw;
        }

        public int getRequestPartialResultCount() {
            return requestPartialResultCount;
        }

        public void setRequestPartialResultCount(int requestPartialResultCount) {
            this.requestPartialResultCount = requestPartialResultCount;
        }

        public byte getRequestPipelineMaxDepth() {
            return requestPipelineMaxDepth;
        }

        public void setRequestPipelineMaxDepth(byte requestPipelineMaxDepth) {
            this.requestPipelineMaxDepth = requestPipelineMaxDepth;
        }

        public String getScalerCroppingType() {
            return scalerCroppingType;
        }

        public void setScalerCroppingType(String scalerCroppingType) {
            this.scalerCroppingType = scalerCroppingType;
        }

        public JSONArray getSensorAvailableTestPatternModes() {
            return sensorAvailableTestPatternModes;
        }

        public String getSensorInfoSensitivityRange() {
            return sensorInfoSensitivityRange;
        }

        public void setSensorInfoSensitivityRange(String sensorInfoSensitivityRange) {
            this.sensorInfoSensitivityRange = sensorInfoSensitivityRange;
        }

        public void setSensorAvailableTestPatternModes(JSONArray sensorAvailableTestPatternModes) {
            this.sensorAvailableTestPatternModes = sensorAvailableTestPatternModes;
        }

        public String getSensorInfoColorFilterArrangement() {
            return sensorInfoColorFilterArrangement;
        }

        public void setSensorInfoColorFilterArrangement(String sensorInfoColorFilterArrangement) {
            this.sensorInfoColorFilterArrangement = sensorInfoColorFilterArrangement;
        }

        public String getSensorInfoExposureTimeRange() {
            return sensorInfoExposureTimeRange;
        }

        public void setSensorInfoExposureTimeRange(String sensorInfoExposureTimeRange) {
            this.sensorInfoExposureTimeRange = sensorInfoExposureTimeRange;
        }

        public boolean isSensorInfoLensShadingApplied() {
            return sensorInfoLensShadingApplied;
        }

        public void setSensorInfoLensShadingApplied(boolean sensorInfoLensShadingApplied) {
            this.sensorInfoLensShadingApplied = sensorInfoLensShadingApplied;
        }

        public long getSensorInfoaxFrameDuration() {
            return sensorInfoaxFrameDuration;
        }

        public void setSensorInfoaxFrameDuration(long sensorInfoaxFrameDuration) {
            this.sensorInfoaxFrameDuration = sensorInfoaxFrameDuration;
        }

        public int getSensorMaxAnalogSensitivity() {
            return sensorMaxAnalogSensitivity;
        }

        public void setSensorMaxAnalogSensitivity(int sensorMaxAnalogSensitivity) {
            this.sensorMaxAnalogSensitivity = sensorMaxAnalogSensitivity;
        }

        public String getSensorReferenceIlluminant1() {
            return sensorReferenceIlluminant1;
        }

        public void setSensorReferenceIlluminant1(String sensorReferenceIlluminant1) {
            this.sensorReferenceIlluminant1 = sensorReferenceIlluminant1;
        }

        public String getSyncMaxLatency() {
            return syncMaxLatency;
        }

        public void setSyncMaxLatency(String syncMaxLatency) {
            this.syncMaxLatency = syncMaxLatency;
        }





        @Override
        protected JSONObject toJSONObject() {
            try {
                jsonObject.put(BaseData.Camera.CameraInfo.CAMERA_FACING, isEmpty(cameraFacing));
                jsonObject.put(BaseData.Camera.CameraInfo.CAMERA_LEVEL, isEmpty(cameraLevel));
                jsonObject.put(BaseData.Camera.CameraInfo.CAMERA_FLASH_INFO, cameraFlashInfo);
                jsonObject.put(BaseData.Camera.CameraInfo.OUTPUT_FORMATS, outputFormats);
                jsonObject.put("aberrationModes", aberrationModes);
                jsonObject.put("antiBandingModes", antiBandingModes);
                jsonObject.put("aeAvailableModes", aeAvailableModes);
                jsonObject.put("compensationRange", isEmpty(compensationRange));
                jsonObject.put("compensationStep", compensationStep);
                jsonObject.put("lockAvailable", lockAvailable);
                jsonObject.put("afAvailableModes", afAvailableModes);
                jsonObject.put("availableEffects", availableEffects);
                jsonObject.put("availableModes", availableModes);
                jsonObject.put("availableSceneModes", availableSceneModes);
                jsonObject.put("videoStabilizationModes", videoStabilizationModes);
                jsonObject.put("awbAvailableModes", awbAvailableModes);
                jsonObject.put("awbLockAvailable", awbLockAvailable);
                jsonObject.put("maxRegionsAe", maxRegionsAe);
                jsonObject.put("maxRegionsAf", maxRegionsAf);
                jsonObject.put("maxRegionsAwb", maxRegionsAwb);
                jsonObject.put("rawSensitivityBoostRange", isEmpty(rawSensitivityBoostRange));
                jsonObject.put("depthIsExclusive", depthIsExclusive);
                jsonObject.put("correctionAvailableModes", correctionAvailableModes);
                jsonObject.put("availableEdgeModes", availableEdgeModes);
                jsonObject.put("availableHotPixelModes", availableHotPixelModes);
                jsonObject.put("supportedHardwareLevel", isEmpty(supportedHardwareLevel));
                jsonObject.put("infoVersion", isEmpty(infoVersion));
                jsonObject.put("jpegAvailableThumbnailSizes",jpegAvailableThumbnailSizes);
                jsonObject.put("lensDistortion",lensDistortion);
                jsonObject.put("lensInfoAvailableApertures",lensInfoAvailableApertures);
                jsonObject.put("lensInfoAvailableFilterDensities",lensInfoAvailableFilterDensities);
                jsonObject.put("lensInfoAvailableFocalLengths",lensInfoAvailableFocalLengths);
                jsonObject.put("availableOpticalStabilization",availableOpticalStabilization);
                jsonObject.put("focusDistanceCalibration",isEmpty(focusDistanceCalibration));
                jsonObject.put("hyperFocalDistance",hyperFocalDistance);
                jsonObject.put("minimumFocusDistance",minimumFocusDistance);
                jsonObject.put("lensIntrinsicCalibration",lensIntrinsicCalibration);
                jsonObject.put("lensPoseReference",isEmpty(lensPoseReference));
                jsonObject.put("lensPoseRotation",lensPoseRotation);
                jsonObject.put("lensPoseTranslation",lensPoseTranslation);
                jsonObject.put("cameraSensorSyncType",isEmpty(cameraSensorSyncType));
                jsonObject.put("availableNoiseReductionModes",availableNoiseReductionModes);
                jsonObject.put("maxCaptureStall",maxCaptureStall);
                jsonObject.put("requestAvailableCapabilities",requestAvailableCapabilities);
                jsonObject.put("requestMaxNumInputStreams",requestMaxNumInputStreams);
                jsonObject.put("requestMaxNumOutputProc",requestMaxNumOutputProc);
                jsonObject.put("requestMaxNumOutputProcStalling",requestMaxNumOutputProcStalling);
                jsonObject.put("requestMaxNumOutputRaw",requestMaxNumOutputRaw);
                jsonObject.put("requestPartialResultCount",requestPartialResultCount);
                jsonObject.put("requestPipelineMaxDepth",requestPipelineMaxDepth);
                jsonObject.put("scalerAvailableMaxDigitalZoom",scalerAvailableMaxDigitalZoom);
                jsonObject.put("scalerCroppingType",isEmpty(scalerCroppingType));
                jsonObject.put("sensorAvailableTestPatternModes",sensorAvailableTestPatternModes);
                jsonObject.put("sensorInfoColorFilterArrangement",isEmpty(sensorInfoColorFilterArrangement));
                jsonObject.put("sensorInfoExposureTimeRange",isEmpty(sensorInfoExposureTimeRange));
                jsonObject.put("sensorInfoLensShadingApplied",sensorInfoLensShadingApplied);
                jsonObject.put("sensorInfoaxFrameDuration",sensorInfoaxFrameDuration);
                jsonObject.put("sensorInfoSensitivityRange",isEmpty(sensorInfoSensitivityRange));
                jsonObject.put("sensorInfoTimestampSource",isEmpty(sensorInfoTimestampSource));
                jsonObject.put("sensorInfoWhiteLevel",sensorInfoWhiteLevel);
                jsonObject.put("sensorMaxAnalogSensitivity",sensorMaxAnalogSensitivity);
                jsonObject.put("sensorOrientation",sensorOrientation);
                jsonObject.put("sensorReferenceIlluminant1",isEmpty(sensorReferenceIlluminant1));
                jsonObject.put("shadingAvailableModes",shadingAvailableModes);
                jsonObject.put("availableFaceDetectModes", availableFaceDetectModes);
                jsonObject.put("availableLensShadingMapModes", availableLensShadingMapModes);
                jsonObject.put("availableOisDataModes", availableOisDataModes);
                jsonObject.put("statisticsInfoMaxFaceCount", statisticsInfoMaxFaceCount);
                jsonObject.put("syncMaxLatency", isEmpty(syncMaxLatency));
                jsonObject.put("availableToneMapModes", availableToneMapModes);
                jsonObject.put("tonemapMaxCurvePoints", tonemapMaxCurvePoints);

                jsonObject.put("fpsRanges", fpsRanges);

            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
            return super.toJSONObject();
        }
    }
}
