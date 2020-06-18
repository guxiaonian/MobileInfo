package com.mobile.mobilehardware.vpn;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;

import com.mobile.mobilehardware.MobileHardWareHelper;

/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class VpnHelper {
    private static final String TAG = VpnHelper.class.getSimpleName();

    @SuppressLint("MissingPermission")
    public static boolean getVpnData() {
        try {
            ConnectivityManager cm = (ConnectivityManager) MobileHardWareHelper.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null) {
                return false;
            }
            return cm.getNetworkInfo(ConnectivityManager.TYPE_VPN).isConnectedOrConnecting();
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
    }

}
