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
     * 当前屏幕密度与标准屏幕密度的比值
     */
    private String densityScale;

    /**
     * 屏幕密度
     */
    private String densityDpi;

    /**
     * 屏幕宽度
     */
    private String width;

    /**
     * 屏幕高度
     */
    private String height;

    /**
     * 亮度是否为自动调节
     */
    private String isScreenAuto;

    /**
     * 屏幕亮度
     */
    private String screenBrightness;

    /**
     * 屏幕是否开启自动旋转
     */
    private String isScreenAutoChange;

    /**
     * 是否隐藏状态栏
     */
    private String checkHideStatusBar;

    /**
     * 是否显示底部导航栏
     */
    private String checkHasNavigationBar;

    /**
     * 获取状态栏高度
     */
    private String getStatusBarHeight;

    /**
     * 获取底部导航栏的高度
     */
    private String getNavigationBarHeight;

    public static String getTAG() {
        return TAG;
    }

    public String getCheckHideStatusBar() {
        return checkHideStatusBar;
    }

    public void setCheckHideStatusBar(String checkHideStatusBar) {
        this.checkHideStatusBar = checkHideStatusBar;
    }

    public String getCheckHasNavigationBar() {
        return checkHasNavigationBar;
    }

    public void setCheckHasNavigationBar(String checkHasNavigationBar) {
        this.checkHasNavigationBar = checkHasNavigationBar;
    }

    public String getGetStatusBarHeight() {
        return getStatusBarHeight;
    }

    public void setGetStatusBarHeight(String getStatusBarHeight) {
        this.getStatusBarHeight = getStatusBarHeight;
    }

    public String getGetNavigationBarHeight() {
        return getNavigationBarHeight;
    }

    public void setGetNavigationBarHeight(String getNavigationBarHeight) {
        this.getNavigationBarHeight = getNavigationBarHeight;
    }

    public String getDensityScale() {
        return densityScale;
    }

    public void setDensityScale(String densityScale) {
        this.densityScale = densityScale;
    }

    public String getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(String densityDpi) {
        this.densityDpi = densityDpi;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getIsScreenAuto() {
        return isScreenAuto;
    }

    public void setIsScreenAuto(String isScreenAuto) {
        this.isScreenAuto = isScreenAuto;
    }

    public String getScreenBrightness() {
        return screenBrightness;
    }

    public void setScreenBrightness(String screenBrightness) {
        this.screenBrightness = screenBrightness;
    }

    public String getIsScreenAutoChange() {
        return isScreenAutoChange;
    }

    public void setIsScreenAutoChange(String isScreenAutoChange) {
        this.isScreenAutoChange = isScreenAutoChange;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Screen.DENSITY_SCALE, isEmpty(densityScale));
            jsonObject.put(BaseData.Screen.DENSITY_DPI, isEmpty(densityDpi));
            jsonObject.put(BaseData.Screen.WIDTH, isEmpty(width));
            jsonObject.put(BaseData.Screen.HEIGHT, isEmpty(height));
            jsonObject.put(BaseData.Screen.IS_SCREEN_AUTO, isEmpty(isScreenAuto));
            jsonObject.put(BaseData.Screen.IS_SCREEN_AUTO_CHANGE, isEmpty(isScreenAutoChange));
            jsonObject.put(BaseData.Screen.SCREEN_BRIGHTNESS, isEmpty(screenBrightness));
            jsonObject.put(BaseData.Screen.CHECK_HIDE_STATUSBAR, isEmpty(checkHideStatusBar));
            jsonObject.put(BaseData.Screen.CHECK_HAS_NAVIGATIONBAR, isEmpty(checkHasNavigationBar));
            jsonObject.put(BaseData.Screen.GET_STATUSBAR_HEIGHT, isEmpty(getStatusBarHeight));
            jsonObject.put(BaseData.Screen.GET_NAVIGATIONBAR_HEIGHT, isEmpty(getNavigationBarHeight));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
