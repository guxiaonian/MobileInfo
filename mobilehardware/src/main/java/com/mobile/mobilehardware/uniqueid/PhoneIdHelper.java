package com.mobile.mobilehardware.uniqueid;

import android.annotation.SuppressLint;
import android.os.Build;

import java.util.UUID;

/**
 * @author gunaonian
 */
public class PhoneIdHelper {
    /**
     * 返回自己生产的唯一表示
     * @return
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getUniqueID() {
        // If all else fails, if the user does have lower than API 9 (lower
        // than Gingerbread), has reset their phone or 'Secure.ANDROID_ID'
        // returns 'null', then simply the ID returned will be solely based
        // off their Android device information. This is where the collisions
        // can happen.
        // Thanks http://www.pocketmagic.net/?p=1662!
        // Try not to use DISPLAY, HOST or ID - these items could change.
        // If there are collisions, there will be overlapping data
        String m_szDevIDShort = "35" +
                (Build.BOARD.length() % 10)
                + (Build.BRAND.length() % 10)
                + (Build.CPU_ABI.length() % 10)
                + (Build.DEVICE.length() % 10)
                + (Build.MANUFACTURER.length() % 10)
                + (Build.MODEL.length() % 10)
                + (Build.PRODUCT.length() % 10);

        // Thanks to @Roman SL!
        // http://stackoverflow.com/a/4789483/950427
        // Only devices with API >= 9 have android.os.Build.SERIAL
        // http://developer.android.com/reference/android/os/Build.html#SERIAL
        // If a user upgrades software or roots their phone, there will be a duplicate entry
        String serial = "serial";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                serial=Build.getSerial();
            } catch (Exception e) {
            }
        } else {
            serial=Build.SERIAL;
        }
        // Thanks @Joe!
        // http://stackoverflow.com/a/2853253/950427
        // Finally, combine the values we have found by using the UUID class to create a unique identifier
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }
}
