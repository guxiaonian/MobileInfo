package com.mobile.mobilehardware.screen.notch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 谷闹年 on 2019/9/12.\
 * <p>本方法参考自https://github.com/zhangzhun132/NotchTools </p>
 * 感谢
 */
public class WindowInsetsUtils {

    public static void getNotchParams(Window window, final ResultListener resultListener) {
        final View decorView = window.getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            decorView.post(new Runnable() {
                @Override
                public void run() {
                    boolean isWindowNotch = false;
                    int windowNotchHeight = 0;
                    try {
                        DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
                        List<Rect> rects = displayCutout.getBoundingRects();
                        if (rects != null && rects.size() > 0) {
                            isWindowNotch = true;
                            windowNotchHeight = displayCutout.getSafeInsetTop();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    resultListener.onResult(isWindowNotch, windowNotchHeight);
                }
            });
        } else {
            boolean isWindowNotch = false;
            int windowNotchHeight = 0;
            if (DeviceBrandTools.getInstance().isHuaWei()) {
                isWindowNotch = isNotchScreenOfHuaWei(window);
                if (isWindowNotch) {
                    windowNotchHeight = getNotchHeightOfHuaWei(window);
                }
            } else if (DeviceBrandTools.getInstance().isMiui()) {
                isWindowNotch = isNotchScreenOfXiaoMi(window);
                if (isWindowNotch) {
                    windowNotchHeight = getNotchHeightOfXiaoMi(window);
                }
            } else if (DeviceBrandTools.getInstance().isOppo()) {
                isWindowNotch = isNotchScreenOfOppo(window);
                if (isWindowNotch) {
                    windowNotchHeight = getNotchHeightOfOppo(window);
                }
            } else if (DeviceBrandTools.getInstance().isVivo()) {
                isWindowNotch = isNotchScreenOfVivo(window);
                if (isWindowNotch) {
                    windowNotchHeight = getNotchHeightOfVivo(window);
                }
            } else if (DeviceBrandTools.getInstance().isSamsung()) {
                isWindowNotch = isNotchScreenOfSamsung(window);
                if (isWindowNotch) {
                    windowNotchHeight = getNotchHeightOfSamsung(window);
                }
            }
            resultListener.onResult(isWindowNotch, windowNotchHeight);
        }
    }

    /**
     * 三星是否为刘海屏
     *
     * @param window
     * @return
     */
    private static boolean isNotchScreenOfSamsung(Window window) {
        if (window == null) {
            return false;
        }
        boolean isNotchScreen = false;
        try {
            final Resources res = window.getContext().getResources();
            final int resId = res.getIdentifier("config_mainBuiltInDisplayCutout", "string", "android");
            final String spec = resId > 0 ? res.getString(resId) : null;
            isNotchScreen = spec != null && !TextUtils.isEmpty(spec);
        } catch (Exception e) {
            return isNotchScreen;
        }
        return isNotchScreen;
    }

    /**
     * 三星刘海屏高度
     *
     * @param window
     * @return
     */
    private static int getNotchHeightOfSamsung(Window window) {
        if (!isNotchScreenOfSamsung(window)) {
            return 0;
        }

        return getStatusBarHeight(window.getContext());
    }

    /**
     * 华为是否为刘海屏
     *
     * @param window
     * @return
     */
    private static boolean isNotchScreenOfHuaWei(Window window) {
        boolean isNotchScreen = false;
        try {
            ClassLoader cl = window.getContext().getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            isNotchScreen = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNotchScreen;
    }

    /**
     * 华为刘海屏高度
     *
     * @param window
     * @return
     */
    private static int getNotchHeightOfHuaWei(Window window) {
        if (!isNotchScreenOfHuaWei(window)) {
            return 0;
        }
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = window.getContext().getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret[1];
    }

    /**
     * XIOAMI是否为刘海屏
     *
     * @param window
     * @return
     */
    private static boolean isNotchScreenOfXiaoMi(Window window) {
        try {
            return "1".equals(SystemProperties.getInstance().get("ro.miui.notch"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * XIAOMI刘海屏高度
     *
     * @param window
     * @return
     */
    private static int getNotchHeightOfXiaoMi(Window window) {
        if (!isNotchScreenOfXiaoMi(window)) {
            return 0;
        }

        int result = 0;
        try {
            Context context = window.getContext();
            if (isHideNotch(window.getContext())) {
                result = getStatusBarHeight(context);
            } else {
                result = getRealNotchHeight(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * OPPO是否为刘海屏
     *
     * @param window
     * @return
     */
    private static boolean isNotchScreenOfOppo(Window window) {
        try {
            return window.getContext().getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * OPPO刘海屏高度
     *
     * @param window
     * @return
     */
    private static int getNotchHeightOfOppo(Window window) {
        if (!isNotchScreenOfOppo(window)) {
            return 0;
        }
        return getStatusBarHeight(window.getContext());
    }

    /**
     * VIVO是否为刘海屏
     *
     * @param window
     * @return
     */
    private static boolean isNotchScreenOfVivo(Window window) {
        if (window == null) {
            return false;
        }
        ClassLoader classLoader = window.getContext().getClassLoader();
        try {
            Class mClass = classLoader.loadClass("android.util.FtFeature");
            Method mMethod = mClass.getMethod("isFeatureSupport", Integer.TYPE);
            return (boolean) mMethod.invoke(mClass, 0x00000020);
        } catch (ClassNotFoundException e) {
            return false;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        } catch (InvocationTargetException e) {
            return false;
        }
    }

    /**
     * VIVO刘海屏高度
     *
     * @param window
     * @return
     */
    private static int getNotchHeightOfVivo(Window window) {
        if (!isNotchScreenOfVivo(window)) {
            return 0;
        }
        return getStatusBarHeight(window.getContext());
    }

    private static int getRealNotchHeight(Context context) {
        int result;
        int resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        } else {
            result = getStatusBarHeight(context);
        }
        return result;
    }

    private static boolean isHideNotch(Context activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.Global.getInt(activity.getContentResolver(),
                    "force_black", 0) == 1;
        } else {
            return true;
        }
    }

    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resId);
        }
        return statusBarHeight;
    }

}
