package com.mobile.mobilehardware.applist;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class ListAppBean extends BaseBean {

    private static final String TAG = ListAppBean.class.getSimpleName();

    /**
     * app名字
     */
    private String appName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 包签名
     */
    private String packageSign;

    /**
     * 版本号
     */
    private long appVersionCode;

    /**
     * 版本名字
     */
    private String appVersionName;

    /**
     * 目标系统版本号
     */
    private int targetSdkVersion;

    /**
     * 最低系统版本号
     **/
    private int minSdkVersion;

    /**
     * 描述
     */
    private CharSequence description;

    /**
     * 图标
     */
    private Drawable icon;

    /**
     * 是否是系统APP
     */
    private boolean isSystem;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageSign() {
        return packageSign;
    }

    public void setPackageSign(String packageSign) {
        this.packageSign = packageSign;
    }

    public long getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(long appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public int getTargetSdkVersion() {
        return targetSdkVersion;
    }

    public void setTargetSdkVersion(int targetSdkVersion) {
        this.targetSdkVersion = targetSdkVersion;
    }

    public int getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(int minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public CharSequence getDescription() {
        return description;
    }

    public void setDescription(CharSequence description) {
        this.description = description;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.App.APP_NAME, isEmpty(appName));
            jsonObject.put(BaseData.App.PACKAGE_NAME, isEmpty(packageName));
            jsonObject.put(BaseData.App.PACKAGE_SIGN, isEmpty(packageSign));
            jsonObject.put(BaseData.App.APP_VERSION_CODE, appVersionCode);
            jsonObject.put(BaseData.App.APP_VERSION_NAME, isEmpty(appVersionName));
            jsonObject.put(BaseData.App.APP_TARGET_SDK_VERSION, targetSdkVersion);
            jsonObject.put(BaseData.App.APP_MIN_SDK_VERSION, minSdkVersion);
            jsonObject.put(BaseData.App.APP_DESCRIPTION, isEmpty(description));
            jsonObject.put(BaseData.App.APP_ICON, icon);
            jsonObject.put(BaseData.AppList.IS_SYSTEM, isSystem);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
