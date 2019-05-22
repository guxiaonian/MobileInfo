package com.mobile.mobilehardware.signal;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class SignalBean extends BaseBean {
    private static final String TAG = SignalBean.class.getSimpleName();

    /**
     * 网络类型
     */
    private String type;

    /**
     * bssid
     */
    private String bssid;

    /**
     * ssid
     */
    private String ssid;

    /**
     * ipv4
     */
    private String nIpAddress;

    /**
     * ipv6
     */
    private String nIpAddressIpv6;

    /**
     * mac地址
     */
    private String macAddress;

    /**
     * 网络id
     */
    private String networkId;

    /**
     * 网络速度
     */
    private String linkSpeed;

    /**
     * 信号强度
     */
    private String rssi;

    /**
     * 信号等级
     */
    private String level;

    /**
     * 连接状态
     */
    private String supplicantState;

    /**
     * 是否开启代理
     */
    private String proxy;

    /**
     * 代理地址
     */
    private String proxyAddress;

    /**
     * 代理端口号
     */
    private String proxyPort;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getnIpAddress() {
        return nIpAddress;
    }

    public void setnIpAddress(String nIpAddress) {
        this.nIpAddress = nIpAddress;
    }

    public String getnIpAddressIpv6() {
        return nIpAddressIpv6;
    }

    public void setnIpAddressIpv6(String nIpAddressIpv6) {
        this.nIpAddressIpv6 = nIpAddressIpv6;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getLinkSpeed() {
        return linkSpeed;
    }

    public void setLinkSpeed(String linkSpeed) {
        this.linkSpeed = linkSpeed;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSupplicantState() {
        return supplicantState;
    }

    public void setSupplicantState(String supplicantState) {
        this.supplicantState = supplicantState;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public void setProxyAddress(String proxyAddress) {
        this.proxyAddress = proxyAddress;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Signal.TYPE, isEmpty(type));
            jsonObject.put(BaseData.Signal.BSSID, isEmpty(bssid));
            jsonObject.put(BaseData.Signal.SSID, isEmpty(ssid));
            jsonObject.put(BaseData.Signal.N_IP_ADDRESS, isEmpty(nIpAddress));
            jsonObject.put(BaseData.Signal.N_IP_ADDRESS_IPV6, isEmpty(nIpAddressIpv6));
            jsonObject.put(BaseData.Signal.MAC_ADDRESS, isEmpty(macAddress));
            jsonObject.put(BaseData.Signal.NETWORK_ID, isEmpty(networkId));
            jsonObject.put(BaseData.Signal.LINK_SPEED, isEmpty(linkSpeed));
            jsonObject.put(BaseData.Signal.RSSI, isEmpty(rssi));
            jsonObject.put(BaseData.Signal.LEVEL, isEmpty(level));
            jsonObject.put(BaseData.Signal.SUPPLICANT_STATE, isEmpty(supplicantState));
            jsonObject.put(BaseData.Signal.PROXY, isEmpty(proxy));
            jsonObject.put(BaseData.Signal.PROXY_ADDRESS, isEmpty(proxyAddress));
            jsonObject.put(BaseData.Signal.PROXY_PORT, isEmpty(proxyPort));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
