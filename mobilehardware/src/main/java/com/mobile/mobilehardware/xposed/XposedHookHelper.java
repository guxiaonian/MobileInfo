package com.mobile.mobilehardware.xposed;



import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

/**
 * @author 谷闹年
 * @date 2018/8/8
 */
public class XposedHookHelper extends XposedHookInfo {

    /**
     * 检测是否有需要的关键字
     * 如果有 则表示APP被劫持
     * 当发现有Xposed工具时，下面数据显示为：
     * "xposedInfo":{//Xposed的详细信息
     * "xposedApp"："flase",//是否hook了本APP
     * "xposedImei"："flase",//是否hook了IMEI号
     * "xposedSerial"："flase",//是否hook了序列号
     * "xposedSsid"："flase",//是否hook了SSID
     * "xposedMac"："flase",//是否hook了本MAC地址
     * "xposedAddress"："flase",//是否hook了蓝牙地址
     * "xposedAndroidId"："flase",//是否hook了AndroidId
     * "xposedImsi"："flase",//是否hook了IMSI
     * "xposedLatitude"："flase",//是否hook了纬度
     * "xposedLongitude"："flase"//是否hook了经度
     * }
     *
     * @return
     */
    public static JSONObject checkXposedInjet() {
        return xposedInjet(MobileHardWareHelper.getContext());
    }


}
