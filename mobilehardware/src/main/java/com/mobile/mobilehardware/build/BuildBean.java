package com.mobile.mobilehardware.build;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class BuildBean extends BaseBean {
    private static final String TAG = BuildBean.class.getSimpleName();

    /**
     * 主板名称
     */
    private String board;

    /**
     * 系统引导程序版本号
     */
    private String bootloader;

    /**
     * 系统定制商
     */
    private String brand;

    /**
     * 设备参数
     */
    private String device;

    /**
     * 显示屏参数
     */
    private String display;

    /**
     * 硬件名
     */
    private String fingerprint;

    /**
     * 内核命令行中的硬件名
     */
    private String hardware;

    /**
     * host
     */
    private String host;

    /**
     * 标签
     */
    private String id;

    /**
     * 硬件厂商
     */
    private String manufacturer;

    /**
     * 版本
     */
    private String model;

    /**
     * 手机厂商
     */
    private String product;

    /**
     * 返回无线电固件的版本字符串
     */
    private String radio;

    /**
     * 获取硬件序列号
     */
    private String serial;

    /**
     * 描述Build的标签
     */
    private String tags;

    /**
     * time
     */
    private long time;

    /**
     * type
     */
    private String type;

    /**
     * user
     */
    private String user;

    /**
     * os版本
     */
    private String osVersion;

    /**
     * 版本
     */
    private String releaseVersion;

    /**
     * 当前开发代码名称
     */
    private String codeName;

    /**
     * 基础源代码控件用于表示此构建的内部值
     */
    private String incremental;

    /**
     * SDK的版本
     */
    private int sdkInt;

    /**
     * SDK的预览版本
     */
    private int previewSdkInt;

    /**
     * 用户可见的安全补丁程序级别
     */
    private String securityPatch;


    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBootloader() {
        return bootloader;
    }

    public void setBootloader(String bootloader) {
        this.bootloader = bootloader;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getIncremental() {
        return incremental;
    }

    public void setIncremental(String incremental) {
        this.incremental = incremental;
    }

    public int getSdkInt() {
        return sdkInt;
    }

    public void setSdkInt(int sdkInt) {
        this.sdkInt = sdkInt;
    }

    public int getPreviewSdkInt() {
        return previewSdkInt;
    }

    public void setPreviewSdkInt(int previewSdkInt) {
        this.previewSdkInt = previewSdkInt;
    }

    public String getSecurityPatch() {
        return securityPatch;
    }

    public void setSecurityPatch(String securityPatch) {
        this.securityPatch = securityPatch;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Build.BOARD, isEmpty(board));
            jsonObject.put(BaseData.Build.BOOTLOADER, isEmpty(bootloader));
            jsonObject.put(BaseData.Build.BRAND, isEmpty(brand));
            jsonObject.put(BaseData.Build.DEVICE, isEmpty(device));
            jsonObject.put(BaseData.Build.DISPLAY, isEmpty(display));
            jsonObject.put(BaseData.Build.FINGERPRINT, isEmpty(fingerprint));
            jsonObject.put(BaseData.Build.HARDWARE, isEmpty(hardware));
            jsonObject.put(BaseData.Build.HOST, isEmpty(host));
            jsonObject.put(BaseData.Build.ID, isEmpty(id));
            jsonObject.put(BaseData.Build.MANUFACTURER, isEmpty(manufacturer));
            jsonObject.put(BaseData.Build.MODEL, isEmpty(model));
            jsonObject.put(BaseData.Build.PRODUCT, isEmpty(product));
            jsonObject.put(BaseData.Build.RADIO, isEmpty(radio));
            jsonObject.put(BaseData.Build.SERIAL, isEmpty(serial));
            jsonObject.put(BaseData.Build.TAGS, isEmpty(tags));
            jsonObject.put(BaseData.Build.TIME, time);
            jsonObject.put(BaseData.Build.TYPE, isEmpty(type));
            jsonObject.put(BaseData.Build.USER, isEmpty(user));
            jsonObject.put(BaseData.Build.OS_VERSION, isEmpty(osVersion));
            jsonObject.put(BaseData.Build.RELEASE_VERSION, isEmpty(releaseVersion));
            jsonObject.put(BaseData.Build.CODE_NAME, isEmpty(codeName));
            jsonObject.put(BaseData.Build.INCREMENTAL, isEmpty(incremental));
            jsonObject.put(BaseData.Build.SDK_INT, sdkInt);
            jsonObject.put(BaseData.Build.PREVIEW_SDK_INT, previewSdkInt);
            jsonObject.put(BaseData.Build.SECURITY_PATCH, isEmpty(securityPatch));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }

}
