package com.mobile.mobilehardware.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.provider.Settings;
import android.util.Log;


import com.mobile.mobilehardware.utils.MobDataUtils;

import org.json.JSONObject;


import java.lang.reflect.Method;


/**
 * @author gunaonian
 * @date 2018/3/28
 */

class NetWorkInfo {
    private static final String TAG = NetWorkInfo.class.getSimpleName();

    static JSONObject getMobNetWork(Context context) {
        NetWorkBean netWorkBean = new NetWorkBean();
        try {
            netWorkBean.setType(MobDataUtils.networkTypeALL(context));
            netWorkBean.setNetworkAvailable(MobDataUtils.isNetworkAvailable(context) + "");
            netWorkBean.setHaveIntent(MobDataUtils.haveIntent(context) + "");
            netWorkBean.setIsFlightMode(getAirplaneMode(context) + "");
            netWorkBean.setIsNFCEnabled(hasNfc(context) + "");
            netWorkBean.setIsHotspotEnabled(isWifiApEnabled(context) + "");
            WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (mWifiManager == null) {
                return netWorkBean.toJSONObject();
            }
            WifiConfiguration config = getHotPotConfig(mWifiManager);
            if (config == null) {
                return netWorkBean.toJSONObject();
            }
            netWorkBean.setHotspotSSID(config.SSID);
            netWorkBean.setHotspotPwd(config.preSharedKey);
            netWorkBean.setEncryptionType(config.allowedKeyManagement.get(4) ? "WPA2_PSK" : "NONE");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return netWorkBean.toJSONObject();
    }


    private static boolean getAirplaneMode(Context context) {
        int isAirplaneMode = Settings.System.getInt(context.getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, 0);
        return isAirplaneMode == 1;
    }

    private static boolean hasNfc(Context context) {
        boolean bRet = false;
        if (context == null) {
            return false;
        }
        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        if (manager == null) {
            return false;
        }
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter != null && adapter.isEnabled()) {
            // adapter存在，能启用
            bRet = true;
        }
        return bRet;
    }

    /**
     * 热点开关是否打开
     */
    private static boolean isWifiApEnabled(Context context) {
        try {
            WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            Method method = mWifiManager.getClass().getMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (Boolean) method.invoke(mWifiManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取当前热点的配置参数
     *
     * @return
     */
    private static void getHotPotParams(Context context, JSONObject jsonObject) {

        try {
            WifiManager mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (mWifiManager == null) {
                return;
            }
            WifiConfiguration config = getHotPotConfig(mWifiManager);
            if (config == null) {
                return;
            }
            if (config.SSID != null && !"".equals(config.SSID)) {
                jsonObject.put("hotspotSSID", config.SSID);
            }
            if (config.preSharedKey != null && !"".equals(config.preSharedKey)) {
                jsonObject.put("hotspotPwd", config.preSharedKey);
            }
            jsonObject.put("encryptionType", config.allowedKeyManagement.get(4) ? "WPA2_PSK" : "NONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取当前热点配置
     *
     * @return
     */
    private static WifiConfiguration getHotPotConfig(WifiManager mWifiManager) {
        try {
            @SuppressLint("PrivateApi") Method method = WifiManager.class.getDeclaredMethod("getWifiApConfiguration");
            method.setAccessible(true);
            return (WifiConfiguration) method.invoke(mWifiManager);
        } catch (Exception e) {
            return null;
        }

    }

}
