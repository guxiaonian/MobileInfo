package com.mobile.mobilehardware.build;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.mobile.mobilehardware.MobileNativeHelper;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 * @date 2018/3/28
 */

class BuildInfo {
    private static final String TAG = BuildInfo.class.getSimpleName();

    @SuppressLint({"HardwareIds", "MissingPermission"})
    static JSONObject getBuildInfo() {
        BuildBean buildBean = new BuildBean();
        try {
            buildBean.setBoard(Build.BOARD);
            buildBean.setBootloader(Build.BOOTLOADER);
            buildBean.setBrand(Build.BRAND);
            buildBean.setDevice(Build.DEVICE);
            buildBean.setDisplay(Build.DISPLAY);
            buildBean.setFingerprint(Build.FINGERPRINT);
            buildBean.setHardware(Build.HARDWARE);
            buildBean.setHost(Build.HOST);
            buildBean.setId(Build.ID);
            buildBean.setManufacturer(Build.MANUFACTURER);
            buildBean.setModel(Build.MODEL);
            buildBean.setProduct(Build.PRODUCT);
            buildBean.setRadio(Build.getRadioVersion());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                try {
                    buildBean.setSerial(Build.getSerial());
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            } else {
                buildBean.setSerial(Build.SERIAL);
            }
            buildBean.setTags(Build.TAGS);
            buildBean.setTime(Build.TIME);
            buildBean.setType(Build.TYPE);
            buildBean.setUser(Build.USER);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                buildBean.setOsVersion(Build.VERSION.BASE_OS);
                buildBean.setPreviewSdkInt(Build.VERSION.PREVIEW_SDK_INT);
                buildBean.setSecurityPatch(Build.VERSION.SECURITY_PATCH);
            }
            buildBean.setReleaseVersion(Build.VERSION.RELEASE);
            buildBean.setIncremental(Build.VERSION.INCREMENTAL);
            buildBean.setSdkInt(Build.VERSION.SDK_INT);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return putNativeJson(buildBean.toJSONObject());
    }

    private static JSONObject putNativeJson(JSONObject jsonObject) {
        try {
            putJson(jsonObject, "dhcp.wlan0.dns1",true);
            putJson(jsonObject, "dhcp.wlan0.gateway",true);
            putJson(jsonObject, "dhcp.wlan0.ipaddress",true);
            putJson(jsonObject, "dhcp.wlan0.server",true);
            putJson(jsonObject, "wifi.interface",true);
            putJson(jsonObject, "gsm.sim.state",true);
            putJson(jsonObject, "gsm.version.baseband",false);
            putJson(jsonObject, "gsm.version.ril-impl",true);
            putJson(jsonObject, "net.hostname",true);
            putJson(jsonObject, "ro.board.platform",true);
            putJson(jsonObject, "ro.build.characteristics",true);
            putJson(jsonObject, "ro.build.date",true);
            putJson(jsonObject, "ro.build.description",false);
            putJson(jsonObject, "ro.boot.serialno",true);
            putJson(jsonObject, "ro.opengles.version",true);
            putJson(jsonObject, "ro.product.cpu.abi",true);
            putJson(jsonObject, "gsm.current.phone-type",true);
            putJson(jsonObject, "gsm.network.type",true);
            putJson(jsonObject, "ro.com.google.clientidbase",true);
            putJson(jsonObject, "gsm.operator.isroaming",true);
            putJson(jsonObject, "ro.adb.secure",true);
            putJson(jsonObject, "ro.carrier",true);
            putJson(jsonObject, "ro.secure",true);
            putJson(jsonObject, "ro.debuggable",true);
            putJson(jsonObject, "rild.libargs",true);
            putJson(jsonObject, "persist.sys.timezone",true);
            putJson(jsonObject, "persist.sys.country",true);
            putJson(jsonObject, "gsm.operator.numeric",true);
            putJson(jsonObject, "wlan.driver.status",true);
            putJson(jsonObject, "ro.runtime.firstboot",true);
            putJson(jsonObject, "ril.iccid.sim1",true);
            putJson(jsonObject, "ril.iccid.sim2",true);
            putJson(jsonObject, "gsm.serial",true);
            putJson(jsonObject, "init.svc.adbd",true);
            putJson(jsonObject, "ro.serialno",true);
            putJson(jsonObject, "persist.radio.data.iccido",true);
            putJson(jsonObject, "persist.radio.cfu.iccid.0",true);
            putJson(jsonObject, "ro.mediatek.project.path",true);
            putJson(jsonObject, "ril.subscription.types",true);
            putJson(jsonObject, "ro.mediatek.version.release",true);
            putJson(jsonObject, "ro.nfc.port",true);
            putJson(jsonObject, "keyguard.no_require_sim",true);
            putJson(jsonObject, "ro.vendor.extension_library",true);
            putJson(jsonObject, "persist.sys.updater.imei",true);
            putJson(jsonObject, "ro.boot.securebootkeyhash",false);
            putJson(jsonObject, "persist.service.bdroid.bdaddr",true);
            putJson(jsonObject, "wlan.driver.macaddr",true);
            putJson(jsonObject, "ro.bt.bdaddr_path",true);
        } catch (Exception e) {
            //ignore
        }
        return jsonObject;
    }

    private static void putJson(JSONObject jsonObject, String key,boolean is64) throws Exception {

        String result=is64? MobileNativeHelper.getBuildInfo64(key):MobileNativeHelper.getBuildInfo256(key);
        jsonObject.put(key, TextUtils.isEmpty(result) ? BaseData.UNKNOWN_PARAM : result);
    }
}
