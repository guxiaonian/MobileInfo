package com.mobile.mobilehardware.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by 谷闹年 on 2018/1/3.
 */
public class MobRssiUtils {
    private static final String WIFI = "WIFI";
    private static final String TAG = "MobRssiUtils";


    /**
     * 信号强度获取
     *
     * @param context
     * @return
     */
    public static JSONObject mobGetNetRssi(Context context) {
        if (MobDataUtils.isNetworkAvailable(context)) {
            String netWorkType = MobDataUtils.networkTypeALL(context);
            if (WIFI.equals(netWorkType)) {
                return getDetailsWifiInfo(context);
            } else {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return null;
                } else {
                    return getMobileDbm(context, netWorkType);
                }
            }
        } else {
            return null;
        }

    }

    /**
     * mobile
     *
     * @param context
     * @param type
     * @return
     */
    private static JSONObject getMobileDbm(Context context, String type) {
        int dbm = -1;
        int level = 0;
        JSONObject jsonObject = new JSONObject();
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            List<CellInfo> cellInfoList;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return null;
                }
                if (tm == null) {
                    return null;
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
            jsonObject.put("type", type);
            jsonObject.put("BSSID", "$unknown");
            jsonObject.put("SSID", "$unknown");
            jsonObject.put("nIpAddress", getNetIPV4());
            jsonObject.put("nIpAddressIpv6", getNetIP());
            jsonObject.put("MacAddress", MobDataUtils.getMac(context));
            jsonObject.put("NetworkId", "$unknown");
            jsonObject.put("LinkSpeed", "$unknown");
            jsonObject.put("Rssi", dbm + "");
            jsonObject.put("level", level + "");
            isWifiProxy(context, jsonObject);
            jsonObject.put("SupplicantState", "$unknown");
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return jsonObject;
    }

    private static String getNetIPV4() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
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
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet6Address) {
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
    static WifiInfo getWifiInfo(Context mContext) {
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
    private static void isWifiProxy(Context context, JSONObject jsonObject) {
// 是否大于等于4.0
        JSONObject jsonObject1 = new JSONObject();
        final boolean IS_ICS_OR_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        String proxyAddress;
        int proxyPort;
        if (IS_ICS_OR_LATER) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portStr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
        } else {
            proxyAddress = android.net.Proxy.getHost(context);
            proxyPort = android.net.Proxy.getPort(context);
        }
        try {
            if ((!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1)) {
                jsonObject.put("proxy", "true");
                jsonObject1.put("proxyAddress", proxyAddress);
                jsonObject1.put("proxyPort", proxyPort + "");
                jsonObject.put("proxyData", jsonObject1);
            } else {
                jsonObject.put("proxy", "false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * wifi
     *
     * @param mContext
     * @return
     */
    private static JSONObject getDetailsWifiInfo(Context mContext) {
        JSONObject jsonObject = new JSONObject();
        try {
            WifiInfo mWifiInfo = getWifiInfo(mContext);
            int ip = mWifiInfo.getIpAddress();
            String strIp = "" + (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 24) & 0xFF);
            jsonObject.put("type", "WIFI");
            jsonObject.put("BSSID", mWifiInfo.getBSSID());
            jsonObject.put("SSID", mWifiInfo.getSSID().replace("\"", ""));
            jsonObject.put("nIpAddress", strIp);
            jsonObject.put("MacAddress", MobDataUtils.getMac(mContext));
            jsonObject.put("NetworkId", mWifiInfo.getNetworkId() + "");
            jsonObject.put("LinkSpeed", mWifiInfo.getLinkSpeed() + "Mbps");
            int rssi = mWifiInfo.getRssi();
            jsonObject.put("Rssi", rssi + "");
            jsonObject.put("level", calculateSignalLevel(rssi) + "");
            isWifiProxy(mContext, jsonObject);
            jsonObject.put("SupplicantState", mWifiInfo.getSupplicantState());
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return jsonObject;

    }


}
