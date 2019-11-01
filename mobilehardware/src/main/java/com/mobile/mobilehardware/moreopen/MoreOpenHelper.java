package com.mobile.mobilehardware.moreopen;




import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author 谷闹年
 * @date 2018/11/20
 */
public class MoreOpenHelper extends MoreOpenInfo {

    public static JSONObject checkVirtual() {
        return checkVirtualInfo(MobileHardWareHelper.getContext());
    }

}
