package com.mobile.mobilehardware.network;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class NetWorkBean extends BaseBean {
    private static final String TAG = NetWorkBean.class.getSimpleName();

    /**
     * 网络类型
     */
    private String type;

    /**
     * 网络是否可用
     */
    private String networkAvailable;

    /**
     * 是否开启数据流量
     */
    private String haveIntent;

    /**
     * 是否是飞行模式
     */
    private String isFlightMode;

    /**
     * NFC功能是否开启
     */
    private String isNFCEnabled;

    /**
     * 是否开启热点
     */
    private String isHotspotEnabled;

    /**
     * 热点账号
     */
    private String hotspotSSID;

    /**
     * 热点密码
     */
    private String hotspotPwd;

    /**
     * 热点加密类型
     */
    private String encryptionType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNetworkAvailable() {
        return networkAvailable;
    }

    public void setNetworkAvailable(String networkAvailable) {
        this.networkAvailable = networkAvailable;
    }

    public String getHaveIntent() {
        return haveIntent;
    }

    public void setHaveIntent(String haveIntent) {
        this.haveIntent = haveIntent;
    }

    public String getIsFlightMode() {
        return isFlightMode;
    }

    public void setIsFlightMode(String isFlightMode) {
        this.isFlightMode = isFlightMode;
    }

    public String getIsNFCEnabled() {
        return isNFCEnabled;
    }

    public void setIsNFCEnabled(String isNFCEnabled) {
        this.isNFCEnabled = isNFCEnabled;
    }

    public String getIsHotspotEnabled() {
        return isHotspotEnabled;
    }

    public void setIsHotspotEnabled(String isHotspotEnabled) {
        this.isHotspotEnabled = isHotspotEnabled;
    }

    public String getHotspotSSID() {
        return hotspotSSID;
    }

    public void setHotspotSSID(String hotspotSSID) {
        this.hotspotSSID = hotspotSSID;
    }

    public String getHotspotPwd() {
        return hotspotPwd;
    }

    public void setHotspotPwd(String hotspotPwd) {
        this.hotspotPwd = hotspotPwd;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.NetWork.TYPE, isEmpty(type));
            jsonObject.put(BaseData.NetWork.NETWORK_AVAILABLE, isEmpty(networkAvailable));
            jsonObject.put(BaseData.NetWork.HAVE_INTENT, isEmpty(haveIntent));
            jsonObject.put(BaseData.NetWork.IS_FLIGHT_MODE, isEmpty(isFlightMode));
            jsonObject.put(BaseData.NetWork.IS_NFC_ENABLED, isEmpty(isNFCEnabled));
            jsonObject.put(BaseData.NetWork.IS_HOTSPOT_ENABLED, isEmpty(isHotspotEnabled));
            jsonObject.put(BaseData.NetWork.HOTSPOT_SSID, isEmpty(hotspotSSID));
            jsonObject.put(BaseData.NetWork.HOTSPOT_PWD, isEmpty(hotspotPwd));
            jsonObject.put(BaseData.NetWork.ENCRYPTION_TYPE, isEmpty(encryptionType));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
