package com.mobile.mobilehardware.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Created by gunaonian on 2018/3/28.
 */

public class MobNetWorkUtils {
    private static final String TAG = "MobNetWorkUtils";

    public static JSONObject mobGetMobNetWork(Context context) {
        JSONObject jsonObject = new JSONObject();
        getNetWork(context, jsonObject);
        return jsonObject;
    }


    private static void getNetWork(Context context, JSONObject jsonObject) {
        try {
            jsonObject.put("type", MobDataUtils.networkTypeALL(context));
            jsonObject.put("networkAvailable", MobDataUtils.isNetworkAvailable(context) + "");
            jsonObject.put("haveIntent", MobDataUtils.haveIntent(context) + "");
            jsonObject.put("isFlightMode", getAirplaneMode(context) + "");
            jsonObject.put("isNFCEnabled", hasNfc(context) + "");
            jsonObject.put("isHotspotEnabled", isWifiApEnabled(context) + "");
            getHotPotParams(context, jsonObject);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
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
//            readArp(jsonObject);
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

    private static void readArp(JSONObject jsonObject) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("/proc/net/arp"));
            String line;
            String ip;
            String flag;
            String mac;

            while ((line = br.readLine()) != null) {
                try {
                    line = line.trim();
                    if (line.length() < 63) continue;
                    if (line.toUpperCase(Locale.US).contains("IP")) continue;
                    ip = line.substring(0, 17).trim();
                    flag = line.substring(29, 32).trim();
                    mac = line.substring(41, 63).trim();
                    if (mac.contains("00:00:00:00:00:00")) continue;
//                    Log.e("scanner", "readArp: mac= "+mac+" ; ip= "+ip+" ;flag= "+flag);
                    map.put("ip", ip);
                    map.put("mac", mac);
                    list.add(map);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            br.close();
            jsonObject.put("hotspotData",new JSONArray(list));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
