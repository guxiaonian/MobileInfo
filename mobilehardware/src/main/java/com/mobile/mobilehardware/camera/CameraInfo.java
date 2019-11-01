package com.mobile.mobilehardware.camera;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Log;
import android.util.Range;
import android.util.Rational;
import android.util.Size;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/1/5
 */
class CameraInfo {

    private static final String UNKNOWN = "UNKNOWN";

    private static final String TAG = CameraInfo.class.getSimpleName();

    static JSONObject cameraInfo(Context context) {
        CameraBean cameraBean = new CameraBean();
        JSONArray jsonArray = new JSONArray();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CameraManager manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
                for (String cameraId : manager.getCameraIdList()) {
                    CameraBean.CameraInfoBean cameraInfoBean = new CameraBean.CameraInfoBean();
                    CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                    if (CameraCharacteristics.LENS_FACING != null) {
                        Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                        //摄像头位置 后置 前置 外置
                        cameraInfoBean.setCameraFacing(getFacing(facing));
                    }

                    if (CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL != null) {
                        Integer level = characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                        //摄像头支持的设备等级
                        cameraInfoBean.setCameraLevel(getLevel(level));
                    }
                    if (CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP != null) {
                        StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                        if (map != null) {
                            int[] ints = map.getOutputFormats();
                            JSONArray jsonArrayOutputFormats = new JSONArray();
                            for (int i : ints) {
                                jsonArrayOutputFormats.put(getFormat(i));
                            }
                            //摄像头支持格式
                            cameraInfoBean.setOutputFormats(jsonArrayOutputFormats);
                        }
                    }

                    //TODO 本相机设备支持的像差校正模式列表
                    if (CameraCharacteristics.COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES != null) {
                        int[] aberrationModes = characteristics.get(CameraCharacteristics.COLOR_CORRECTION_AVAILABLE_ABERRATION_MODES);
                        if (aberrationModes != null && aberrationModes.length != 0) {
                            JSONArray jsonArrayAberrationModes = new JSONArray();
                            for (int i : aberrationModes) {
                                jsonArrayAberrationModes.put(getAberrationModes(i));
                            }
                            cameraInfoBean.setAberrationModes(jsonArrayAberrationModes);
                        }
                    }
                    //TODO 本相机设备支持的自动曝光防条纹模式列表
                    if (CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES != null) {
                        int[] antiBandingModes = characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_ANTIBANDING_MODES);
                        if (antiBandingModes != null && antiBandingModes.length != 0) {
                            JSONArray jsonArrayAntiBandingModes = new JSONArray();
                            for (int i : antiBandingModes) {
                                jsonArrayAntiBandingModes.put(getAntiBandingModes(i));
                            }
                            cameraInfoBean.setAntiBandingModes(jsonArrayAntiBandingModes);
                        }
                    }
                    //TODO 本相机设备支持的自动曝光模式列表
                    if (CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES != null) {
                        int[] aeAvailableModes = characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_MODES);
                        if (aeAvailableModes != null && aeAvailableModes.length != 0) {
                            JSONArray jsonArrayAeAvailableModes = new JSONArray();
                            for (int i : aeAvailableModes) {
                                jsonArrayAeAvailableModes.put(getAeAvailableModes(i));
                            }
                            cameraInfoBean.setAeAvailableModes(jsonArrayAeAvailableModes);
                        }
                    }
                    //TODO 此相机设备支持的最大和最小曝光补偿值
                    if (CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE != null) {
                        Range<Integer> compensationRange = characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
                        if (compensationRange != null) {
                            cameraInfoBean.setCompensationRange(compensationRange.toString());
                        }
                    }
                    //TODO 可以更改曝光补偿的最小步长
                    if (CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP != null) {
                        Rational compensationStep = characteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP);
                        if (compensationStep != null) {
                            cameraInfoBean.setCompensationStep(compensationStep.doubleValue());
                        }
                    }
                    //TODO 是否锁定自动曝光
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.CONTROL_AE_LOCK_AVAILABLE != null) {
                        Boolean lockAvailable = characteristics.get(CameraCharacteristics.CONTROL_AE_LOCK_AVAILABLE);
                        if (lockAvailable != null) {
                            cameraInfoBean.setLockAvailable(lockAvailable);
                        }
                    }
                    //TODO 相机设备支持的自动对焦（AF）模式列表
                    if (CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES != null) {
                        int[] afAvailableModes = characteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
                        if (afAvailableModes != null && afAvailableModes.length != 0) {
                            JSONArray jsonArrayAfAvailableModes = new JSONArray();
                            for (int i : afAvailableModes) {
                                jsonArrayAfAvailableModes.put(getAfAvailableModes(i));
                            }
                            cameraInfoBean.setAfAvailableModes(jsonArrayAfAvailableModes);
                        }
                    }
                    //TODO 本相机设备支持的色彩效果列表
                    if (CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS != null) {
                        int[] availableEffects = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_EFFECTS);
                        if (availableEffects != null && availableEffects.length != 0) {
                            JSONArray jsonArrayAvailableEffects = new JSONArray();
                            for (int i : availableEffects) {
                                jsonArrayAvailableEffects.put(getAvailableEffects(i));
                            }
                            cameraInfoBean.setAvailableEffects(jsonArrayAvailableEffects);
                        }
                    }
                    //TODO 本相机设备支持的控制模式列表
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.CONTROL_AVAILABLE_MODES != null) {
                        int[] availableModes = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_MODES);
                        if (availableModes != null && availableModes.length != 0) {
                            JSONArray jsonArrayAvailableModes = new JSONArray();
                            for (int i : availableModes) {
                                jsonArrayAvailableModes.put(getAvailableModes(i));
                            }
                            cameraInfoBean.setAvailableModes(jsonArrayAvailableModes);
                        }

                    }
                    //TODO 本相机设备支持的场景模式列表
                    if (CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES != null) {
                        int[] availableSceneModes = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_SCENE_MODES);
                        if (availableSceneModes != null && availableSceneModes.length != 0) {
                            JSONArray jsonArrayAvailableSceneModes = new JSONArray();
                            for (int i : availableSceneModes) {
                                jsonArrayAvailableSceneModes.put(getAvailableSceneModes(i));
                            }
                            cameraInfoBean.setAvailableSceneModes(jsonArrayAvailableSceneModes);
                        }
                    }
                    //TODO 本相机设备支持的视频稳定模式列表
                    if (CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES != null) {
                        int[] videoStabilizationModes = characteristics.get(CameraCharacteristics.CONTROL_AVAILABLE_VIDEO_STABILIZATION_MODES);
                        if (videoStabilizationModes != null && videoStabilizationModes.length != 0) {
                            JSONArray jsonArrayVideoStabilizationModes = new JSONArray();
                            for (int i : videoStabilizationModes) {
                                jsonArrayVideoStabilizationModes.put(getVideoStabilizationModes(i));
                            }
                            cameraInfoBean.setVideoStabilizationModes(jsonArrayVideoStabilizationModes);
                        }
                    }
                    //TODO 本相机设备支持的自动白平衡模式列表
                    if (CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES != null) {
                        int[] awbAvailableModes = characteristics.get(CameraCharacteristics.CONTROL_AWB_AVAILABLE_MODES);
                        if (awbAvailableModes != null && awbAvailableModes.length != 0) {
                            JSONArray jsonArrayAwbAvailableModes = new JSONArray();
                            for (int i : awbAvailableModes) {
                                jsonArrayAwbAvailableModes.put(getAwbAvailableModes(i));
                            }
                            cameraInfoBean.setAwbAvailableModes(jsonArrayAwbAvailableModes);
                        }
                    }
                    //TODO 设备是否支持自动白平衡

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.CONTROL_AWB_LOCK_AVAILABLE != null) {
                        Boolean awbLockAvailable = characteristics.get(CameraCharacteristics.CONTROL_AWB_LOCK_AVAILABLE);
                        if (awbLockAvailable != null) {
                            cameraInfoBean.setAwbLockAvailable(awbLockAvailable);
                        }
                    }

                    //TODO 自动曝光（AE）例程可以使用的最大测光区域数
                    if (CameraCharacteristics.CONTROL_MAX_REGIONS_AE != null) {
                        int maxRegionsAe = characteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AE);
                        cameraInfoBean.setMaxRegionsAe(maxRegionsAe);
                    }

                    //TODO 自动对焦（AF）例程可以使用的最大测光区域数
                    if (CameraCharacteristics.CONTROL_MAX_REGIONS_AF != null) {
                        int maxRegionsAf = characteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
                        cameraInfoBean.setMaxRegionsAf(maxRegionsAf);
                    }

                    //TODO 自动白平衡（AWB）例程可以使用的最大测光区域数
                    if (CameraCharacteristics.CONTROL_MAX_REGIONS_AWB != null) {
                        int maxRegionsAwb = characteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AWB);
                        cameraInfoBean.setMaxRegionsAwb(maxRegionsAwb);
                    }

                    //TODO 相机设备支持的增强范围
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N && CameraCharacteristics.CONTROL_POST_RAW_SENSITIVITY_BOOST_RANGE != null) {
                        Range<Integer> rawSensitivityBoostRange = characteristics.get(CameraCharacteristics.CONTROL_POST_RAW_SENSITIVITY_BOOST_RANGE);
                        if (rawSensitivityBoostRange != null) {
                            cameraInfoBean.setRawSensitivityBoostRange(rawSensitivityBoostRange.toString());
                        }
                    }

                    //TODO 指示捕获请求是否可以同时针对DEPTH16 / DEPTH_POINT_CLOUD输出和常规彩色输出 true为不可以
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.DEPTH_DEPTH_IS_EXCLUSIVE != null) {
                        Boolean depthIsExclusive = characteristics.get(CameraCharacteristics.DEPTH_DEPTH_IS_EXCLUSIVE);
                        if (depthIsExclusive != null) {
                            cameraInfoBean.setDepthIsExclusive(depthIsExclusive);
                        }
                    }


                    //TODO 相机设备支持的帧频范围列表
                    if (CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES != null) {
                        Range<Integer>[] fpsRanges = characteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                        if (fpsRanges != null && fpsRanges.length != 0) {
                            JSONArray jsonArrayFpsRanges = new JSONArray();
                            for (Range<Integer> i : fpsRanges) {
                                jsonArrayFpsRanges.put(i);
                            }
                            cameraInfoBean.setFpsRanges(jsonArrayFpsRanges);
                        }
                    }

                    //TODO 本相机设备支持的失真校正模式列表
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && CameraCharacteristics.DISTORTION_CORRECTION_AVAILABLE_MODES != null) {
                        int[] correctionAvailableModes = characteristics.get(CameraCharacteristics.DISTORTION_CORRECTION_AVAILABLE_MODES);
                        if (correctionAvailableModes != null && correctionAvailableModes.length != 0) {
                            JSONArray jsonArrayCorrectionAvailableModes = new JSONArray();
                            for (int i : correctionAvailableModes) {
                                jsonArrayCorrectionAvailableModes.put(getCorrectionAvailableModes(i));
                            }
                            cameraInfoBean.setCorrectionAvailableModes(jsonArrayCorrectionAvailableModes);
                        }
                    }


                    //TODO 本相机设备支持的边缘增强模式列表
                    if (CameraCharacteristics.EDGE_AVAILABLE_EDGE_MODES != null) {
                        int[] availableEdgeModes = characteristics.get(CameraCharacteristics.EDGE_AVAILABLE_EDGE_MODES);
                        if (availableEdgeModes != null && availableEdgeModes.length != 0) {
                            JSONArray jsonArrayAvailableEdgeModes = new JSONArray();
                            for (int i : availableEdgeModes) {
                                jsonArrayAvailableEdgeModes.put(getAvailableEdgeModes(i));
                            }
                            cameraInfoBean.setAvailableEdgeModes(jsonArrayAvailableEdgeModes);
                        }
                    }

                    //摄像头是否支持闪光灯
                    if (CameraCharacteristics.FLASH_INFO_AVAILABLE != null) {
                        Boolean flashInfoAvailable = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                        if (flashInfoAvailable != null) {
                            cameraInfoBean.setCameraFlashInfo(flashInfoAvailable);
                        }
                    }

                    //TODO 本相机设备支持的热像素校正模式列表
                    if (CameraCharacteristics.HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES != null) {
                        int[] availableHotPixelModes = characteristics.get(CameraCharacteristics.HOT_PIXEL_AVAILABLE_HOT_PIXEL_MODES);
                        if (availableHotPixelModes != null && availableHotPixelModes.length != 0) {
                            JSONArray jsonArrayAvailableHotPixelModes = new JSONArray();
                            for (int i : availableHotPixelModes) {
                                jsonArrayAvailableHotPixelModes.put(getAvailableHotPixelModes(i));
                            }
                            cameraInfoBean.setAvailableHotPixelModes(jsonArrayAvailableHotPixelModes);
                        }
                    }
                    //TODO 通常对相机设备功能的总体分类
                    if (CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL != null) {
                        cameraInfoBean.setSupportedHardwareLevel(getLevel(characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)));
                    }
                    //TODO 摄像机设备制造商版本信息的简短字符串
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && CameraCharacteristics.INFO_VERSION != null) {
                        cameraInfoBean.setInfoVersion(characteristics.get(CameraCharacteristics.INFO_VERSION));
                    }
                    //TODO 此相机设备支持的JPEG缩略图尺寸列表
                    if (CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES != null) {
                        Size[] jpegAvailableThumbnailSizes = characteristics.get(CameraCharacteristics.JPEG_AVAILABLE_THUMBNAIL_SIZES);
                        JSONArray jsonArrayJpegAvailableThumbnailSizes = new JSONArray();
                        if (jpegAvailableThumbnailSizes != null && jpegAvailableThumbnailSizes.length != 0) {
                            for (Size s : jpegAvailableThumbnailSizes) {
                                jsonArrayJpegAvailableThumbnailSizes.put(s.toString());
                            }
                        }
                        cameraInfoBean.setJpegAvailableThumbnailSizes(jsonArrayJpegAvailableThumbnailSizes);
                    }
                    //TODO 用于校正此相机设备的径向和切向镜头失真的校正系数
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && CameraCharacteristics.LENS_DISTORTION != null) {
                        float[] lensDistortion = characteristics.get(CameraCharacteristics.LENS_DISTORTION);
                        if (lensDistortion != null && lensDistortion.length != 0) {
                            cameraInfoBean.setLensDistortion(new JSONArray(lensDistortion));
                        }
                    }
                    //TODO 此相机设备支持的光圈大小值列表
                    if (CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES != null) {
                        float[] lensInfoAvailableApertures = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES);
                        if (lensInfoAvailableApertures != null && lensInfoAvailableApertures.length != 0) {
                            cameraInfoBean.setLensInfoAvailableApertures(new JSONArray(lensInfoAvailableApertures));
                        }
                    }
                    //TODO 此相机设备支持的中性密度滤镜值列表
                    if (CameraCharacteristics.LENS_INFO_AVAILABLE_FILTER_DENSITIES != null) {
                        float[] lensInfoAvailableFilterDensities = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FILTER_DENSITIES);
                        if(lensInfoAvailableFilterDensities!=null&&lensInfoAvailableFilterDensities.length!=0){
                            cameraInfoBean.setLensInfoAvailableFilterDensities(new JSONArray(lensInfoAvailableFilterDensities));
                        }
                    }
                    //TODO 此相机设备支持的焦距列表
                    if (CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS != null) {
                        float[] lensInfoAvailableFocalLengths = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
                       if(lensInfoAvailableFocalLengths!=null&&lensInfoAvailableFocalLengths.length!=0){
                           cameraInfoBean.setLensInfoAvailableFocalLengths(new JSONArray(lensInfoAvailableFocalLengths));
                       }
                    }
                    //TODO 本相机设备支持的光学防抖（OIS）模式列表
                    if (CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION != null) {
                        int[] availableOpticalStabilization = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION);
                        if (availableOpticalStabilization != null && availableOpticalStabilization.length != 0) {
                            JSONArray jsonArrayAvailableOpticalStabilization = new JSONArray();
                            for (int i : availableOpticalStabilization) {
                                jsonArrayAvailableOpticalStabilization.put(getAvailableOpticalStabilization(i));
                            }
                            cameraInfoBean.setAvailableOpticalStabilization(jsonArrayAvailableOpticalStabilization);
                        }
                    }
                    //TODO 镜头焦距校准质量
                    if (CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION != null) {
                        Integer focusDistanceCalibration = characteristics.get(CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION);
                        cameraInfoBean.setFocusDistanceCalibration(getFocusDistanceCalibration(focusDistanceCalibration));
                    }
                    //TODO 镜头的超焦距
                    if (CameraCharacteristics.LENS_INFO_HYPERFOCAL_DISTANCE != null) {
                        float hyperFocalDistance = characteristics.get(CameraCharacteristics.LENS_INFO_HYPERFOCAL_DISTANCE);
                        cameraInfoBean.setHyperFocalDistance(hyperFocalDistance);
                    }
                    //TODO 距镜头最前面的最短距离，可使其聚焦
                    if (CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE != null) {
                        float minimumFocusDistance = characteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
                        cameraInfoBean.setMinimumFocusDistance(minimumFocusDistance);
                    }
                    //TODO 本相机设备固有校准的参数
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.LENS_INTRINSIC_CALIBRATION != null) {
                        float[] lensIntrinsicCalibration = characteristics.get(CameraCharacteristics.LENS_INTRINSIC_CALIBRATION);
                       if(lensIntrinsicCalibration!=null&&lensIntrinsicCalibration.length!=0){
                           cameraInfoBean.setLensIntrinsicCalibration(new JSONArray(lensIntrinsicCalibration));
                       }
                    }
                    //TODO 镜头姿势
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P && CameraCharacteristics.LENS_POSE_REFERENCE != null) {
                        Integer lensPoseReference = characteristics.get(CameraCharacteristics.LENS_POSE_REFERENCE);
                        cameraInfoBean.setLensPoseReference(getLensPoseReference(lensPoseReference));
                    }
                    //TODO 相机相对于传感器坐标系的方向
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.LENS_POSE_ROTATION != null) {
                        float[] lensPoseRotation = characteristics.get(CameraCharacteristics.LENS_POSE_ROTATION);
                        if(lensPoseRotation!=null&&lensPoseRotation.length!=0){
                            cameraInfoBean.setLensPoseRotation(new JSONArray(lensPoseRotation));
                        }
                    }
                    //TODO 相机光学中心的位置
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.LENS_POSE_TRANSLATION != null) {
                        float[] lensPoseTranslation = characteristics.get(CameraCharacteristics.LENS_POSE_TRANSLATION);
                      if(lensPoseTranslation!=null&&lensPoseTranslation.length!=0){
                          cameraInfoBean.setLensPoseTranslation(new JSONArray(lensPoseTranslation));
                      }
                    }
                    //TODO 帧时间戳同步
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P && CameraCharacteristics.LOGICAL_MULTI_CAMERA_SENSOR_SYNC_TYPE != null) {
                        Integer cameraSensorSyncType = characteristics.get(CameraCharacteristics.LOGICAL_MULTI_CAMERA_SENSOR_SYNC_TYPE);
                        cameraInfoBean.setCameraSensorSyncType(getCameraSensorSyncType(cameraSensorSyncType));
                    }
                    //TODO 本相机设备支持的降噪模式列表
                    if (CameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES != null) {
                        int[] availableNoiseReductionModes = characteristics.get(CameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES);
                        if (availableNoiseReductionModes != null && availableNoiseReductionModes.length != 0) {
                            JSONArray jsonArrayAvailableNoiseReductionModes = new JSONArray();
                            for (int i : availableNoiseReductionModes) {
                                jsonArrayAvailableNoiseReductionModes.put(getAvailableNoiseReductionModes(i));
                            }
                            cameraInfoBean.setAvailableNoiseReductionModes(jsonArrayAvailableNoiseReductionModes);
                        }
                    }
                    //TODO 最大摄像机捕获流水线停顿
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.REPROCESS_MAX_CAPTURE_STALL != null) {
                        Integer maxCaptureStall = characteristics.get(CameraCharacteristics.REPROCESS_MAX_CAPTURE_STALL);
                        if (maxCaptureStall != null) {
                            cameraInfoBean.setMaxCaptureStall(maxCaptureStall);
                        }
                    }
                    //TODO 此相机设备宣传为完全支持的功能列表
                    if (CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES != null) {
                        int[] requestAvailableCapabilities = characteristics.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
                        if (requestAvailableCapabilities != null && requestAvailableCapabilities.length != 0) {
                            JSONArray jsonArrayRequestAvailableCapabilities = new JSONArray();
                            for (int i : requestAvailableCapabilities) {
                                jsonArrayRequestAvailableCapabilities.put(getRequestAvailableCapabilities(i));
                            }
                            cameraInfoBean.setRequestAvailableCapabilities(jsonArrayRequestAvailableCapabilities);
                        }
                    }

                    //TODO 摄像机设备可以同时配置和使用的任何类型的输入流的最大数量
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.REQUEST_MAX_NUM_INPUT_STREAMS != null) {
                        Integer requestMaxNumInputStreams = characteristics.get(CameraCharacteristics.REQUEST_MAX_NUM_INPUT_STREAMS);
                        if (requestMaxNumInputStreams != null) {
                            cameraInfoBean.setRequestMaxNumInputStreams(requestMaxNumInputStreams);
                        }
                    }

                    //TODO 相机设备可以针对任何已处理（但不是陈旧）格式同时配置和使用的不同类型的输出流的最大数量
                    if (CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC != null) {
                        Integer requestMaxNumOutputProc = characteristics.get(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC);
                        if (requestMaxNumOutputProc != null) {
                            cameraInfoBean.setRequestMaxNumOutputProc(requestMaxNumOutputProc);
                        }
                    }

                    //TODO 相机设备可以针对任何已处理（和停顿）格式同时配置和使用的不同类型的输出流的最大数量
                    if (CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC_STALLING != null) {
                        Integer requestMaxNumOutputProcStalling = characteristics.get(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_PROC_STALLING);
                        if (requestMaxNumOutputProcStalling != null) {
                            cameraInfoBean.setRequestMaxNumOutputProcStalling(requestMaxNumOutputProcStalling);
                        }
                    }

                    //TODO 相机设备可以针对任何RAW格式同时配置和使用的不同类型的输出流的最大数量
                    if (CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_RAW != null) {
                        Integer requestMaxNumOutputRaw = characteristics.get(CameraCharacteristics.REQUEST_MAX_NUM_OUTPUT_RAW);
                        if (requestMaxNumOutputRaw != null) {
                            cameraInfoBean.setRequestMaxNumOutputRaw(requestMaxNumOutputRaw);
                        }
                    }

                    //TODO 定义结果将由多少个子组件组成
                    if (CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT != null) {
                        Integer requestPartialResultCount = characteristics.get(CameraCharacteristics.REQUEST_PARTIAL_RESULT_COUNT);
                        if (requestPartialResultCount != null) {
                            cameraInfoBean.setRequestPartialResultCount(requestPartialResultCount);
                        }
                    }

                    //TODO 指定从暴露帧到框架可用时必须经历的最大管道阶段数
                    if (CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH != null) {
                        Byte requestPipelineMaxDepth = characteristics.get(CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH);
                        if (requestPipelineMaxDepth != null) {
                            cameraInfoBean.setRequestPipelineMaxDepth(requestPipelineMaxDepth);
                        }
                    }

                    //TODO 活动区域宽度和作物区域宽度以及活动区域高度和作物区域高度之间的最大比率
                    if (CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM != null) {
                        Float scalerAvailableMaxDigitalZoom = characteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);
                        if (scalerAvailableMaxDigitalZoom != null) {
                            cameraInfoBean.setScalerAvailableMaxDigitalZoom(scalerAvailableMaxDigitalZoom);
                        }
                    }

                    //TODO 该相机设备支持的裁切类型
                    if (CameraCharacteristics.SCALER_CROPPING_TYPE != null) {
                        Integer scalerCroppingType = characteristics.get(CameraCharacteristics.SCALER_CROPPING_TYPE);
                        if (scalerCroppingType != null) {
                            cameraInfoBean.setScalerCroppingType(getScalerCroppingType(scalerCroppingType));
                        }
                    }

                    //TODO  此相机设备支持的传感器测试图案模式列表
                    if (CameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES != null) {
                        int[] sensorAvailableTestPatternModes = characteristics.get(CameraCharacteristics.SENSOR_AVAILABLE_TEST_PATTERN_MODES);
                        if (sensorAvailableTestPatternModes != null && sensorAvailableTestPatternModes.length != 0) {
                            JSONArray jsonArraySensorAvailableTestPatternModes = new JSONArray();
                            for (int i : sensorAvailableTestPatternModes) {
                                jsonArraySensorAvailableTestPatternModes.put(getSensorAvailableTestPatternModes(i));
                            }
                            cameraInfoBean.setSensorAvailableTestPatternModes(jsonArraySensorAvailableTestPatternModes);
                        }
                    }

                    //TODO 彩色滤光片在传感器上的布置
                    if (CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT != null) {
                        Integer sensorInfoColorFilterArrangement = characteristics.get(CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT);
                        if (sensorInfoColorFilterArrangement != null) {
                            cameraInfoBean.setSensorInfoColorFilterArrangement(getSensorInfoColorFilterArrangement(sensorInfoColorFilterArrangement));
                        }
                    }

                    //TODO 此相机设备支持的图像曝光时间范围
                    if (CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE != null) {
                        Range<Long> sensorInfoExposureTimeRange = characteristics.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE);
                        if (sensorInfoExposureTimeRange != null) {
                            cameraInfoBean.setSensorInfoExposureTimeRange(sensorInfoExposureTimeRange.toString());
                        }
                    }

                    //TODO 从本相机设备输出的RAW图像是否经过镜头阴影校正
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.SENSOR_INFO_LENS_SHADING_APPLIED != null) {
                        Boolean sensorInfoLensShadingApplied = characteristics.get(CameraCharacteristics.SENSOR_INFO_LENS_SHADING_APPLIED);
                        if (sensorInfoLensShadingApplied != null) {
                            cameraInfoBean.setSensorInfoLensShadingApplied(sensorInfoLensShadingApplied);
                        }
                    }

                    //TODO 本相机设备支持的最大可能帧时长
                    if (CameraCharacteristics.SENSOR_INFO_MAX_FRAME_DURATION != null) {
                        Long sensorInfoaxFrameDuration = characteristics.get(CameraCharacteristics.SENSOR_INFO_MAX_FRAME_DURATION);
                        if (sensorInfoaxFrameDuration != null) {
                            cameraInfoBean.setSensorInfoaxFrameDuration(sensorInfoaxFrameDuration);
                        }
                    }

                    //TODO 本相机设备支持的感光度范围
                    if (CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE != null) {
                        Range<Integer> sensorInfoSensitivityRange = characteristics.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE);
                        if (sensorInfoSensitivityRange != null) {
                            cameraInfoBean.setSensorInfoSensitivityRange(sensorInfoSensitivityRange.toString());
                        }
                    }

                    //TODO 传感器捕获开始时间戳记的时基源
                    if (CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE != null) {
                        Integer sensorInfoTimestampSource = characteristics.get(CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE);
                        if (sensorInfoTimestampSource != null) {
                            cameraInfoBean.setSensorInfoTimestampSource(getSensorInfoTimestampSource(sensorInfoTimestampSource));
                        }
                    }

                    //TODO 传感器输出的最大原始值
                    if (CameraCharacteristics.SENSOR_INFO_WHITE_LEVEL != null) {
                        Integer sensorInfoWhiteLevel = characteristics.get(CameraCharacteristics.SENSOR_INFO_WHITE_LEVEL);
                        if (sensorInfoWhiteLevel != null) {
                            cameraInfoBean.setSensorInfoWhiteLevel(sensorInfoWhiteLevel);
                        }
                    }

                    //TODO 纯粹通过模拟增益实现的最大灵敏度
                    if (CameraCharacteristics.SENSOR_MAX_ANALOG_SENSITIVITY != null) {
                        Integer sensorMaxAnalogSensitivity = characteristics.get(CameraCharacteristics.SENSOR_MAX_ANALOG_SENSITIVITY);
                        if (sensorMaxAnalogSensitivity != null) {
                            cameraInfoBean.setSensorMaxAnalogSensitivity(sensorMaxAnalogSensitivity);
                        }
                    }

                    //TODO 需要以顺时针方向旋转输出图像以使其在设备屏幕上以其原始方向直立
                    if (CameraCharacteristics.SENSOR_ORIENTATION != null) {
                        Integer sensorOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                        if (sensorOrientation != null) {
                            cameraInfoBean.setSensorOrientation(sensorOrientation);
                        }
                    }

                    //TODO 用作场景光源的标准参考光源
                    if (CameraCharacteristics.SENSOR_REFERENCE_ILLUMINANT1 != null) {
                        Integer sensorReferenceIlluminant1 = characteristics.get(CameraCharacteristics.SENSOR_REFERENCE_ILLUMINANT1);
                        if (sensorReferenceIlluminant1 != null) {
                            cameraInfoBean.setSensorReferenceIlluminant1(getSensorReferenceIlluminant1(sensorReferenceIlluminant1));
                        }
                    }

                    //TODO 本相机设备支持的镜头阴影模式列表
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.SHADING_AVAILABLE_MODES != null) {
                        int[] shadingAvailableModes = characteristics.get(CameraCharacteristics.SHADING_AVAILABLE_MODES);
                        if (shadingAvailableModes != null && shadingAvailableModes.length != 0) {
                            JSONArray jsonArrayShadingAvailableModes = new JSONArray();
                            for (int i : shadingAvailableModes) {
                                jsonArrayShadingAvailableModes.put(getShadingAvailableModes(i));
                            }
                            cameraInfoBean.setShadingAvailableModes(jsonArrayShadingAvailableModes);
                        }
                    }

                    //TODO 本相机设备支持的脸部识别模式列表
                    if (CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES != null) {
                        int[] availableFaceDetectModes = characteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES);
                        if (availableFaceDetectModes != null && availableFaceDetectModes.length != 0) {
                            JSONArray jsonArrayAvailableFaceDetectModes = new JSONArray();
                            for (int i : availableFaceDetectModes) {
                                jsonArrayAvailableFaceDetectModes.put(getAvailableFaceDetectModes(i));
                            }
                            cameraInfoBean.setAvailableFaceDetectModes(jsonArrayAvailableFaceDetectModes);
                        }
                    }

                    //TODO 本相机设备支持的镜头阴影贴图输出模式列表
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && CameraCharacteristics.STATISTICS_INFO_AVAILABLE_LENS_SHADING_MAP_MODES != null) {
                        int[] availableLensShadingMapModes = characteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_LENS_SHADING_MAP_MODES);
                        if (availableLensShadingMapModes != null && availableLensShadingMapModes.length != 0) {
                            JSONArray jsonArrayAvailableLensShadingMapModes = new JSONArray();
                            for (int i : availableLensShadingMapModes) {
                                jsonArrayAvailableLensShadingMapModes.put(getAvailableLensShadingMapModes(i));
                            }
                            cameraInfoBean.setAvailableLensShadingMapModes(jsonArrayAvailableLensShadingMapModes);
                        }
                    }

                    //TODO 本相机设备支持的OIS数据输出模式列表
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P && CameraCharacteristics.STATISTICS_INFO_AVAILABLE_OIS_DATA_MODES != null) {
                        int[] availableOisDataModes = characteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_OIS_DATA_MODES);
                        if (availableOisDataModes != null && availableOisDataModes.length != 0) {
                            JSONArray jsonArrayAvailableOisDataModes = new JSONArray();
                            for (int i : availableOisDataModes) {
                                jsonArrayAvailableOisDataModes.put(getAvailableOisDataModes(i));
                            }
                            cameraInfoBean.setAvailableOisDataModes(jsonArrayAvailableOisDataModes);
                        }
                    }


                    //TODO 同时可检测到的脸部的最大数量
                    if (CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT != null) {
                        Integer statisticsInfoMaxFaceCount = characteristics.get(CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT);
                        if (statisticsInfoMaxFaceCount != null) {
                            cameraInfoBean.setStatisticsInfoMaxFaceCount(statisticsInfoMaxFaceCount);
                        }
                    }

                    //TODO 提交请求后（与前一个请求不同）并且结果状态变为同步之前可以出现的最大帧数
                    if (CameraCharacteristics.SYNC_MAX_LATENCY != null) {
                        Integer syncMaxLatency = characteristics.get(CameraCharacteristics.SYNC_MAX_LATENCY);
                        if (syncMaxLatency != null) {
                            cameraInfoBean.setSyncMaxLatency(getSyncMaxLatency(syncMaxLatency));
                        }
                    }

                    //TODO 本相机设备支持的色调映射模式列表
                    if (CameraCharacteristics.TONEMAP_AVAILABLE_TONE_MAP_MODES != null) {
                        int[] availableToneMapModes = characteristics.get(CameraCharacteristics.TONEMAP_AVAILABLE_TONE_MAP_MODES);
                        if (availableToneMapModes != null && availableToneMapModes.length != 0) {
                            JSONArray jsonArrayAvailableToneMapModes = new JSONArray();
                            for (int i : availableToneMapModes) {
                                jsonArrayAvailableToneMapModes.put(getAvailableToneMapModes(i));
                            }
                            cameraInfoBean.setAvailableToneMapModes(jsonArrayAvailableToneMapModes);
                        }
                    }

                    //TODO 色调图曲线中可用于的最大支持点数
                    if (CameraCharacteristics.TONEMAP_MAX_CURVE_POINTS != null) {
                        Integer tonemapMaxCurvePoints = characteristics.get(CameraCharacteristics.TONEMAP_MAX_CURVE_POINTS);
                        if (tonemapMaxCurvePoints != null) {
                            cameraInfoBean.setTonemapMaxCurvePoints(tonemapMaxCurvePoints);
                        }
                    }


                    jsonArray.put(cameraInfoBean.toJSONObject());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cameraBean.setCameraInfo(jsonArray);
        return cameraBean.toJSONObject();
    }

    private static String getAvailableToneMapModes(int availableToneMapModes) {
        switch (availableToneMapModes) {
            case CaptureRequest.TONEMAP_MODE_CONTRAST_CURVE:
                return "CONTRAST_CURVE";
            case CaptureRequest.TONEMAP_MODE_FAST:
                return "FAST";
            case CaptureRequest.TONEMAP_MODE_GAMMA_VALUE:
                return "GAMMA_VALUE";
            case CaptureRequest.TONEMAP_MODE_HIGH_QUALITY:
                return "HIGH_QUALITY";
            case CaptureRequest.TONEMAP_MODE_PRESET_CURVE:
                return "PRESET_CURVE";
            default:
                return UNKNOWN + "-" + availableToneMapModes;

        }
    }


    private static String getSyncMaxLatency(int syncMaxLatency) {
        switch (syncMaxLatency) {
            case CaptureRequest.SYNC_MAX_LATENCY_UNKNOWN:
                return UNKNOWN;
            case CaptureRequest.SYNC_MAX_LATENCY_PER_FRAME_CONTROL:
                return "PER_FRAME_CONTROL";
            default:
                return UNKNOWN + "-" + syncMaxLatency;

        }
    }

    private static String getAvailableOisDataModes(int availableOisDataModes) {
        switch (availableOisDataModes) {
            case CaptureRequest.STATISTICS_OIS_DATA_MODE_ON:
                return "ON";
            case CaptureRequest.STATISTICS_OIS_DATA_MODE_OFF:
                return "OFF";
            default:
                return UNKNOWN + "-" + availableOisDataModes;

        }
    }

    private static String getAvailableLensShadingMapModes(int availableLensShadingMapModes) {
        switch (availableLensShadingMapModes) {
            case CaptureRequest.STATISTICS_LENS_SHADING_MAP_MODE_ON:
                return "ON";
            case CaptureRequest.STATISTICS_LENS_SHADING_MAP_MODE_OFF:
                return "OFF";
            default:
                return UNKNOWN + "-" + availableLensShadingMapModes;

        }
    }

    private static String getAvailableFaceDetectModes(int availableFaceDetectModes) {
        switch (availableFaceDetectModes) {
            case CaptureRequest.STATISTICS_FACE_DETECT_MODE_FULL:
                return "FULL";
            case CaptureRequest.STATISTICS_FACE_DETECT_MODE_SIMPLE:
                return "SIMPLE";
            case CaptureRequest.STATISTICS_FACE_DETECT_MODE_OFF:
                return "OFF";
            default:
                return UNKNOWN + "-" + availableFaceDetectModes;

        }
    }

    private static String getShadingAvailableModes(int shadingAvailableModes) {
        switch (shadingAvailableModes) {
            case CaptureRequest.SHADING_MODE_FAST:
                return "FAST";
            case CaptureRequest.SHADING_MODE_HIGH_QUALITY:
                return "HIGH_QUALITY";
            case CaptureRequest.SHADING_MODE_OFF:
                return "OFF";
            default:
                return UNKNOWN + "-" + shadingAvailableModes;

        }
    }


    private static String getSensorReferenceIlluminant1(int sensorReferenceIlluminant1) {
        switch (sensorReferenceIlluminant1) {
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_CLOUDY_WEATHER:
                return "CLOUDY_WEATHER";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_COOL_WHITE_FLUORESCENT:
                return "COOL_WHITE_FLUORESCENT";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_D50:
                return "D50";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_D55:
                return "D55";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_D65:
                return "D65";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_D75:
                return "D75";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_DAY_WHITE_FLUORESCENT:
                return "DAY_WHITE_FLUORESCENT";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_DAYLIGHT:
                return "DAYLIGHT";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_DAYLIGHT_FLUORESCENT:
                return "DAYLIGHT_FLUORESCENT";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_FINE_WEATHER:
                return "FINE_WEATHER";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_FLASH:
                return "FLASH";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_FLUORESCENT:
                return "FLUORESCENT";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_ISO_STUDIO_TUNGSTEN:
                return "ISO_STUDIO_TUNGSTEN";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_SHADE:
                return "SHADE";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_STANDARD_A:
                return "STANDARD_A";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_STANDARD_B:
                return "STANDARD_B";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_STANDARD_C:
                return "STANDARD_C";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_TUNGSTEN:
                return "TUNGSTEN";
            case CaptureRequest.SENSOR_REFERENCE_ILLUMINANT1_WHITE_FLUORESCENT:
                return "WHITE_FLUORESCENT";

            default:
                return UNKNOWN + "-" + sensorReferenceIlluminant1;

        }
    }

    private static String getSensorInfoTimestampSource(int sensorInfoTimestampSource) {
        switch (sensorInfoTimestampSource) {
            case CaptureRequest.SENSOR_INFO_TIMESTAMP_SOURCE_UNKNOWN:
                return "UNKNOWN";
            case CaptureRequest.SENSOR_INFO_TIMESTAMP_SOURCE_REALTIME:
                return "REALTIME";
            default:
                return UNKNOWN + "-" + sensorInfoTimestampSource;

        }
    }

    private static String getSensorInfoColorFilterArrangement(int sensorInfoColorFilterArrangement) {
        switch (sensorInfoColorFilterArrangement) {
            case CaptureRequest.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_BGGR:
                return "BGGR";
            case CaptureRequest.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_GBRG:
                return "GBRG";
            case CaptureRequest.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_GRBG:
                return "GRBG";
            case CaptureRequest.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_RGB:
                return "RGB";
            case CaptureRequest.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT_RGGB:
                return "RGGB";
            default:
                return UNKNOWN + "-" + sensorInfoColorFilterArrangement;

        }
    }

    private static String getSensorAvailableTestPatternModes(int sensorAvailableTestPatternModes) {
        switch (sensorAvailableTestPatternModes) {
            case CaptureRequest.SENSOR_TEST_PATTERN_MODE_COLOR_BARS:
                return "COLOR_BARS";
            case CaptureRequest.SENSOR_TEST_PATTERN_MODE_COLOR_BARS_FADE_TO_GRAY:
                return "COLOR_BARS_FADE_TO_GRAY";
            case CaptureRequest.SENSOR_TEST_PATTERN_MODE_CUSTOM1:
                return "CUSTOM1";
            case CaptureRequest.SENSOR_TEST_PATTERN_MODE_OFF:
                return "OFF";
            case CaptureRequest.SENSOR_TEST_PATTERN_MODE_PN9:
                return "PN9";
            case CaptureRequest.SENSOR_TEST_PATTERN_MODE_SOLID_COLOR:
                return "SOLID_COLOR";
            default:
                return UNKNOWN + "-" + sensorAvailableTestPatternModes;

        }
    }

    private static String getScalerCroppingType(int scalerCroppingType) {
        switch (scalerCroppingType) {
            case CaptureRequest.SCALER_CROPPING_TYPE_CENTER_ONLY:
                return "CENTER_ONLY";
            case CaptureRequest.SCALER_CROPPING_TYPE_FREEFORM:
                return "FREEFORM";
            default:
                return UNKNOWN + "-" + scalerCroppingType;

        }
    }

    private static String getRequestAvailableCapabilities(int requestAvailableCapabilities) {
        switch (requestAvailableCapabilities) {
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE:
                return "BACKWARD_COMPATIBLE";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_BURST_CAPTURE:
                return "BURST_CAPTURE";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_CONSTRAINED_HIGH_SPEED_VIDEO:
                return "CONSTRAINED_HIGH_SPEED_VIDEO";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_DEPTH_OUTPUT:
                return "DEPTH_OUTPUT";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_MANUAL_POST_PROCESSING:
                return "MANUAL_POST_PROCESSING";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_LOGICAL_MULTI_CAMERA:
                return "LOGICAL_MULTI_CAMERA";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_MANUAL_SENSOR:
                return "MANUAL_SENSOR";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_MONOCHROME:
                return "MONOCHROME";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_MOTION_TRACKING:
                return "MOTION_TRACKING";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_PRIVATE_REPROCESSING:
                return "PRIVATE_REPROCESSING";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_RAW:
                return "RAW";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_READ_SENSOR_SETTINGS:
                return "READ_SENSOR_SETTINGS";
            case CaptureRequest.REQUEST_AVAILABLE_CAPABILITIES_YUV_REPROCESSING:
                return "YUV_REPROCESSING";
            default:
                return UNKNOWN + "-" + requestAvailableCapabilities;

        }
    }

    private static String getAvailableNoiseReductionModes(int availableNoiseReductionModes) {
        switch (availableNoiseReductionModes) {
            case CaptureRequest.NOISE_REDUCTION_MODE_FAST:
                return "FAST";
            case CaptureRequest.NOISE_REDUCTION_MODE_HIGH_QUALITY:
                return "HIGH_QUALITY";
            case CaptureRequest.NOISE_REDUCTION_MODE_MINIMAL:
                return "MINIMAL";
            case CaptureRequest.NOISE_REDUCTION_MODE_OFF:
                return "OFF";
            case CaptureRequest.NOISE_REDUCTION_MODE_ZERO_SHUTTER_LAG:
                return "ZERO_SHUTTER_LAG";
            default:
                return UNKNOWN + "-" + availableNoiseReductionModes;

        }
    }

    private static String getCameraSensorSyncType(Integer cameraSensorSyncType) {
        if (cameraSensorSyncType == null) {
            return UNKNOWN;
        }
        switch (cameraSensorSyncType) {
            case CaptureRequest.LOGICAL_MULTI_CAMERA_SENSOR_SYNC_TYPE_APPROXIMATE:
                return "APPROXIMATE";
            case CaptureRequest.LOGICAL_MULTI_CAMERA_SENSOR_SYNC_TYPE_CALIBRATED:
                return "CALIBRATED";
            default:
                return UNKNOWN + "-" + cameraSensorSyncType;

        }
    }

    private static String getLensPoseReference(Integer lensPoseReference) {
        if (lensPoseReference == null) {
            return UNKNOWN;
        }
        switch (lensPoseReference) {
            case CaptureRequest.LENS_POSE_REFERENCE_PRIMARY_CAMERA:
                return "PRIMARY_CAMERA";
            case CaptureRequest.LENS_POSE_REFERENCE_GYROSCOPE:
                return "GYROSCOPE";
            default:
                return UNKNOWN + "-" + lensPoseReference;

        }
    }

    private static String getFocusDistanceCalibration(Integer focusDistanceCalibration) {
        if (focusDistanceCalibration == null) {
            return UNKNOWN;
        }
        switch (focusDistanceCalibration) {
            case CaptureRequest.LENS_INFO_FOCUS_DISTANCE_CALIBRATION_APPROXIMATE:
                return "APPROXIMATE";
            case CaptureRequest.LENS_INFO_FOCUS_DISTANCE_CALIBRATION_CALIBRATED:
                return "CALIBRATED";
            case CaptureRequest.LENS_INFO_FOCUS_DISTANCE_CALIBRATION_UNCALIBRATED:
                return "UNCALIBRATED";
            default:
                return UNKNOWN + "-" + focusDistanceCalibration;

        }
    }

    private static String getAvailableOpticalStabilization(int jsonArrayAvailableOpticalStabilization) {
        switch (jsonArrayAvailableOpticalStabilization) {
            case CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE_OFF:
                return "OFF";
            case CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE_ON:
                return "ON";
            default:
                return UNKNOWN + "-" + jsonArrayAvailableOpticalStabilization;

        }
    }


    private static String getAvailableHotPixelModes(int availableHotPixelModes) {
        switch (availableHotPixelModes) {
            case CaptureRequest.HOT_PIXEL_MODE_FAST:
                return "FAST";
            case CaptureRequest.HOT_PIXEL_MODE_HIGH_QUALITY:
                return "HIGH_QUALITY";
            case CaptureRequest.HOT_PIXEL_MODE_OFF:
                return "OFF";
            default:
                return UNKNOWN + "-" + availableHotPixelModes;

        }
    }

    private static String getAvailableEdgeModes(int availableEdgeModes) {
        switch (availableEdgeModes) {
            case CaptureRequest.EDGE_MODE_FAST:
                return "FAST";
            case CaptureRequest.EDGE_MODE_HIGH_QUALITY:
                return "HIGH_QUALITY";
            case CaptureRequest.EDGE_MODE_OFF:
                return "OFF";
            case CaptureRequest.EDGE_MODE_ZERO_SHUTTER_LAG:
                return "ZERO_SHUTTER_LAG";
            default:
                return UNKNOWN + "-" + availableEdgeModes;

        }
    }


    private static String getCorrectionAvailableModes(int correctionAvailableModes) {
        switch (correctionAvailableModes) {
            case CaptureRequest.DISTORTION_CORRECTION_MODE_FAST:
                return "FAST";
            case CaptureRequest.DISTORTION_CORRECTION_MODE_HIGH_QUALITY:
                return "HIGH_QUALITY";
            case CaptureRequest.DISTORTION_CORRECTION_MODE_OFF:
                return "OFF";
            default:
                return UNKNOWN + "-" + correctionAvailableModes;

        }
    }

    private static String getAwbAvailableModes(int awbAvailableModes) {
        switch (awbAvailableModes) {
            case CaptureRequest.CONTROL_AWB_MODE_AUTO:
                return "AUTO";
            case CaptureRequest.CONTROL_AWB_MODE_CLOUDY_DAYLIGHT:
                return "CLOUDY_DAYLIGHT";
            case CaptureRequest.CONTROL_AWB_MODE_DAYLIGHT:
                return "DAYLIGHT";
            case CaptureRequest.CONTROL_AWB_MODE_FLUORESCENT:
                return "FLUORESCENT";
            case CaptureRequest.CONTROL_AWB_MODE_INCANDESCENT:
                return "INCANDESCENT";
            case CaptureRequest.CONTROL_AWB_MODE_OFF:
                return "OFF";
            case CaptureRequest.CONTROL_AWB_MODE_SHADE:
                return "SHADE";
            case CaptureRequest.CONTROL_AWB_MODE_TWILIGHT:
                return "CONTROL_AWB_MODE_TWILIGHT";
            case CaptureRequest.CONTROL_AWB_MODE_WARM_FLUORESCENT:
                return "WARM_FLUORESCENT";
            default:
                return UNKNOWN + "-" + awbAvailableModes;

        }
    }


    private static String getVideoStabilizationModes(int videoStabilizationModes) {
        switch (videoStabilizationModes) {
            case CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE_OFF:
                return "OFF";
            case CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE_ON:
                return "ON";
            default:
                return UNKNOWN + "-" + videoStabilizationModes;

        }
    }

    private static String getAvailableSceneModes(int availableSceneModes) {
        switch (availableSceneModes) {
            case CaptureRequest.CONTROL_SCENE_MODE_ACTION:
                return "ACTION";
            case CaptureRequest.CONTROL_SCENE_MODE_BARCODE:
                return "BARCODE";
            case CaptureRequest.CONTROL_SCENE_MODE_BEACH:
                return "BEACH";
            case CaptureRequest.CONTROL_SCENE_MODE_CANDLELIGHT:
                return "CANDLELIGHT";
            case CaptureRequest.CONTROL_SCENE_MODE_DISABLED:
                return "DISABLED";
            case CaptureRequest.CONTROL_SCENE_MODE_FACE_PRIORITY:
                return "FACE_PRIORITY";
            case CaptureRequest.CONTROL_SCENE_MODE_FIREWORKS:
                return "FIREWORKS";
            case CaptureRequest.CONTROL_SCENE_MODE_HDR:
                return "HDR";
            case CaptureRequest.CONTROL_SCENE_MODE_LANDSCAPE:
                return "LANDSCAPE";
            case CaptureRequest.CONTROL_SCENE_MODE_NIGHT:
                return "NIGHT";
            case CaptureRequest.CONTROL_SCENE_MODE_NIGHT_PORTRAIT:
                return "NIGHT_PORTRAIT";
            case CaptureRequest.CONTROL_SCENE_MODE_PARTY:
                return "PARTY";
            case CaptureRequest.CONTROL_SCENE_MODE_PORTRAIT:
                return "PORTRAIT";
            case CaptureRequest.CONTROL_SCENE_MODE_SNOW:
                return "SNOW";
            case CaptureRequest.CONTROL_SCENE_MODE_SPORTS:
                return "SPORTS";
            case CaptureRequest.CONTROL_SCENE_MODE_STEADYPHOTO:
                return "STEADYPHOTO";
            case CaptureRequest.CONTROL_SCENE_MODE_SUNSET:
                return "SUNSET";
            case CaptureRequest.CONTROL_SCENE_MODE_THEATRE:
                return "THEATRE";
            case CaptureRequest.CONTROL_SCENE_MODE_HIGH_SPEED_VIDEO:
                return "HIGH_SPEED_VIDEO";
            default:
                return UNKNOWN + "-" + availableSceneModes;

        }
    }

    private static String getAvailableModes(int availableModes) {
        switch (availableModes) {
            case CaptureRequest.CONTROL_MODE_AUTO:
                return "AUTO";
            case CaptureRequest.CONTROL_MODE_OFF:
                return "OFF";
            case CaptureRequest.CONTROL_MODE_OFF_KEEP_STATE:
                return "OFF_KEEP_STATE";
            case CaptureRequest.CONTROL_MODE_USE_SCENE_MODE:
                return "MODE_USE_SCENE_MODE";
            default:
                return UNKNOWN + "-" + availableModes;

        }
    }

    private static String getAvailableEffects(int availableEffects) {
        switch (availableEffects) {
            case CaptureRequest.CONTROL_EFFECT_MODE_OFF:
                return "OFF";
            case CaptureRequest.CONTROL_EFFECT_MODE_AQUA:
                return "AQUA";
            case CaptureRequest.CONTROL_EFFECT_MODE_BLACKBOARD:
                return "BLACKBOARD";
            case CaptureRequest.CONTROL_EFFECT_MODE_MONO:
                return "MONO";
            case CaptureRequest.CONTROL_EFFECT_MODE_NEGATIVE:
                return "NEGATIVE";
            case CaptureRequest.CONTROL_EFFECT_MODE_POSTERIZE:
                return "POSTERIZE";
            case CaptureRequest.CONTROL_EFFECT_MODE_SEPIA:
                return "SEPIA";
            case CaptureRequest.CONTROL_EFFECT_MODE_SOLARIZE:
                return "SOLARIZE";
            case CaptureRequest.CONTROL_EFFECT_MODE_WHITEBOARD:
                return "WHITEBOARD";
            default:
                return UNKNOWN + "-" + availableEffects;

        }
    }

    private static String getAfAvailableModes(int afAvailableModes) {
        switch (afAvailableModes) {
            case CaptureRequest.CONTROL_AF_MODE_OFF:
                return "OFF";
            case CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE:
                return "CONTINUOUS_PICTURE";
            case CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_VIDEO:
                return "CONTINUOUS_VIDEO";
            case CaptureRequest.CONTROL_AF_MODE_EDOF:
                return "EDOF";
            case CaptureRequest.CONTROL_AF_MODE_MACRO:
                return "MACRO";
            case CaptureRequest.CONTROL_AF_MODE_AUTO:
                return "AUTO";

            default:
                return UNKNOWN + "-" + afAvailableModes;

        }
    }

    private static String getAeAvailableModes(int aeAvailableModes) {
        switch (aeAvailableModes) {
            case CaptureRequest.CONTROL_AE_MODE_OFF:
                return "OFF";
            case CaptureRequest.CONTROL_AE_MODE_ON:
                return "ON";
            case CaptureRequest.CONTROL_AE_MODE_ON_ALWAYS_FLASH:
                return "ON_ALWAYS_FLASH";
            case CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH:
                return "ON_AUTO_FLASH";
            case CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH_REDEYE:
                return "ON_AUTO_FLASH_REDEYE";
            case CaptureRequest.CONTROL_AE_MODE_ON_EXTERNAL_FLASH:
                return "ON_EXTERNAL_FLASH";

            default:
                return UNKNOWN + "-" + aeAvailableModes;

        }
    }

    private static String getAntiBandingModes(int antiBandingModes) {
        switch (antiBandingModes) {
            case CaptureRequest.CONTROL_AE_ANTIBANDING_MODE_50HZ:
                return "50HZ";
            case CaptureRequest.CONTROL_AE_ANTIBANDING_MODE_60HZ:
                return "60HZ";
            case CaptureRequest.CONTROL_AE_ANTIBANDING_MODE_AUTO:
                return "AUTO";
            case CaptureRequest.CONTROL_AE_ANTIBANDING_MODE_OFF:
                return "OFF";
            default:
                return UNKNOWN + "-" + antiBandingModes;

        }
    }

    private static String getAberrationModes(int aberrationModes) {
        switch (aberrationModes) {
            case CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE_FAST:
                return "FAST";
            case CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE_HIGH_QUALITY:
                return "HIGH_QUALITY";
            case CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE_OFF:
                return "OFF";
            default:
                return UNKNOWN + "-" + aberrationModes;

        }
    }

    private static String getFacing(Integer facing) {
        if (facing == null) {
            return UNKNOWN;
        }
        switch (facing) {
            case CameraCharacteristics.LENS_FACING_FRONT:
                return "FRONT";
            case CameraCharacteristics.LENS_FACING_BACK:
                return "BACK";
            case CameraCharacteristics.LENS_FACING_EXTERNAL:
                return "EXTERNAL";
            default:
                return UNKNOWN + "-" + facing;
        }
    }

    private static String getLevel(Integer level) {
        if (level == null) {
            return UNKNOWN;
        }
        switch (level) {
            case CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY:
                return "LEGACY";
            case CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_3:
                return "LEVEL_3";
            case CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_EXTERNAL:
                return "EXTERNAL";
            case CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_FULL:
                return "FULL";
            case CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED:
                return "LIMITED";
            default:
                return UNKNOWN + "-" + level;
        }
    }

    private static String getFormat(int format) {
        switch (format) {
            case ImageFormat.DEPTH16:
                return "DEPTH16";
            case ImageFormat.DEPTH_POINT_CLOUD:
                return "DEPTH_POINT_CLOUD";
            case ImageFormat.FLEX_RGBA_8888:
                return "FLEX_RGBA_8888";
            case ImageFormat.FLEX_RGB_888:
                return "FLEX_RGB_888";
            case ImageFormat.JPEG:
                return "JPEG";
            case ImageFormat.NV16:
                return "NV16";
            case ImageFormat.NV21:
                return "NV21";
            case ImageFormat.PRIVATE:
                return "PRIVATE";
            case ImageFormat.RAW10:
                return "RAW10";
            case ImageFormat.RAW12:
                return "RAW12";
            case ImageFormat.RAW_PRIVATE:
                return "RAW_PRIVATE";
            case ImageFormat.RAW_SENSOR:
                return "RAW_SENSOR";
            case ImageFormat.RGB_565:
                return "RGB_565";
            case ImageFormat.YUV_420_888:
                return "YUV_420_888";
            case ImageFormat.YUV_422_888:
                return "YUV_422_888";
            case ImageFormat.YUV_444_888:
                return "YUV_444_888";
            case ImageFormat.YUY2:
                return "YUY2";
            case ImageFormat.YV12:
                return "YV12";
            default:
                return UNKNOWN + "-" + format;
        }
    }

}
