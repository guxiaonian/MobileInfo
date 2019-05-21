package com.mobile.mobilehardware.debug;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class DebugBean extends BaseBean {
    private static final String TAG = DebugBean.class.getSimpleName();

    /**
     * 是否开启了调试模式
     */
    private String isOpenDebug;

    /**
     * 是否是Debug版本
     */
    private String isDebugVersion;

    /**
     * 是否正在调试
     */
    private String isDebugging;

    /**
     * 读取id判断是否在调试
     */
    private String isReadProcStatus;

    /**
     * 是否可以打开位置模拟
     */
    private String isAllowMockLocation;

    public String getIsOpenDebug() {
        return isOpenDebug;
    }

    public void setIsOpenDebug(String isOpenDebug) {
        this.isOpenDebug = isOpenDebug;
    }

    public String getIsDebugVersion() {
        return isDebugVersion;
    }

    public void setIsDebugVersion(String isDebugVersion) {
        this.isDebugVersion = isDebugVersion;
    }

    public String getIsDebugging() {
        return isDebugging;
    }

    public void setIsDebugging(String isDebugging) {
        this.isDebugging = isDebugging;
    }

    public String getIsReadProcStatus() {
        return isReadProcStatus;
    }

    public void setIsReadProcStatus(String isReadProcStatus) {
        this.isReadProcStatus = isReadProcStatus;
    }

    public String getIsAllowMockLocation() {
        return isAllowMockLocation;
    }

    public void setIsAllowMockLocation(String isAllowMockLocation) {
        this.isAllowMockLocation = isAllowMockLocation;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Debug.IS_OPEN_DEBUG, isEmpty(isOpenDebug));
            jsonObject.put(BaseData.Debug.IS_DEBUG_VERSION, isEmpty(isDebugVersion));
            jsonObject.put(BaseData.Debug.IS_DEBUGGING, isEmpty(isDebugging));
            jsonObject.put(BaseData.Debug.IS_READ_PROC_STATUS, isEmpty(isReadProcStatus));
            jsonObject.put(BaseData.Debug.IS_ALLOW_MOCK_LOCATION, isEmpty(isAllowMockLocation));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
