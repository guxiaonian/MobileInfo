package com.mobile.mobilehardware.base;

/**
 * @author gunaonian
 */
public class BaseData {
    public static final String UNKNOWN_PARAM = "$unknown";


    public static class Aduio {
        public static final String MAX_VOICE_CALL = "maxVoiceCall";
        public static final String MIN_VOICE_CALL = "minVoiceCall";
        public static final String CURRENT_VOICE_CALL = "currentVoiceCall";
        public static final String MAX_SYSTEM = "maxSystem";
        public static final String MIN_SYSTEM = "minSystem";
        public static final String CURRENT_SYSTEM = "currentSystem";
        public static final String MAX_RING = "maxRing";
        public static final String MIN_RING = "minRing";
        public static final String CURRENT_RING = "currentRing";
        public static final String MAX_MUSIC = "maxMusic";
        public static final String MIN_MUSIC = "minMusic";
        public static final String CURRENT_MUSIC = "currentMusic";
        public static final String MAX_ALARM = "maxAlarm";
        public static final String MIN_ALARM = "minAlarm";
        public static final String CURRENT_ALARM = "currentAlarm";
        public static final String MAX_NOTIFICATIONS="maxNotifications";
        public static final String MIN_NOTIFICATIONS="minNotifications";
        public static final String CURRENT_NOTIFICATIONS="currentNotifications";
        public static final String MAX_ACCESSIBILITY="maxAccessibility";
        public static final String MIN_ACCESSIBILITY="minAccessibility";
        public static final String CURRENT_ACCESSIBILITY="currentAccessibility";
        public static final String MAX_DTMF="maxDTMF";
        public static final String MIN_DTMF="minDTMF";
        public static final String CURRENT_DTMF="currentDTMF";
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
        public static final String IS_HAVE_XPOSED = "xposedInfo";
        public static final String IS_HAVE_SUBSTRATE = "substrateInfo";
        public static final String IS_HAVE_FRIDA = "fridaInfo";

        public static class Xposed {
            public static final String CHECK_XPOSED_PACKAGE = "checkXposedPackage";
            public static final String CHECK_XPOSED_HOOK_METHOD = "checkXposedHookMethod";
            public static final String CHECK_XPOSED_JARS = "checkXposedJars";
            public static final String CHECK_CLASSLOADER = "checkClassLoader";
            public static final String CHECK_NATIVE_METHOD = "checkNativeMethod";
            public static final String CHECK_SYSTEM = "checkSystem";
            public static final String CHECK_EXEC_LIB = "checkExecLib";
            public static final String CHECK_CHECKMAN = "checkCheckman";
            public static final String CHECK_XPOSED_BRIDGE = "checkXposedBridge";

        }

        public static class Substrate {
            public static final String CHECK_SUBSTRATE_PACKAGE = "checkSubstratePackage";
            public static final String CHECK_SUBSTRATE_HOOK_METHOD = "checkSubstrateHookMethod";
            public static final String CHECK_SUBSTRATE_JARS = "checkSubstrateJars";
        }

        public static class Frida {
            public static final String CHECK_RUNNING_PROCESSES = "checkRunningProcesses";
            public static final String CHECK_FRIDA_JARS = "checkFridaJars";

        }


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
        public static final String CHECK_HIDE_STATUSBAR = "checkHideStatusBar";
        public static final String CHECK_HAS_NAVIGATIONBAR = "checkHasNavigationBar";
        public static final String GET_STATUSBAR_HEIGHT = "getStatusBarHeight";
        public static final String GET_NAVIGATIONBAR_HEIGHT = "getNavigationBarHeight";
        public static final String IS_WINDOW_NOTCH = "isWindowNotch";
        public static final String WINDOW_NOTCH_HEIGHT = "windowNotchHeight";
    }

    public static class Cpu {
        public static final String CPU_NAME = "cpuName";
        public static final String CPU_FREQ = "cpuFreq";
        public static final String CPU_MAX_FREQ = "cpuMaxFreq";
        public static final String CPU_MIN_FREQ = "cpuMinFreq";
        public static final String CPU_HARDWARE = "cpuHardware";
        public static final String CPU_CORES = "cpuCores";
        public static final String CPU_TEMP = "cpuTemp";
        public static final String CPU_ABI = "cpuAbi";
    }

    public static class Build {
        public static final String BOARD = "board";
        public static final String BOOTLOADER = "bootloader";
        public static final String BRAND = "brand";
        public static final String DEVICE = "device";
        public static final String DISPLAY = "display";
        public static final String FINGERPRINT = "fingerprint";
        public static final String HARDWARE = "hardware";
        public static final String HOST = "host";
        public static final String ID = "id";
        public static final String MANUFACTURER = "manufacturer";
        public static final String MODEL = "model";
        public static final String PRODUCT = "product";
        public static final String RADIO = "radio";
        public static final String SERIAL = "serial";
        public static final String TAGS = "tags";
        public static final String TIME = "time";
        public static final String TYPE = "type";
        public static final String USER = "user";
        public static final String OS_VERSION = "osVersion";
        public static final String RELEASE_VERSION = "releaseVersion";
        public static final String CODE_NAME = "codeName";
        public static final String INCREMENTAL = "incremental";
        public static final String SDK_INT = "sdkInt";
        public static final String PREVIEW_SDK_INT = "previewSdkInt";
        public static final String SECURITY_PATCH = "securityPatch";
    }

