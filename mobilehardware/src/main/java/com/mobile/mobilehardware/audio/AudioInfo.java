package com.mobile.mobilehardware.audio;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
class AudioInfo {

    private static final String TAG = AudioInfo.class.getSimpleName();

    static JSONObject getAudio(Context context) {
        AudioBean mobAudioBean = new AudioBean();
        try {
            AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (mAudioManager == null) {
                return mobAudioBean.toJSONObject();
            }
            mobAudioBean.setMaxVoiceCall(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL) + "");
            mobAudioBean.setCurrentVoiceCall(mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL) + "");
            mobAudioBean.setMaxSystem(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM) + "");
            mobAudioBean.setCurrentSystem(mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM) + "");
            mobAudioBean.setMaxRing(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING) + "");
            mobAudioBean.setCurrentRing(mAudioManager.getStreamVolume(AudioManager.STREAM_RING) + "");
            mobAudioBean.setMaxMusic(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) + "");
            mobAudioBean.setCurrentMusic(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) + "");
            mobAudioBean.setMaxAlarm(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM) + "");
            mobAudioBean.setCurrentAlarm(mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM) + "");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return mobAudioBean.toJSONObject();
    }
}
