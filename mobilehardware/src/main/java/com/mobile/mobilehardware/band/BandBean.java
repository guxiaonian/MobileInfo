package com.mobile.mobilehardware.band;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class BandBean extends BaseBean {
    private static final String TAG = BandBean.class.getSimpleName();

    /**
     * 基带版本
     */
    private String baseBand;

    /**
     * 内部版本
     */
    private String innerBand;

    /**
     * linux内核版本
     */
    private String linuxBand;

    public String getBaseBand() {
        return baseBand;
    }

    public void setBaseBand(String baseBand) {
        this.baseBand = baseBand;
    }

    public String getInnerBand() {
        return innerBand;
    }

    public void setInnerBand(String innerBand) {
        this.innerBand = innerBand;
    }

    public String getLinuxBand() {
        return linuxBand;
    }

    public void setLinuxBand(String linuxBand) {
        this.linuxBand = linuxBand;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Band.BASE_BAND, isEmpty(baseBand));
            jsonObject.put(BaseData.Band.INNER_BAND, isEmpty(innerBand));
            jsonObject.put(BaseData.Band.LINUX_BAND, isEmpty(linuxBand));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
