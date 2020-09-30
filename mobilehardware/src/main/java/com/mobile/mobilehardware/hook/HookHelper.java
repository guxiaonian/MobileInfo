package com.mobile.mobilehardware.hook;



import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author guxiaonian
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
