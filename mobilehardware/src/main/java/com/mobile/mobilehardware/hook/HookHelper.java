package com.mobile.mobilehardware.hook;



import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/8/8
 * Xposed  CydiaSubstrate 框架检测工具类
 */
public class HookHelper extends HookInfo {


    /**
     * 判断是否有xposed等hook工具
     *
     * @return
     */
    public static JSONObject isXposedHook() {
        return getXposedHook(MobileHardWareHelper.getContext());
    }


}
