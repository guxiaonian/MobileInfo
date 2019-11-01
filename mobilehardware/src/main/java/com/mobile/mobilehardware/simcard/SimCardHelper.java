package com.mobile.mobilehardware.simcard;

import android.content.Context;

import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/8/6
 * 运营商获取类
 */
public class SimCardHelper extends SimCardInfo {

    /**
     * 获取网络卡信息
     *
     * @return 运营商返回
     */
    public static JSONObject mobileSimInfo() {
        return getMobSimInfo(MobileHardWareHelper.getContext());
    }
}
