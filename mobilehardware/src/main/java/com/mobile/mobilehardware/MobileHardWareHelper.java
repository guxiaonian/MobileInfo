package com.mobile.mobilehardware;

import android.content.Context;
import android.support.annotation.NonNull;

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

    private static final String VERSION = "2.2.1";
    private static final String TAG = "MobileHardWareHelper";

    public static String getMobVersion() {
        return VERSION;
    }

    /**
     * 获取指定ip的host，只能在子线程中进行。指定域名如：api.geetest.com
     *
     * @param host
     * @return
     */
    public static String mobNetDNS(@NonNull String host) {
        return DnsHelper.mobDNS(host);
    }

    /**
     * 获取收集所有app的信息，很耗时，需要在子线程中执行
     *
     * @param context
     * @return
     */
    public static List<JSONObject> mobileAppList(@NonNull Context context) {
        return ListAppHelper.mobListApp(context);
    }


    /**
     * 手机是否root
     *
     * @param context
     * @return
     */
    public static boolean mobileRoot(@NonNull Context context) {
        return RootHelper.mobileRoot(context);
    }

    /**
     * 手机设置信息
     *
     * @param context
     * @return
     */
    public static JSONObject mobileSettings(@NonNull Context context) {
        return SettingsHelper.mobGetMobSettings(context);
    }

    /**
     * 手机电池信息
     *
     * @param context
     * @return
     */
    public static JSONObject mobileBattery(@NonNull Context context) {
        return BatteryHelper.mobGetBattery(context);
    }

    /**
     * 手机CPU信息
     *
     * @return
     */
    public static JSONObject mobileCPU() {
        return CpuHelper.mobGetCpuInfo();
    }

    /**
     * 手机内存信息
     *
     * @return
     */
    public static JSONObject mobileMemory(@NonNull Context context) {
        return MemoryHelper.getMemoryInfo(context);
    }

    /**
     * 手机版本信息
     *
     * @return
     */
    public static JSONObject mobileBand() {
        return BandHelper.mobGetBandInfo();
    }

    /**
     * app包信息
     *
     * @return
     */
    public static JSONObject mobilePackage(@NonNull Context context) {
        return PackageHelper.getPackageInfo(context);
    }

    /**
     * 手机摄像头信息
     *
     * @return
     */
    public static JSONObject mobileCamera(@NonNull Context context) {
        return CameraHelper.getCameraInfo(context);
    }

    /**
     * 手机信号信息
     *
     * @param context
     * @return
     */
    public static JSONObject mobileRssi(@NonNull Context context) {
        return SignalHelper.mobGetNetRssi(context);
    }

    /**
     * 手机基本信息 build
     *
     * @return
     */
    public static JSONObject mobileBuild() {
        return BuildHelper.mobGetBuildInfo();
    }

    /**
     * 手机基本信息 local
     *
     * @return
     */
    public static JSONObject mobileLocal() {
        return LocalHelper.mobGetMobLocal();
    }

    /**
     * 手机基本信息 screen
     *
     * @return
     */
    public static JSONObject mobileScreen(@NonNull Context context) {
        return ScreenHelper.mobGetMobScreen(context);
    }

    /**
     * 手机基本信息 netWork
     *
     * @return
     */
    public static JSONObject mobileNetWork(@NonNull Context context) {
        return NetWorkHelper.mobGetMobNetWork(context);
    }


    /**
     * 手机基本信息 emulator
     *
     * @return
     */
    public static JSONObject mobileEmulator(@NonNull Context context) {
        return EmulatorHelper.mobCheckEmulator(context);
    }


    /**
     * 蓝牙信息 bluetooth
     *
     * @param context
     * @return
     */
    public static JSONObject mobileBluetooth(@NonNull Context context) {
        return BluetoothHelper.mobGetMobBluetooth(context);
    }

    /**
     * 拿到手机卡的详细信息
     *
     * @param context
     * @return
     */
    public static JSONObject mobileSimInfo(@NonNull Context context) {
        return SimCardHelper.mobileSimInfo(context);
    }

    /**
     * 返回默认的UA
     *
     * @param context
     * @return
     */
    public static String mobileUserAgent(@NonNull Context context) {
        return UserAgentHelper.getDefaultUserAgent(context);
    }

    /**
     * 利用硬件标示来产生设备的唯一id
     *
     * @return
     */
    public static String mobileId() {
        return PhoneIdHelper.getPsuedoUniqueID();
    }

    /**
     * 判断是否包含Hook工具
     *
     * @param context 上下文
     * @return
     */
    public static JSONObject isHaveXposed(@NonNull Context context) {
        return HookHelper.isXposedHook(context);
    }

    /**
     * 判断Hook的信息
     *
     * @param context 上下文
     * @return
     */
    public static JSONObject xposedInfo(@NonNull Context context) {
        return XposedHookHelper.checkXposedInjet(context);
    }


    /**
     * 判断debug的信息
     *
     * @param context 上下文
     * @return
     */
    public static JSONObject debugInfo(@NonNull Context context) {
        return DebugHelper.getDebuggingData(context);
    }

    /**
     * 判断多开软件的信息
     *
     * @param context 上下文
     * @return
     */
    public static JSONObject virtualInfo(@NonNull Context context) {
        return MoreOpenHelper.checkVirtual(context);
    }

    /**
     * 判断当前线程的堆栈信息
     *
     * @param thread 所要获取的线程
     * @return
     */
    public static String stackInfo(@NonNull Thread thread) {
        return StackSampler.getStackInfo(thread);
    }

    /**
     * 获取手机音量的信息
     *
     * @param context 上下文
     * @return
     */
    public static JSONObject audioInfo(@NonNull Context context) {
        return AudioHelper.mobGetMobAudio(context);
    }

    /**
     * 获取SDCard信息
     *
     * @return
     */
    public static JSONObject getSDCardInfo() {
        return SDCardHelper.mobGetSdCard();
    }
}
