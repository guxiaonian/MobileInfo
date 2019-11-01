package com.mobile.mobilehardware.screen;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class ScreenBean extends BaseBean {
    private static final String TAG = ScreenBean.class.getSimpleName();

    /**
     * 是否是刘海屏
     */
    private boolean isWindowNotch;

    /**
     * 刘海屏高度
     */
    private int windowNotchHeight;

    /**
     * 当前屏幕密度与标准屏幕密度的比值
     */
    private float densityScale;

    /**
     * 屏幕密度
     */
    private int densityDpi;

    /**
     * 屏幕宽度
     */
    private int width;

    /**
     * 屏幕高度
     */
    private int height;

    /**
     * 亮度是否为自动调节
     */
    private boolean isScreenAuto;

    /**
     * 屏幕亮度
     */
    private int screenBrightness;

    /**
     * 屏幕是否开启自动旋转
     */
    private boolean isScreenAutoChange;

    /**
     * 是否隐藏状态栏
     */
    private boolean checkHideStatusBar;

    /**
     * 是否显示底部导航栏
     */
    private boolean checkHasNavigationBar;

    /**
     * 获取状态栏高度
     */
    private int getStatusBarHeight;

    /**
     * 获取底部导航栏的高度
     */
    private int getNavigationBarHeight;

    public boolean isWindowNotch() {
        return isWindowNotch;
    }

    public void setWindowNotch(boolean windowNotch) {
        isWindowNotch = windowNotch;
    }

    public int getWindowNotchHeight() {
        return windowNotchHeight;
    }

    public void setWindowNotchHeight(int windowNotchHeight) {
        this.windowNotchHeight = windowNotchHeight;
    }

    public float getDensityScale() {
        return densityScale;
    }

    public void setDensityScale(float densityScale) {
        this.densityScale = densityScale;
    }

    public int getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(int densityDpi) {
        this.densityDpi = densityDpi;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isScreenAuto() {
        return isScreenAuto;
    }

    public void setScreenAuto(boolean screenAuto) {
        isScreenAuto = screenAuto;
    }

    public int getScreenBrightness() {
        return screenBrightness;
    }

    public void setScreenBrightness(int screenBrightness) {
        this.screenBrightness = screenBrightness;
    }

    public boolean isScreenAutoChange() {
        return isScreenAutoChange;
    }

    public void setScreenAutoChange(boolean screenAutoChange) {
        isScreenAutoChange = screenAutoChange;
    }

    public boolean isCheckHideStatusBar() {
        return checkHideStatusBar;
    }

    public void setCheckHideStatusBar(boolean checkHideStatusBar) {
        this.checkHideStatusBar = checkHideStatusBar;
    }

    public boolean isCheckHasNavigationBar() {
        return checkHasNavigationBar;
    }

    public void setCheckHasNavigationBar(boolean checkHasNavigationBar) {
        this.checkHasNavigationBar = checkHasNavigationBar;
    }

    public int getGetStatusBarHeight() {
        return getStatusBarHeight;
    }

    public void setGetStatusBarHeight(int getStatusBarHeight) {
        this.getStatusBarHeight = getStatusBarHeight;
    }

    public int getGetNavigationBarHeight() {
        return getNavigationBarHeight;
    }

    public void setGetNavigationBarHeight(int getNavigationBarHeight) {
        this.getNavigationBarHeight = getNavigationBarHeight;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Screen.DENSITY_SCALE, densityScale);
            jsonObject.put(BaseData.Screen.DENSITY_DPI, densityDpi);
            jsonObject.put(BaseData.Screen.WIDTH, width);
            jsonObject.put(BaseData.Screen.HEIGHT, height);
            jsonObject.put(BaseData.Screen.IS_SCREEN_AUTO, isScreenAuto);
            jsonObject.put(BaseData.Screen.IS_SCREEN_AUTO_CHANGE, isScreenAutoChange);
            jsonObject.put(BaseData.Screen.SCREEN_BRIGHTNESS, screenBrightness);
            jsonObject.put(BaseData.Screen.CHECK_HIDE_STATUSBAR, checkHideStatusBar);
            jsonObject.put(BaseData.Screen.CHECK_HAS_NAVIGATIONBAR, checkHasNavigationBar);
            jsonObject.put(BaseData.Screen.GET_STATUSBAR_HEIGHT, getStatusBarHeight);
            jsonObject.put(BaseData.Screen.GET_NAVIGATIONBAR_HEIGHT, getNavigationBarHeight);
            jsonObject.put(BaseData.Screen.IS_WINDOW_NOTCH, isWindowNotch);
            jsonObject.put(BaseData.Screen.WINDOW_NOTCH_HEIGHT, windowNotchHeight + "");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
