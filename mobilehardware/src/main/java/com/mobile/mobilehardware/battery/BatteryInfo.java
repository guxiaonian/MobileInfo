package com.mobile.mobilehardware.battery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

import com.mobile.mobilehardware.base.BaseData;
import com.mobile.mobilehardware.utils.DoubleUtil;

import org.json.JSONObject;

/**
 * @author 谷闹年
 * @date 2018/1/5
 */
class BatteryInfo {

    private static final String TAG = BatteryInfo.class.getSimpleName();

    @SuppressLint("PrivateApi")
    private static String getBatteryCapacity(Context context) {
        Object mPowerProfile;
        double batteryCapacity = 0;
        final String powerProfileClass = "com.android.internal.os.PowerProfile";

        try {
            mPowerProfile = Class.forName(powerProfileClass)
                    .getConstructor(Context.class)
                    .newInstance(context);

            batteryCapacity = (double) Class.forName(powerProfileClass)
                    .getMethod("getBatteryCapacity")
                    .invoke(mPowerProfile);

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return batteryCapacity + "mAh";
    }

    static JSONObject getBattery(Context context) {
        BatteryBean batteryBean = new BatteryBean();
        try {
            Intent batteryStatus = context.registerReceiver(null,
                    new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            if (batteryStatus != null) {
                int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                double batteryLevel = -1;
                if (level != -1 && scale != -1) {
                    batteryLevel = DoubleUtil.divide((double) level, (double) scale);
                }
                // unknown=1, charging=2, discharging=3, not charging=4, full=5
                int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                // ac=1, usb=2, wireless=4
                int plugState = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                // unknown=1, good=2, overheat=3, dead=4, over voltage=5, unspecified failure=6, cold=7
                int health = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
                boolean present = batteryStatus.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
                String technology = batteryStatus.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
                int temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
                int voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                batteryBean.setBr(DoubleUtil.mul(batteryLevel,100d)+ "%");
                batteryBean.setStatus(batteryStatus(status));
                batteryBean.setPlugState(batteryPlugged(plugState));
                batteryBean.setHealth(batteryHealth(health));
                batteryBean.setPresent(present);
                batteryBean.setTechnology(technology);
                batteryBean.setTemperature(temperature / 10 + "℃");
                if (voltage > 1000) {
                    batteryBean.setVoltage(voltage / 1000f + "V");
                } else {
                    batteryBean.setVoltage(voltage + "V");
                }
                batteryBean.setPower(getBatteryCapacity(context));
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return batteryBean.toJSONObject();
    }

    private static String batteryHealth(int health) {
        String healthBat = BaseData.UNKNOWN_PARAM;
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_COLD:
                healthBat = "cold";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthBat = "dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthBat = "good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthBat = "overVoltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthBat = "overheat";
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                healthBat = "unknown";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthBat = "unspecified";
                break;
            default:
                break;
        }
        return healthBat;
    }

    private static String batteryStatus(int status) {
        String healthBat = BaseData.UNKNOWN_PARAM;
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                healthBat = "charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                healthBat = "disCharging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                healthBat = "full";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                healthBat = "notCharging";
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                healthBat = "unknown";
                break;
            default:
                break;
        }
        return healthBat;
    }

    private static String batteryPlugged(int status) {
        String healthBat = BaseData.UNKNOWN_PARAM;
        switch (status) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                healthBat = "ac";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                healthBat = "usb";
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                healthBat = "wireless";
                break;
            default:
                break;
        }
        return healthBat;
    }

}
