package com.mobile.mobilehardware.wifilist;


import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONArray;
import org.json.JSONObject;

public class WifiBean extends BaseBean {
    private boolean wifiScanStatus;
    private JSONArray wifiScanResult;
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isWifiScanStatus() {
        return wifiScanStatus;
    }

    public void setWifiScanStatus(boolean wifiScanStatus) {
        this.wifiScanStatus = wifiScanStatus;
    }

    public JSONArray getWifiScanResult() {
        return wifiScanResult;
    }

    public void setWifiScanResult(JSONArray wifiScanResult) {
        this.wifiScanResult = wifiScanResult;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put("wifiScanStatus", wifiScanStatus);
            jsonObject.put("wifiScanResult", wifiScanResult);
            jsonObject.put("time", time);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.toJSONObject();
    }

    public static class WifiResultBean extends BaseBean {
        private String SSID;
        private String BSSID;
        private String capabilities;
        private int level;

        public String getSSID() {
            return SSID;
        }

        public void setSSID(String SSID) {
            this.SSID = SSID;
        }

        public String getBSSID() {
            return BSSID;
        }

        public void setBSSID(String BSSID) {
            this.BSSID = BSSID;
        }

        public String getCapabilities() {
            return capabilities;
        }

        public void setCapabilities(String capabilities) {
            this.capabilities = capabilities;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @Override
        protected JSONObject toJSONObject() {
            try {
                jsonObject.put("SSID", isEmpty(SSID));
                jsonObject.put("BSSID", isEmpty(BSSID));
                jsonObject.put("capabilities", isEmpty(capabilities));
                jsonObject.put("level", level);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.toJSONObject();
        }
    }


}
