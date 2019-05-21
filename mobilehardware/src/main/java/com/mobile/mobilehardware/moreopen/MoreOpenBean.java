package com.mobile.mobilehardware.moreopen;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class MoreOpenBean extends BaseBean {
    private static final String TAG = MoreOpenBean.class.getSimpleName();

    /**
     * 检测私有路径判断是否有多开
     */
    private String checkByPrivateFilePath;

    /**
     * maps检测
     */
    private String checkByMultiApkPackageName;

    /**
     * ps检测
     */
    private String checkByHasSameUid;

    public String getCheckByPrivateFilePath() {
        return checkByPrivateFilePath;
    }

    public void setCheckByPrivateFilePath(String checkByPrivateFilePath) {
        this.checkByPrivateFilePath = checkByPrivateFilePath;
    }

    public String getCheckByMultiApkPackageName() {
        return checkByMultiApkPackageName;
    }

    public void setCheckByMultiApkPackageName(String checkByMultiApkPackageName) {
        this.checkByMultiApkPackageName = checkByMultiApkPackageName;
    }

    public String getCheckByHasSameUid() {
        return checkByHasSameUid;
    }

    public void setCheckByHasSameUid(String checkByHasSameUid) {
        this.checkByHasSameUid = checkByHasSameUid;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.MoreOpen.CHECK_BY_PRIVATE_FILE_PATH, isEmpty(checkByPrivateFilePath));
            jsonObject.put(BaseData.MoreOpen.CHECK_BY_MULTIAPK_PACKAGE_NAME, isEmpty(checkByMultiApkPackageName));
            jsonObject.put(BaseData.MoreOpen.CHECK_BY_HAS_SAMEUID, isEmpty(checkByHasSameUid));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
