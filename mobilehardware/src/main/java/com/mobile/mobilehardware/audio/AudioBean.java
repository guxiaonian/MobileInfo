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
    private int maxVoiceCall;

    /**
     * 最小通话音量
     */
    private int minVoiceCall;

    /**
     * 当前通话音量
     */
    private int currentVoiceCall;

    /**
     * 最大系统音量
     */
    private int maxSystem;

    /**
     * 最小系统音量
     */
    private int minSystem;

    /**
     * 当前系统音量
     */
    private int currentSystem;

    /**
     * 最大铃声音量
     */
    private int maxRing;

    /**
     * 最小铃声音量
     */
    private int minRing;

    /**
     * 当前铃声音量
     */
    private int currentRing;

    /**
     * 最大音乐音量
     */
    private int maxMusic;

    /**
     * 最小音乐音量
     */
    private int minMusic;

    /**
     * 当前音乐音量
     */
    private int currentMusic;

    /**
     * 最大提示声音音量
     */
    private int maxAlarm;

    /**
     * 最小提示声音音量
     */
    private int minAlarm;

    /**
     * 当前提示声音音量
     */
    private int currentAlarm;

    /**
     * 最大通知栏提示声音
     */
    private int maxNotifications;

    /**
     * 最小通知栏提示声音
     */
    private int minNotifications;

    /**
     * 当前通知栏提示声音
     */
    private int currentNotifications;

    /**
     * 最大辅助功能提示声音
     */
    private int maxAccessibility;

    /**
     * 最小辅助功能提示声音
     */
    private int minAccessibility;

    /**
     * 当前辅助功能提示声音
     */
    private int currentAccessibility;

    /**
     * 最大拨号键提示声音
     */
    private int maxDTMF;

    /**
     * 最小拨号键提示声音
     */
    private int minDTMF;

    /**
     * 当前拨号键提示声音
     */
    private int currentDTMF;


    public AudioBean() {
    }

    public int getMaxVoiceCall() {
        return maxVoiceCall;
    }

    public void setMaxVoiceCall(int maxVoiceCall) {
        this.maxVoiceCall = maxVoiceCall;
    }

    public int getMinVoiceCall() {
        return minVoiceCall;
    }

    public void setMinVoiceCall(int minVoiceCall) {
        this.minVoiceCall = minVoiceCall;
    }

    public int getCurrentVoiceCall() {
        return currentVoiceCall;
    }

    public void setCurrentVoiceCall(int currentVoiceCall) {
        this.currentVoiceCall = currentVoiceCall;
    }

    public int getMaxSystem() {
        return maxSystem;
    }

    public void setMaxSystem(int maxSystem) {
        this.maxSystem = maxSystem;
    }

    public int getMinSystem() {
        return minSystem;
    }

    public void setMinSystem(int minSystem) {
        this.minSystem = minSystem;
    }

    public int getCurrentSystem() {
        return currentSystem;
    }

    public void setCurrentSystem(int currentSystem) {
        this.currentSystem = currentSystem;
    }

    public int getMaxRing() {
        return maxRing;
    }

    public void setMaxRing(int maxRing) {
        this.maxRing = maxRing;
    }

    public int getMinRing() {
        return minRing;
    }

    public void setMinRing(int minRing) {
        this.minRing = minRing;
    }

    public int getCurrentRing() {
        return currentRing;
    }

    public void setCurrentRing(int currentRing) {
        this.currentRing = currentRing;
    }

    public int getMaxMusic() {
        return maxMusic;
    }

    public void setMaxMusic(int maxMusic) {
        this.maxMusic = maxMusic;
    }

    public int getMinMusic() {
        return minMusic;
    }

    public void setMinMusic(int minMusic) {
        this.minMusic = minMusic;
    }

    public int getCurrentMusic() {
        return currentMusic;
    }

    public void setCurrentMusic(int currentMusic) {
        this.currentMusic = currentMusic;
    }

    public int getMaxAlarm() {
        return maxAlarm;
    }

    public void setMaxAlarm(int maxAlarm) {
        this.maxAlarm = maxAlarm;
    }

    public int getMinAlarm() {
        return minAlarm;
    }

    public void setMinAlarm(int minAlarm) {
        this.minAlarm = minAlarm;
    }

    public int getCurrentAlarm() {
        return currentAlarm;
    }

    public void setCurrentAlarm(int currentAlarm) {
        this.currentAlarm = currentAlarm;
    }

    public int getMaxNotifications() {
        return maxNotifications;
    }

    public void setMaxNotifications(int maxNotifications) {
        this.maxNotifications = maxNotifications;
    }

    public int getMinNotifications() {
        return minNotifications;
    }

    public void setMinNotifications(int minNotifications) {
        this.minNotifications = minNotifications;
    }

    public int getCurrentNotifications() {
        return currentNotifications;
    }

    public void setCurrentNotifications(int currentNotifications) {
        this.currentNotifications = currentNotifications;
    }

    public int getMaxAccessibility() {
        return maxAccessibility;
    }

    public void setMaxAccessibility(int maxAccessibility) {
        this.maxAccessibility = maxAccessibility;
    }

    public int getMinAccessibility() {
        return minAccessibility;
    }

    public void setMinAccessibility(int minAccessibility) {
        this.minAccessibility = minAccessibility;
    }

    public int getCurrentAccessibility() {
        return currentAccessibility;
    }

    public void setCurrentAccessibility(int currentAccessibility) {
        this.currentAccessibility = currentAccessibility;
    }

    public int getMaxDTMF() {
        return maxDTMF;
    }

    public void setMaxDTMF(int maxDTMF) {
        this.maxDTMF = maxDTMF;
    }

    public int getMinDTMF() {
        return minDTMF;
    }

    public void setMinDTMF(int minDTMF) {
        this.minDTMF = minDTMF;
    }

    public int getCurrentDTMF() {
        return currentDTMF;
    }

    public void setCurrentDTMF(int currentDTMF) {
        this.currentDTMF = currentDTMF;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Aduio.MAX_VOICE_CALL, maxVoiceCall);
            jsonObject.put(BaseData.Aduio.MIN_VOICE_CALL, minVoiceCall);
            jsonObject.put(BaseData.Aduio.CURRENT_VOICE_CALL, currentVoiceCall);
            jsonObject.put(BaseData.Aduio.MAX_SYSTEM, maxSystem);
            jsonObject.put(BaseData.Aduio.MIN_SYSTEM, minSystem);
            jsonObject.put(BaseData.Aduio.CURRENT_SYSTEM, currentSystem);
            jsonObject.put(BaseData.Aduio.MAX_RING, maxRing);
            jsonObject.put(BaseData.Aduio.MIN_RING, minRing);
            jsonObject.put(BaseData.Aduio.CURRENT_RING, currentRing);
            jsonObject.put(BaseData.Aduio.MAX_MUSIC, maxMusic);
            jsonObject.put(BaseData.Aduio.MIN_MUSIC, minMusic);
            jsonObject.put(BaseData.Aduio.CURRENT_MUSIC, currentMusic);
            jsonObject.put(BaseData.Aduio.MAX_ALARM, maxAlarm);
            jsonObject.put(BaseData.Aduio.MIN_ALARM, minAlarm);
            jsonObject.put(BaseData.Aduio.CURRENT_ALARM, currentAlarm);
            jsonObject.put(BaseData.Aduio.MAX_NOTIFICATIONS, maxNotifications);
            jsonObject.put(BaseData.Aduio.MIN_NOTIFICATIONS, minNotifications);
            jsonObject.put(BaseData.Aduio.CURRENT_NOTIFICATIONS, currentNotifications);
            jsonObject.put(BaseData.Aduio.MAX_ACCESSIBILITY, maxAccessibility);
            jsonObject.put(BaseData.Aduio.MIN_ACCESSIBILITY, minAccessibility);
            jsonObject.put(BaseData.Aduio.CURRENT_ACCESSIBILITY, currentAccessibility);
            jsonObject.put(BaseData.Aduio.MAX_DTMF, maxDTMF);
            jsonObject.put(BaseData.Aduio.MIN_DTMF, minDTMF);
            jsonObject.put(BaseData.Aduio.CURRENT_DTMF, currentDTMF);


        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
