package com.mobile.mobilehardware.sdcard;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class SDCardBean extends BaseBean {
    private static final String TAG = SDCardBean.class.getSimpleName();

    /**
     * sd card 是否可用
     */
    private String isSDCardEnable;

    /**
     * sd card路径
     */
    private String sDCardPath;

    public String getIsSDCardEnable() {
        return isSDCardEnable;
    }

    public void setIsSDCardEnable(String isSDCardEnable) {
        this.isSDCardEnable = isSDCardEnable;
    }

    public String getsDCardPath() {
        return sDCardPath;
    }

    public void setsDCardPath(String sDCardPath) {
        this.sDCardPath = sDCardPath;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.SDCard.IS_SDCARD_ENABLE, isEmpty(isSDCardEnable));
            jsonObject.put(BaseData.SDCard.SDCARD_PATH, isEmpty(sDCardPath));

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }


}
