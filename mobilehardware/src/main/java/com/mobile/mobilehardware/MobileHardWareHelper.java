package com.mobile.mobilehardware;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;

import com.mobile.mobilehardware.applist.ListAppHelper;
import com.mobile.mobilehardware.audio.AudioHelper;
import com.mobile.mobilehardware.band.BandHelper;
import com.mobile.mobilehardware.bluetooth.BluetoothHelper;
import com.mobile.mobilehardware.build.BuildHelper;
import com.mobile.mobilehardware.sdcard.SDCardHelper;
import com.mobile.mobilehardware.simcard.SimCardHelper;
import com.mobile.mobilehardware.xposed.XposedHookHelper;
import com.mobile.mobilehardware.root.RootHelper;
import com.mobile.mobilehardware.stack.StackSampler;
import com.mobile.mobilehardware.debug.DebugHelper;
import com.mobile.mobilehardware.moreopen.MoreOpenHelper;
import com.mobile.mobilehardware.hook.HookHelper;
import com.mobile.mobilehardware.battery.BatteryHelper;
import com.mobile.mobilehardware.camera.CameraHelper;
import com.mobile.mobilehardware.cpu.CpuHelper;
import com.mobile.mobilehardware.dns.DnsHelper;
import com.mobile.mobilehardware.emulator.EmulatorHelper;
import com.mobile.mobilehardware.local.LocalHelper;
import com.mobile.mobilehardware.memory.MemoryHelper;
import com.mobile.mobilehardware.network.NetWorkHelper;
import com.mobile.mobilehardware.app.PackageHelper;
import com.mobile.mobilehardware.signal.SignalHelper;
import com.mobile.mobilehardware.screen.ScreenHelper;
import com.mobile.mobilehardware.setting.SettingsHelper;
import com.mobile.mobilehardware.useragent.UserAgentHelper;
import com.mobile.mobilehardware.uniqueid.PhoneIdHelper;

import org.json.JSONObject;

import java.util.List;

/**
 * @author gunaonian
 * @date 2018/3/27
 */

public class MobileHardWareHelper {

    /**
     * 全局上下文
     */
    private static Context mContext;

    /**
     * 设置全局上下文 默认使用MobInitializer来进行初始化
     * 可以自行修改
     *
     * @param context 上下文
     */
    public static void init(@NonNull Context context) {
        mContext = context;
    }

    /**
     * 获取全局上下文
     *
     * @return 上下文
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * app包信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Package
     */
    public static JSONObject getPackageInfo() {
        return PackageHelper.getPackageInfo();
    }

    /**
     * 获取收集所有app的信息，很耗时，需要在子线程中执行
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/AppList
     */
    public static List<JSONObject> getListAppInfo() {
        return ListAppHelper.mobListApp();
    }

    /**
     * 获取手机音量的信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Audio
     */
    public static JSONObject getAudioInfo() {
        return AudioHelper.mobGetMobAudio();
    }

    /**
     * 手机版本信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Band
     */
    public static JSONObject getBandInfo() {
        return BandHelper.mobGetBandInfo();
    }

    /**
     * 手机电池信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Battery
     */
    public static JSONObject getBatteryInfo() {
        return BatteryHelper.mobGetBattery();
    }


    /**
     * 蓝牙信息 bluetooth
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Bluetooth
     */
    public static JSONObject getBluetoothInfo() {
        return BluetoothHelper.mobGetMobBluetooth();
    }

    /**
     * 手机基本信息 build
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Build
     */
    public static JSONObject getBuildInfo() {
        return BuildHelper.mobGetBuildInfo();
    }


    /**
     * 手机摄像头信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Camera
     */
    public static JSONObject getCameraInfo() {
        return CameraHelper.getCameraInfo();
    }

    /**
     * 手机CPU信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Cpu
     */
    public static JSONObject getCpuInfo() {
        return CpuHelper.mobGetCpuInfo();
    }

    /**
     * 判断debug的信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Debug
     */
    public static JSONObject getDebugInfo() {
        return DebugHelper.getDebuggingData();
    }

    /**
     * 获取指定ip的host，只能在子线程中进行。
     *
     * @param host ip地址
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Host
     */
    public static String getHostAddressInfo(@NonNull String host) {
        return DnsHelper.mobDNS(host);
    }

    /**
     * 手机基本信息 emulator
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Emulator
     */
    public static JSONObject getEmulatorInfo() {
        return EmulatorHelper.mobCheckEmulator();
    }

    /**
     * 判断是否包含Hook工具
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Hook
     */
    public static JSONObject getHookInfo() {
        return HookHelper.isXposedHook();
    }

    /**
     * 手机基本信息 local
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Local
     */
    public static JSONObject getLocalInfo() {
        return LocalHelper.mobGetMobLocal();
    }

    /**
     * 手机内存信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Memory
     */
    public static JSONObject getMemoryInfo() {
        return MemoryHelper.getMemoryInfo();
    }

    /**
     * 判断多开软件的信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/MoreOpen
     */
    public static JSONObject getMoreOpenInfo() {
        return MoreOpenHelper.checkVirtual();
    }

    /**
     * 手机基本信息 netWork
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/NetWork
     */
    public static JSONObject getNetWorkInfo() {
        return NetWorkHelper.mobGetMobNetWork();
    }


    /**
     * 手机是否root
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Root
     */
    public static boolean isRoot() {
        return RootHelper.mobileRoot();
    }

    /**
     * 手机基本信息 screen
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Screen
     */
    public static JSONObject getScreenInfo(@Nullable Window window) {
        return ScreenHelper.mobGetMobScreen(window);
    }


    /**
     * 获取SDCard信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/SDCard
     */
    public static JSONObject getSDCardInfo() {
        return SDCardHelper.mobGetSdCard();
    }

    /**
     * 手机设置信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Settings
     */
    public static JSONObject getSettingsInfo() {
        return SettingsHelper.mobGetMobSettings();
    }

    /**
     * 手机信号信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Signal
     */
    public static JSONObject getSignalInfo() {
        return SignalHelper.mobGetNetRssi();
    }

    /**
     * 判断Hook的信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Xposed
     */
    public static JSONObject getXposedInfo() {
        return XposedHookHelper.checkXposedInjet();
    }

    /**
     * 返回默认的UA
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/UserAgent
     */
    public static String getUserAgentInfo() {
        return UserAgentHelper.getDefaultUserAgent();
    }

    /**
     * 利用硬件标示来产生设备的唯一id
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/PhoneId
     */
    public static String getUniqueIDInfo() {
        return PhoneIdHelper.getUniqueID();
    }

    /**
     * 判断当前线程的堆栈信息
     *
     * @param thread 所要获取的线程
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/Stack
     */
    public static String getThreadStackInfo(@NonNull Thread thread) {
        return StackSampler.getStackInfo(thread);
    }

    /**
     * 拿到手机卡的详细信息
     *
     * @return @see https://github.com/guxiaonian/MobileInfo/wiki/SimCard
     */
    public static JSONObject getSimInfo() {
        return SimCardHelper.mobileSimInfo();
    }


}
