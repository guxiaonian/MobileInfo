package com.mobile.mobilehardware.audio;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class AudioBean extends BaseBean {
    private static final String TAG = AudioBean.class.getSimpleName();

    /**
     * 最大通话音量
     */
    private String maxVoiceCall = "0";

    /**
     * 最小通话音量
     */
    private String minVoiceCall = "0";

    /**
     * 当前通话音量
     */
    private String currentVoiceCall = "0";

    /**
     * 最大系统音量
     */
    private String maxSystem = "0";

    /**
     * 最小系统音量
     */
    private String minSystem = "0";

    /**
     * 当前系统音量
     */
    private String currentSystem = "0";

    /**
     * 最大铃声音量
     */
    private String maxRing = "0";

    /**
     * 最小铃声音量
     */
    private String minRing = "0";

    /**
     * 当前铃声音量
     */
    private String currentRing = "0";

    /**
     * 最大音乐音量
     */
    private String maxMusic = "0";

    /**
     * 最小音乐音量
     */
    private String minMusic = "0";

    /**
     * 当前音乐音量
     */
    private String currentMusic = "0";

    /**
     * 最大提示声音音量
     */
    private String maxAlarm = "0";

    /**
     * 最小提示声音音量
     */
    private String minAlarm = "0";

    /**
     * 当前提示声音音量
     */
    private String currentAlarm = "0";

    /**
     * 最大通知栏提示声音
     */
    private String maxNotifications = "0";

    /**
     * 最小通知栏提示声音
     */
    private String minNotifications = "0";

    /**
     * 当前通知栏提示声音
     */
    private String currentNotifications = "0";

    /**
     * 最大辅助功能提示声音
     */
    private String maxAccessibility = "0";

    /**
     * 最小辅助功能提示声音
     */
    private String minAccessibility = "0";

    /**
     * 当前辅助功能提示声音
     */
    private String currentAccessibility = "0";

    /**
     * 最大拨号键提示声音
     */
    private String maxDTMF = "0";

    /**
     * 最小拨号键提示声音
     */
    private String minDTMF = "0";

    /**
     * 当前拨号键提示声音
     */
    private String currentDTMF = "0";


    public AudioBean() {
    }

    public String getMaxVoiceCall() {
        return maxVoiceCall;
    }

    public void setMaxVoiceCall(String maxVoiceCall) {
        this.maxVoiceCall = maxVoiceCall;
    }

    public String getCurrentVoiceCall() {
        return currentVoiceCall;
    }

    public void setCurrentVoiceCall(String currentVoiceCall) {
        this.currentVoiceCall = currentVoiceCall;
    }

    public String getMaxSystem() {
        return maxSystem;
    }

    public void setMaxSystem(String maxSystem) {
        this.maxSystem = maxSystem;
    }

    public String getCurrentSystem() {
        return currentSystem;
    }

    public void setCurrentSystem(String currentSystem) {
        this.currentSystem = currentSystem;
    }

    public String getMaxRing() {
        return maxRing;
    }

    public void setMaxRing(String maxRing) {
        this.maxRing = maxRing;
    }

    public String getCurrentRing() {
        return currentRing;
    }

    public void setCurrentRing(String currentRing) {
        this.currentRing = currentRing;
    }

    public String getMaxMusic() {
        return maxMusic;
    }

    public void setMaxMusic(String maxMusic) {
        this.maxMusic = maxMusic;
    }

    public String getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic(String currentMusic) {
        this.currentMusic = currentMusic;
    }

    public String getMaxAlarm() {
        return maxAlarm;
    }

    public void setMaxAlarm(String maxAlarm) {
        this.maxAlarm = maxAlarm;
    }

    public String getCurrentAlarm() {
        return currentAlarm;
    }

    public void setCurrentAlarm(String currentAlarm) {
        this.currentAlarm = currentAlarm;
    }

    public String getMinVoiceCall() {
        return minVoiceCall;
    }

    public void setMinVoiceCall(String minVoiceCall) {
        this.minVoiceCall = minVoiceCall;
    }

    public String getMinSystem() {
        return minSystem;
    }

    public void setMinSystem(String minSystem) {
        this.minSystem = minSystem;
    }

    public String getMinRing() {
        return minRing;
    }

    public void setMinRing(String minRing) {
        this.minRing = minRing;
    }

    public String getMinMusic() {
        return minMusic;
    }

    public void setMinMusic(String minMusic) {
        this.minMusic = minMusic;
    }

    public String getMinAlarm() {
        return minAlarm;
    }

    public void setMinAlarm(String minAlarm) {
        this.minAlarm = minAlarm;
    }

    public String getMaxNotifications() {
        return maxNotifications;
    }

    public void setMaxNotifications(String maxNotifications) {
        this.maxNotifications = maxNotifications;
    }

    public String getMinNotifications() {
        return minNotifications;
    }

    public void setMinNotifications(String minNotifications) {
        this.minNotifications = minNotifications;
    }

    public String getCurrentNotifications() {
        return currentNotifications;
    }

    public void setCurrentNotifications(String currentNotifications) {
        this.currentNotifications = currentNotifications;
    }

    public String getMaxAccessibility() {
        return maxAccessibility;
    }

    public void setMaxAccessibility(String maxAccessibility) {
        this.maxAccessibility = maxAccessibility;
    }

    public String getMinAccessibility() {
        return minAccessibility;
    }

    public void setMinAccessibility(String minAccessibility) {
        this.minAccessibility = minAccessibility;
    }

    public String getCurrentAccessibility() {
        return currentAccessibility;
    }

    public void setCurrentAccessibility(String currentAccessibility) {
        this.currentAccessibility = currentAccessibility;
    }

    public String getMaxDTMF() {
        return maxDTMF;
    }

    public void setMaxDTMF(String maxDTMF) {
        this.maxDTMF = maxDTMF;
    }

    public String getMinDTMF() {
        return minDTMF;
    }

    public void setMinDTMF(String minDTMF) {
        this.minDTMF = minDTMF;
    }

    public String getCurrentDTMF() {
        return currentDTMF;
    }

    public void setCurrentDTMF(String currentDTMF) {
        this.currentDTMF = currentDTMF;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Aduio.MAX_VOICE_CALL, isEmpty(maxVoiceCall));
            jsonObject.put(BaseData.Aduio.MIN_VOICE_CALL, isEmpty(minVoiceCall));
            jsonObject.put(BaseData.Aduio.CURRENT_VOICE_CALL, isEmpty(currentVoiceCall));
            jsonObject.put(BaseData.Aduio.MAX_SYSTEM, isEmpty(maxSystem));
            jsonObject.put(BaseData.Aduio.MIN_SYSTEM, isEmpty(minSystem));
            jsonObject.put(BaseData.Aduio.CURRENT_SYSTEM, isEmpty(currentSystem));
            jsonObject.put(BaseData.Aduio.MAX_RING, isEmpty(maxRing));
            jsonObject.put(BaseData.Aduio.MIN_RING, isEmpty(minRing));
            jsonObject.put(BaseData.Aduio.CURRENT_RING, isEmpty(currentRing));
            jsonObject.put(BaseData.Aduio.MAX_MUSIC, isEmpty(maxMusic));
            jsonObject.put(BaseData.Aduio.MIN_MUSIC, isEmpty(minMusic));
            jsonObject.put(BaseData.Aduio.CURRENT_MUSIC, isEmpty(currentMusic));
            jsonObject.put(BaseData.Aduio.MAX_ALARM, isEmpty(maxAlarm));
            jsonObject.put(BaseData.Aduio.MIN_ALARM, isEmpty(minAlarm));
            jsonObject.put(BaseData.Aduio.CURRENT_ALARM, isEmpty(currentAlarm));
            jsonObject.put(BaseData.Aduio.MAX_NOTIFICATIONS, isEmpty(maxNotifications));
            jsonObject.put(BaseData.Aduio.MIN_NOTIFICATIONS, isEmpty(minNotifications));
            jsonObject.put(BaseData.Aduio.CURRENT_NOTIFICATIONS, isEmpty(currentNotifications));
            jsonObject.put(BaseData.Aduio.MAX_ACCESSIBILITY, isEmpty(maxAccessibility));
            jsonObject.put(BaseData.Aduio.MIN_ACCESSIBILITY, isEmpty(minAccessibility));
            jsonObject.put(BaseData.Aduio.CURRENT_ACCESSIBILITY, isEmpty(currentAccessibility));
            jsonObject.put(BaseData.Aduio.MAX_DTMF, isEmpty(maxDTMF));
            jsonObject.put(BaseData.Aduio.MIN_DTMF, isEmpty(minDTMF));
            jsonObject.put(BaseData.Aduio.CURRENT_DTMF, isEmpty(currentDTMF));


        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
