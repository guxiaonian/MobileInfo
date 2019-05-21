package com.mobile.mobilehardware.hook;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class HookBean extends BaseBean {
    private static final String TAG = HookBean.class.getSimpleName();

    /**
     * 是否有Xposed
     */
    private String isHaveXposed;

    /**
     * 是否有Substrate
     */
    private String isHaveSubstrate;

    /**
     * 是否有Frida
     */
    private String isHaveFrida;

    public String getIsHaveXposed() {
        return isHaveXposed;
    }

    public void setIsHaveXposed(String isHaveXposed) {
        this.isHaveXposed = isHaveXposed;
    }

    public String getIsHaveSubstrate() {
        return isHaveSubstrate;
    }

    public void setIsHaveSubstrate(String isHaveSubstrate) {
        this.isHaveSubstrate = isHaveSubstrate;
    }

    public String getIsHaveFrida() {
        return isHaveFrida;
    }

    public void setIsHaveFrida(String isHaveFrida) {
        this.isHaveFrida = isHaveFrida;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Hook.IS_HAVE_XPOSED, isEmpty(isHaveXposed));
            jsonObject.put(BaseData.Hook.IS_HAVE_SUBSTRATE, isEmpty(isHaveSubstrate));
            jsonObject.put(BaseData.Hook.IS_HAVE_FRIDA, isEmpty(isHaveFrida));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
