package com.mobile.mobilehardware.app;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class PackageBean extends BaseBean {

    private static final String TAG = PackageBean.class.getSimpleName();
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
    private String appVersionCode;

    /**
     * 版本名字
     */
    private String appVersionName;

    /**
     * 目标系统版本号
     */
    private String targetSdkVersion;

    /**
     * 最低系统版本号
     **/
    private String minSdkVersion;

    public String getTargetSdkVersion() {
        return targetSdkVersion;
    }

    public void setTargetSdkVersion(String targetSdkVersion) {
        this.targetSdkVersion = targetSdkVersion;
    }

    public String getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(String minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

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

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.App.APP_NAME, isEmpty(appName));
            jsonObject.put(BaseData.App.PACKAGE_NAME, isEmpty(packageName));
            jsonObject.put(BaseData.App.PACKAGE_SIGN, isEmpty(packageSign));
            jsonObject.put(BaseData.App.APP_VERSION_CODE, isEmpty(appVersionCode));
            jsonObject.put(BaseData.App.APP_VERSION_NAME, isEmpty(appVersionName));
            jsonObject.put(BaseData.App.APP_TARGET_SDK_VERSION, isEmpty(targetSdkVersion));
            jsonObject.put(BaseData.App.APP_MIN_SDK_VERSION, isEmpty(minSdkVersion));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
