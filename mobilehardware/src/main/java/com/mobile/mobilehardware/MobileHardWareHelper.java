package com.mobile.mobilehardware;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mobile.mobilehardware.applist.ListAppHelper;
import com.mobile.mobilehardware.audio.AudioHelper;
import com.mobile.mobilehardware.band.BandHelper;
import com.mobile.mobilehardware.bluetooth.BluetoothHelper;
import com.mobile.mobilehardware.xposed.XposedHookHelper;
import com.mobile.mobilehardware.root.RootHelper;
import com.mobile.mobilehardware.stack.StackSampler;
import com.mobile.mobilehardware.debug.DebugHelper;
import com.mobile.mobilehardware.moreopen.MoreOpenHelper;
import com.mobile.mobilehardware.hook.HookHelper;
import com.mobile.mobilehardware.battery.BatteryHelper;
import com.mobile.mobilehardware.utils.MobBuildUtils;
import com.mobile.mobilehardware.utils.MobCameraUtils;
import com.mobile.mobilehardware.utils.MobCardUtils;
import com.mobile.mobilehardware.cpu.CpuHelper;
import com.mobile.mobilehardware.dns.DnsHelper;
import com.mobile.mobilehardware.utils.MobEmulatorUtils;
import com.mobile.mobilehardware.local.LocalHelper;
import com.mobile.mobilehardware.utils.MobMemoryUtils;
import com.mobile.mobilehardware.utils.MobNetWorkUtils;
import com.mobile.mobilehardware.utils.MobPackageUtils;
import com.mobile.mobilehardware.utils.MobRssiUtils;
import com.mobile.mobilehardware.screen.ScreenHelper;
import com.mobile.mobilehardware.setting.SettingsHelper;
import com.mobile.mobilehardware.useragent.UserAgentHelper;
import com.mobile.mobilehardware.uniqueid.PhoneIdHelper;
import com.mobile.mobilehardware.utils.SimOperator;

import org.json.JSONObject;

import java.util.List;

/**
 *
 * @author gunaonian
 * @date 2018/3/27
 */

public class MobileHardWareHelper {

    private static final String VERSION = "1.0.0";
    private static final String TAG = "MobileHardWareHelper";

    public static String getMobVersion() {
        return VERSION;
    }

    /**
     * 手机信息全部方法汇总
     *
     * @param context
     * @return
     */
    public static JSONObject mobileInfo(@NonNull Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            //当然以下这些并不是特别耗时的操作，可以直接在主线程获取-------------------------------------------------------------------------//


            //需要实时获取-----------------------------------------------------------------//
            jsonObject.put("mobBatteryInfo", mobileBattery(context));
//            jsonObject.put("mobCardInfo", mobileCard(context));
//            jsonObject.put("mobSignalInfo", mobileRssi(context));
//            jsonObject.put("mobNetWorkInfo", mobileNetWork(context));
//            jsonObject.put("mobMemoryInfo", mobileMemory(context));
//
//            //可以在app首次安装的时候进行获取并进行保存-----------------------------------------------------------------------//
//            jsonObject.put("mobRootInfo", mobileRoot(context) + "");
//            jsonObject.put("mobSettingsInfo", mobileSettings(context));
//            jsonObject.put("mobCpuInfo", mobileCPU());
//            jsonObject.put("mobBandInfo", mobileBand());
//            jsonObject.put("mobPackageInfo", mobilePackage(context));
//            jsonObject.put("mobCameraInfo", mobileCamera(context));
//            jsonObject.put("mobBuildInfo", mobileBuild(context));
//            jsonObject.put("mobLocalInfo", mobileLocal());
//            jsonObject.put("mobScreenInfo", mobileScreen(context));
//            jsonObject.put("mobEmulatorInfo", mobileEmulator(context));
//            jsonObject.put("mobBluetoothInfo", mobileBluetooth(context));
//
//            //需要实时获取-----------------------------------------------------------------//
//            jsonObject.put("mobSimOperator", mobileSimOperator(context, null));
//            jsonObject.put("mobSimCard", mobileSimInfo(context));
//            jsonObject.put("mobUserAgent", mobileUserAgent(context));
//            jsonObject.put("mobUniqueID", mobileId());
//            jsonObject.put("xposedInfo", xposed(context,null));


        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return jsonObject;
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
        return MobMemoryUtils.getMemoryInfo(context);
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
        return MobPackageUtils.getPackageInfo(context);
    }

    /**
     * 手机摄像头信息
     *
     * @return
     */
    public static JSONObject mobileCamera(@NonNull Context context) {
        return MobCameraUtils.getCameraInfo(context);
    }

    /**
     * 手机卡信息
     *
     * @param context
     * @return
     */
    public static JSONObject mobileCard(@NonNull Context context) {
        return MobCardUtils.mobGetCardInfo(context);
    }

    /**
     * 手机信号信息
     *
     * @param context
     * @return
     */
    public static JSONObject mobileRssi(@NonNull Context context) {
        return MobRssiUtils.mobGetNetRssi(context);
    }

    /**
     * 手机基本信息 build
     *
     * @return
     */
    public static JSONObject mobileBuild(@NonNull Context context) {
        return MobBuildUtils.mobGetBuildInfo(context);
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
        return MobNetWorkUtils.mobGetMobNetWork(context);
    }


    /**
     * 手机基本信息 emulator
     *
     * @return
     */
    public static JSONObject mobileEmulator(@NonNull Context context) {
        return MobEmulatorUtils.mobCheckEmulator(context);
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
     * 返回当前的流量框卡的运营商
     *
     * @param context
     * @param number
     * @return CM CT CU
     */
    public static String mobileSimOperator(@NonNull Context context, @Nullable String number) {
        return SimOperator.getSimOperator(context, number);
    }

    /**
     * 拿到手机卡的详细信息
     *
     * @param context
     * @return
     */
    public static JSONObject mobileSimInfo(@NonNull Context context) {
        return SimOperator.getMobSimInfo(context);
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


}
