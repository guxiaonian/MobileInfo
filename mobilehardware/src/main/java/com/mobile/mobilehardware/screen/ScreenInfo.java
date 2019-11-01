package com.mobile.mobilehardware.screen;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mobile.mobilehardware.screen.notch.ResultListener;
import com.mobile.mobilehardware.screen.notch.WindowInsetsUtils;

import org.json.JSONObject;

/**
 * @author gunaonian
 * @date 2018/3/28
 */

class ScreenInfo {
    private static final String TAG = ScreenInfo.class.getSimpleName();

    static JSONObject getMobScreen(Context context, Window window) {
        final ScreenBean screenBean = new ScreenBean();
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            screenBean.setDensityScale(displayMetrics.density);
            screenBean.setDensityDpi(displayMetrics.densityDpi);
            screenBean.setWidth(displayMetrics.widthPixels);
            screenBean.setHeight(displayMetrics.heightPixels);
            int screenMode = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
            int screenBrightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            int screenChange = Settings.System.getInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION);
            screenBean.setScreenAuto((screenMode == 1));
            screenBean.setScreenBrightness(screenBrightness);
            screenBean.setScreenAutoChange((screenChange == 1));
            screenBean.setCheckHasNavigationBar(checkHasNavigationBar(context));
            screenBean.setCheckHideStatusBar(checkHideStatusBar(context));
            screenBean.setGetStatusBarHeight(getStatusBarHeight(context));
            screenBean.setGetNavigationBarHeight(getNavigationBarHeight(context));
            if (window != null) {
                WindowInsetsUtils.getNotchParams(window, new ResultListener() {
                    @Override
                    public void onResult(boolean isWindowNotch, int windowNotchHeight) {
                        screenBean.setWindowNotch(isWindowNotch);
                        screenBean.setWindowNotchHeight(windowNotchHeight);
                    }
                });
            }
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return screenBean.toJSONObject();
    }


    private static boolean checkHideStatusBar(Context context) {
        return checkFullScreenByTheme(context) || checkFullScreenByCode(context) || checkFullScreenByCode2(context);
    }

    private static boolean checkFullScreenByTheme(Context context) {
        Resources.Theme theme = context.getTheme();
        if (theme != null) {
            TypedValue typedValue = new TypedValue();
            boolean result = theme.resolveAttribute(android.R.attr.windowFullscreen, typedValue, false);
            if (result) {
                typedValue.coerceToString();
                if (typedValue.type == TypedValue.TYPE_INT_BOOLEAN) {
                    return typedValue.data != 0;
                }
            }
        }
        return false;
    }

    private static boolean checkFullScreenByCode(Context context) {
        if (context instanceof Activity) {
            Window window = ((Activity) context).getWindow();
            if (window != null) {
                View decorView = window.getDecorView();
                if (decorView != null) {
                    return (decorView.getSystemUiVisibility() & View.SYSTEM_UI_FLAG_FULLSCREEN) == View.SYSTEM_UI_FLAG_FULLSCREEN;
                }
            }
        }
        return false;
    }

    private static boolean checkFullScreenByCode2(Context context) {
        if (context instanceof Activity) {
            return (((Activity) context).getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
        }
        return false;
    }

    private static boolean checkHasNavigationBar(Context context) {
        if (context instanceof Activity) {
            WindowManager windowManager = ((Activity) context).getWindowManager();
            Display d = windowManager.getDefaultDisplay();

            DisplayMetrics realDisplayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                d.getRealMetrics(realDisplayMetrics);
            }

            int realHeight = realDisplayMetrics.heightPixels;
            int realWidth = realDisplayMetrics.widthPixels;

            DisplayMetrics displayMetrics = new DisplayMetrics();
            d.getMetrics(displayMetrics);

            int displayHeight = displayMetrics.heightPixels;
            int displayWidth = displayMetrics.widthPixels;

            return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
        }
        return false;
    }


    private static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    private static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
}