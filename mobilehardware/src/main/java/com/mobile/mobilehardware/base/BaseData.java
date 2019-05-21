package com.mobile.mobilehardware.base;

/**
 * @author gunaonian
 */
public class BaseData {
    public static final String UNKNOWN_PARAM = "$unknown";


    public static class Aduio {
        public static final String MAX_VOICE_CALL = "maxVoiceCall";
        public static final String CURRENT_VOICE_CALL = "currentVoiceCall";
        public static final String MAX_SYSTEM = "maxSystem";
        public static final String CURRENT_SYSTEM = "currentSystem";
        public static final String MAX_RING = "maxRing";
        public static final String CURRENT_RING = "currentRing";
        public static final String MAX_MUSIC = "maxMusic";
        public static final String CURRENT_MUSIC = "currentMusic";
        public static final String MAX_ALARM = "maxAlarm";
        public static final String CURRENT_ALARM = "currentAlarm";
    }

    public static class Battery {
        public static final String BR = "br";
        public static final String STATUS = "status";
        public static final String PLUG_STATE = "plugState";
        public static final String HEALTH = "health";
        public static final String PRESENT = "present";
        public static final String TECHNOLOGY = "technology";
        public static final String TEMPERATURE = "temperature";
        public static final String VOLTAGE = "voltage";
        public static final String POWER = "power";
    }

    public static class AppList {
        public static final String PACKAGE_NAME = "packageName";
        public static final String VERSION_NAME = "versionName";
        public static final String IS_SYSTEM = "isSystem";
        public static final String VERSION_CODE = "versionCode";
    }

    public static class Debug {
        public static final String IS_OPEN_DEBUG = "isOpenDebug";
        public static final String IS_DEBUG_VERSION = "isDebugVersion";
        public static final String IS_DEBUGGING = "isDebugging";
        public static final String IS_READ_PROC_STATUS = "isReadProcStatus";
        public static final String IS_ALLOW_MOCK_LOCATION = "isAllowMockLocation";
    }

    public static class MoreOpen {
        public static final String CHECK_BY_PRIVATE_FILE_PATH = "checkByPrivateFilePath";
        public static final String CHECK_BY_MULTIAPK_PACKAGE_NAME = "checkByMultiApkPackageName";
        public static final String CHECK_BY_HAS_SAMEUID = "checkByHasSameUid";
    }

    public static class Hook {
        public static final String IS_HAVE_XPOSED = "isHaveXposed";
        public static final String IS_HAVE_SUBSTRATE = "isHaveSubstrate";
        public static final String IS_HAVE_FRIDA = "isHaveFrida";
    }

    public static class Xposed {
        public static final String XPOSED_APP = "xposedApp";
        public static final String XPOSED_IMEI = "xposedImei";
        public static final String XPOSED_SERIAL = "xposedSerial";
        public static final String XPOSED_SSID = "xposedSsid";
        public static final String XPOSED_MAC = "xposedMac";
        public static final String XPOSED_ADDRESS = "xposedAddress";
        public static final String XPOSED_ANDROIDID = "xposedAndroidId";
        public static final String XPOSED_IMSI = "xposedImsi";
        public static final String XPOSED_LATITUDE = "xposedLatitude";
        public static final String XPOSED_LONGITUDE = "xposedLongitude";
    }

    public static class Band {
        public static final String BASE_BAND = "baseBand";
        public static final String INNER_BAND = "innerBand";
        public static final String LINUX_BAND = "linuxBand";
    }

    public static class Local {
        public static final String COUNTRY = "country";
        public static final String LANGUAGE = "language";
    }

    public static class Bluetooth {
        public static final String BLUETOOTH_ADDRESS = "bluetoothAddress";
        public static final String IS_ENABLED = "isEnabled";
        public static final String DEVICE = "device";
        public static final String PHONE_NAME = "phoneName";

        public static class Device {
            public static String ADDRESS = "address";
            public static String NAME = "name";
        }
    }

    public static class Settings {
        public static final String ANDROID_ID = "androidId";
    }

    public static class Screen {
        public static final String DENSITY_SCALE = "densityScale";
        public static final String DENSITY_DPI = "densityDpi";
        public static final String WIDTH = "width";
        public static final String HEIGHT = "height";
        public static final String IS_SCREEN_AUTO = "isScreenAuto";
        public static final String SCREEN_BRIGHTNESS = "screenBrightness";
        public static final String IS_SCREEN_AUTO_CHANGE = "isScreenAutoChange";
    }

    public static class Cpu{
        public static final String CPU_NAME="cpuName";
        public static final String CPU_FREQ="cpuFreq";
        public static final String CPU_MAX_FREQ="cpuMaxFreq";
        public static final String CPU_MIN_FREQ="cpuMinFreq";
        public static final String CPU_HARDWARE="cpuHardware";
        public static final String CPU_CORES="cpuCores";
        public static final String CPU_TEMP="cpuTemp";
        public static final String CPU_ABI="cpuAbi";
    }
}