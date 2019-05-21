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
    private String maxVoiceCall;

    /**
     * 当前通话音量
     */
    private String currentVoiceCall;

    /**
     * 最大系统音量
     */
    private String maxSystem;

    /**
     * 当前系统音量
     */
    private String currentSystem;

    /**
     * 最大铃声音量
     */
    private String maxRing;

    /**
     * 当前铃声音量
     */
    private String currentRing;

    /**
     * 最大音乐音量
     */
    private String maxMusic;

    /**
     * 当前音乐音量
     */
    private String currentMusic;

    /**
     * 最大提示声音音量
     */
    private String maxAlarm;

    /**
     * 当前提示声音音量
     */
    private String currentAlarm;

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

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Aduio.MAX_VOICE_CALL, isEmpty(maxVoiceCall));
            jsonObject.put(BaseData.Aduio.CURRENT_VOICE_CALL, isEmpty(currentVoiceCall));
            jsonObject.put(BaseData.Aduio.MAX_SYSTEM, isEmpty(maxSystem));
            jsonObject.put(BaseData.Aduio.CURRENT_SYSTEM, isEmpty(currentSystem));
            jsonObject.put(BaseData.Aduio.MAX_RING, isEmpty(maxRing));
            jsonObject.put(BaseData.Aduio.CURRENT_RING, isEmpty(currentRing));
            jsonObject.put(BaseData.Aduio.MAX_MUSIC, isEmpty(maxMusic));
            jsonObject.put(BaseData.Aduio.CURRENT_MUSIC, isEmpty(currentMusic));
            jsonObject.put(BaseData.Aduio.MAX_ALARM, isEmpty(maxAlarm));
            jsonObject.put(BaseData.Aduio.CURRENT_ALARM, isEmpty(currentAlarm));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
