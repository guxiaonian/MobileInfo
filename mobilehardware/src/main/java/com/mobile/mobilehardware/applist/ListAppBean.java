package com.mobile.mobilehardware.applist;

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
     * 包名
     */
    private String packageName;

    /**
     * 版本名
     */
    private String versionName;

    /**
     * 版本号
     */
    private String versionCode;

    /**
     * 是否是系统APP
     */
    private String isSystem;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.AppList.PACKAGE_NAME, isEmpty(packageName));
            jsonObject.put(BaseData.AppList.VERSION_NAME, isEmpty(versionName));
            jsonObject.put(BaseData.AppList.VERSION_CODE, isEmpty(versionCode));
            jsonObject.put(BaseData.AppList.IS_SYSTEM, isEmpty(isSystem));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
