package com.mobile.mobilehardware.moreopen;




import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;


/**
 * @author guxiaonian
 */
public class MoreOpenHelper extends MoreOpenInfo {

    public static JSONObject checkVirtual() {
        return checkVirtualInfo(MobileHardWareHelper.getContext());
    }

}