    public static class App {
        public static final String APP_NAME = "appName";
        public static final String PACKAGE_NAME = "packageName";
        public static final String PACKAGE_SIGN = "packageSign";
        public static final String APP_VERSION_CODE = "appVersionCode";
        public static final String APP_VERSION_NAME = "appVersionName";
        public static final String APP_TARGET_SDK_VERSION = "targetSdkVersion";
        public static final String APP_MIN_SDK_VERSION = "minSdkVersion";
        public static final String APP_DESCRIPTION="description";
        public static final String APP_ICON="icon";
    }

    public static class Camera {
        public static final String CAMERA_INFO = "cameraInfo";

        public static class CameraInfo {
            public static final String CAMERA_FACING = "cameraFacing";
            public static final String CAMERA_LEVEL = "cameraLevel";
            public static final String CAMERA_FLASH_INFO = "cameraFlashInfo";
            public static final String OUTPUT_FORMATS = "outputFormats";
        }
    }

    public static class Memory {
        public static final String RAM_MEMORY = "ramMemoryTotal";
        public static final String RAM_AVAIL_MEMORY = "ramMemoryAvailable";
        public static final String ROM_MEMORY_AVAILABLE = "romMemoryAvailable";
        public static final String ROM_MEMORY_TOTAL = "romMemoryTotal";
        public static final String SDCARD_MEMORY_AVAILABLE = "sdCardMemoryAvailable";
        public static final String SDCARD_MEMORY_TOTAL = "sdCardMemoryTotal";
        public static final String SDCARD_REAL_MEMORY_TOTAL = "sdCardRealMemoryTotal";
    }

    public static class Emulator {
        public static final String CHECK_BUILD = "checkBuild";
        public static final String CHECK_PKG = "checkPkg";
        public static final String CHECK_PIPES = "checkPipes";
        public static final String CHECK_QEMU_DRIVER_FILE = "checkQEmuDriverFile";
        public static final String CHECK_HAS_LIGHT_SENSOR_MANAGER = "checkHasLightSensorManager";
        public static final String CHECK_CPU_INFO = "checkCpuInfo";
    }

    public static class NetWork {
        public static final String TYPE = "type";
        public static final String NETWORK_AVAILABLE = "networkAvailable";
        public static final String HAVE_INTENT = "haveIntent";
        public static final String IS_FLIGHT_MODE = "isFlightMode";
        public static final String IS_NFC_ENABLED = "isNFCEnabled";
        public static final String IS_HOTSPOT_ENABLED = "isHotspotEnabled";
        public static final String HOTSPOT_SSID = "hotspotSSID";
        public static final String HOTSPOT_PWD = "hotspotPwd";
        public static final String ENCRYPTION_TYPE = "encryptionType";
    }

    public static class Signal {
        public static final String TYPE = "type";
        public static final String BSSID = "bssid";
        public static final String SSID = "ssid";
        public static final String N_IP_ADDRESS = "ipAddress";
        public static final String N_IP_ADDRESS_IPV6 = "ipAddressIpv6";
        public static final String MAC_ADDRESS = "macAddress";
        public static final String NETWORK_ID = "networkId";
        public static final String LINK_SPEED = "linkSpeed";
        public static final String RSSI = "rssi";
        public static final String LEVEL = "level";
        public static final String SUPPLICANT_STATE = "supplicantState";
        public static final String PROXY = "proxy";
        public static final String PROXY_ADDRESS = "proxyAddress";
        public static final String PROXY_PORT = "proxyPort";
    }

    public static class SimCard {
        public static final String SIM1_IMEI = "sim1Imei";
        public static final String SIM2_IMEI = "sim2Imei";
        public static final String SIM1_IMSI = "sim1Imsi";
        public static final String SIM2_IMSI = "sim2Imsi";
        public static final String SIM_SLOT_INDEX = "simSlotIndex";
        public static final String MEID = "meid";
        public static final String SIM1_IMSI_OPERATOR = "sim1ImsiOperator";
        public static final String SIM2_IMSI_OPERATOR = "sim2ImsiOperator";
        public static final String SIM1_READY = "sim1Ready";
        public static final String SIM2_READY = "sim2Ready";
        public static final String IS_TWO_CARD = "isTwoCard";
        public static final String IS_HAVE_CARD = "isHaveCard";
        public static final String OPERATOR = "operator";
    }

    public static class SDCard {
        public static final String IS_SDCARD_ENABLE = "isSDCardEnable";
        public static final String SDCARD_PATH = "getSDCardPath";
    }
}