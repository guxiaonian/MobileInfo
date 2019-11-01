package com.mobile.mobilehardware.signal;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.mobile.mobilehardware.base.BaseData;
import com.mobile.mobilehardware.utils.DataUtil;

import org.json.JSONObject;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * @author 谷闹年
 * @date 2018/1/3
 */
class SignalInfo {

    private static final String WIFI = "WIFI";
    private static final String TAG = SignalInfo.class.getSimpleName();


    /**
     * 信号强度获取
     *
     * @param context
     * @return
     */
    static JSONObject getNetRssi(Context context) {
        SignalBean signalBean = new SignalBean();
        try {
            String netWorkType = DataUtil.networkTypeALL(context);
            signalBean.setType(netWorkType);
            if (WIFI.equals(netWorkType)) {
                getDetailsWifiInfo(context, signalBean);
            } else {
                getMobileDbm(context, signalBean);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return signalBean.toJSONObject();


    }

    /**
     * mobile
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    private static void getMobileDbm(Context context, SignalBean signalBean) {
        int dbm = -1;
        int level = 0;
        try {
            signalBean.setnIpAddress(getNetIPV4());
            signalBean.setnIpAddressIpv6(getNetIP());
            signalBean.setMacAddress(getMac(context));
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            List<CellInfo> cellInfoList;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                if (tm == null) {
                    return;
                }
                cellInfoList = tm.getAllCellInfo();
                if (null != cellInfoList) {
                    for (CellInfo cellInfo : cellInfoList) {
                        if (cellInfo instanceof CellInfoGsm) {
                            CellSignalStrengthGsm cellSignalStrengthGsm = ((CellInfoGsm) cellInfo).getCellSignalStrength();
                            dbm = cellSignalStrengthGsm.getDbm();
                            level = cellSignalStrengthGsm.getLevel();
                        } else if (cellInfo instanceof CellInfoCdma) {
                            CellSignalStrengthCdma cellSignalStrengthCdma =
                                    ((CellInfoCdma) cellInfo).getCellSignalStrength();
                            dbm = cellSignalStrengthCdma.getDbm();
                            level = cellSignalStrengthCdma.getLevel();
                        } else if (cellInfo instanceof CellInfoLte) {
                            CellSignalStrengthLte cellSignalStrengthLte = ((CellInfoLte) cellInfo).getCellSignalStrength();
                            dbm = cellSignalStrengthLte.getDbm();
                            level = cellSignalStrengthLte.getLevel();

                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                            if (cellInfo instanceof CellInfoWcdma) {
                                CellSignalStrengthWcdma cellSignalStrengthWcdma =
                                        ((CellInfoWcdma) cellInfo).getCellSignalStrength();
                                dbm = cellSignalStrengthWcdma.getDbm();
                                level = cellSignalStrengthWcdma.getLevel();
                            }
                        }
                    }
                }
            }
            signalBean.setRssi(dbm );
            signalBean.setLevel(level);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }

    private static String getNetIPV4() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Log.i(TAG, e.toString());
        }
        return null;
    }

    private static String getNetIP() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet6Address && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Log.i(TAG, e.toString());
        }
        return null;
    }

    /**
     * Anything worse than or equal to this will show 0 bars.
     */
    private static final int MIN_RSSI = -100;

    /**
     * Anything better than or equal to this will show the max bars.
     */
    private static final int MAX_RSSI = -55;

    private static int calculateSignalLevel(int rssi) {

        if (rssi <= MIN_RSSI) {
            return 0;
        } else if (rssi >= MAX_RSSI) {
            return 4;
        } else {
            float inputRange = (MAX_RSSI - MIN_RSSI);
            float outputRange = (4);
            return (int) ((float) (rssi - MIN_RSSI) * outputRange / inputRange);
        }
    }

    /**
     * 获取WifiInfo
     *
     * @param mContext
     * @return
     */
    @SuppressLint("MissingPermission")
    private static WifiInfo getWifiInfo(Context mContext) {
        WifiManager mWifiManager = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (mWifiManager != null) {
            return mWifiManager.getConnectionInfo();
        }
        return null;
    }

    /**
     * 是否使用代理上网
     *
     * @param context
     * @return
     */
    private static void isWifiProxy(Context context, SignalBean signalBean) {
// 是否大于等于4.0
        final boolean isIcsOrLater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        String proxyAddress;
        int proxyPort;
        if (isIcsOrLater) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portStr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
        } else {
            proxyAddress = android.net.Proxy.getHost(context);
            proxyPort = android.net.Proxy.getPort(context);
        }
        if ((!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1)) {
            signalBean.setProxy(true);
            signalBean.setProxyAddress(proxyAddress);
            signalBean.setProxyPort(proxyPort + "");
        } else {
            signalBean.setProxy(false);
        }


    }

    /**
     * wifi
     *
     * @param mContext
     * @return
     */
    private static void getDetailsWifiInfo(Context mContext, SignalBean signalBean) {
        try {
            WifiInfo mWifiInfo = getWifiInfo(mContext);
            int ip = mWifiInfo.getIpAddress();
            String strIp = "" + (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);
            signalBean.setBssid(mWifiInfo.getBSSID());
            signalBean.setSsid(mWifiInfo.getSSID().replace("\"", ""));
            signalBean.setnIpAddress(strIp);
            signalBean.setMacAddress(getMac(mContext));
            signalBean.setNetworkId(mWifiInfo.getNetworkId());
            signalBean.setLinkSpeed(mWifiInfo.getLinkSpeed() + "Mbps");
            int rssi = mWifiInfo.getRssi();
            signalBean.setRssi(rssi);
            signalBean.setLevel(calculateSignalLevel(rssi));
            isWifiProxy(mContext, signalBean);
            signalBean.setSupplicantState(mWifiInfo.getSupplicantState().name());
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }

    }


    /**
     * >=22的sdk则进行如下算法 mac
     *
     * @return
     */
    private static String getMacForBuild() {
        try {
            for (
                    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                    networkInterfaces.hasMoreElements(); ) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if ("wlan0".equals(networkInterface.getName())) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null || hardwareAddress.length == 0) {
                        continue;
                    }
                    StringBuilder buf = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        buf.append(String.format("%02X:", b));
                    }
                    if (buf.length() > 0) {
                        buf.deleteCharAt(buf.length() - 1);
                    }
                    return buf.toString();
                }
            }
        } catch (SocketException e) {
            Log.i(TAG, e.toString());
        }
        return BaseData.UNKNOWN_PARAM;
    }


    /**
     * get macAddress
     *
     * @param mContext
     * @return
     */
    @SuppressLint("HardwareIds")
    public static String getMac(Context mContext) {
        if (Build.VERSION.SDK_INT >= 23) {
            return getMacForBuild();
        } else {
            try {
                return getWifiInfo(mContext).getMacAddress();
            } catch (Exception e) {
                return BaseData.UNKNOWN_PARAM;
            }

        }
    }
}
