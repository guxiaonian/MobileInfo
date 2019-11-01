package com.mobile.mobilehardware.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.mobile.mobilehardware.base.BaseData;


/**
 * @author 谷闹年
 * @date 2018/1/3
 */
public class DataUtil {
    private static final String TAG = "DataUtil";

    /**
     * 拿到具体的网络类型
     *
     * @param context
     * @return
     */
    public static String networkTypeALL(Context context) {

        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                return "WIFI";
            }
        }

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return "unknown";
        }
        int networkType = telephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "3G";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "4G";
            default:
                return BaseData.UNKNOWN_PARAM;

        }
    }

    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager mgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mgr == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Network[] networks = mgr.getAllNetworks();
                NetworkInfo networkInfo;
                for (Network mNetwork : networks) {
                    networkInfo = mgr.getNetworkInfo(mNetwork);
                    if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        return true;
                    }
                }
            } else {
                NetworkInfo[] info = mgr.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

}
