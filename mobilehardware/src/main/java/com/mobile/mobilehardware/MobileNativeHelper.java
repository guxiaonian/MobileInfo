package com.mobile.mobilehardware;


import org.json.JSONException;
import org.json.JSONObject;

public class MobileNativeHelper implements MobileInterface {

    static {
        System.loadLibrary("fairymob-lib");
    }


    public static native String bootIdC();

    public static native String entropyAvailC();

    public static native String poolSizeC();

    public static native String readWakeupThresholdC();

    public static native String writeWakeupThresholdC();

    public static native String uuidC();

    public static native String uRandomMinReseedSecsC();

    public static native String kennel();

    public static native String getBuildInfo64(String param);

    public static native String getBuildInfo256(String param);

    public static JSONObject getRandomData(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("bootId",bootIdC());
            jsonObject.put("entropyAvail",entropyAvailC());
            jsonObject.put("poolSize",poolSizeC());
            jsonObject.put("readWakeupThreshold",readWakeupThresholdC());
            jsonObject.put("writeWakeupThreshold",writeWakeupThresholdC());
            jsonObject.put("uuid",uuidC());
            jsonObject.put("uRandomMinReseedSecs",uRandomMinReseedSecsC());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static native int checkMoreOpenByUid();

    public static native int checkSubstrateBySo();

    public static native String checkHookByMap();

    public static native String checkHookByPackage();
}
