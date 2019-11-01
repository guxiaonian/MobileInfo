package com.mobile.mobilehardware.audio;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
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
            mobAudioBean.setMaxVoiceCall(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));
            mobAudioBean.setCurrentVoiceCall(mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL));

            mobAudioBean.setMaxSystem(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
            mobAudioBean.setCurrentSystem(mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM));

            mobAudioBean.setMaxRing(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING));
            mobAudioBean.setCurrentRing(mAudioManager.getStreamVolume(AudioManager.STREAM_RING));

            mobAudioBean.setMaxMusic(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            mobAudioBean.setCurrentMusic(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            mobAudioBean.setMaxAlarm(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM));
            mobAudioBean.setCurrentAlarm(mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM));

            mobAudioBean.setMaxNotifications(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION));
            mobAudioBean.setCurrentNotifications(mAudioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION));

            mobAudioBean.setMaxDTMF(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_DTMF));
            mobAudioBean.setCurrentDTMF(mAudioManager.getStreamVolume(AudioManager.STREAM_DTMF));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mobAudioBean.setMaxAccessibility(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ACCESSIBILITY));
                mobAudioBean.setCurrentAccessibility(mAudioManager.getStreamVolume(AudioManager.STREAM_ACCESSIBILITY));
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                mobAudioBean.setMinDTMF(mAudioManager.getStreamMinVolume(AudioManager.STREAM_DTMF));
                mobAudioBean.setMinNotifications(mAudioManager.getStreamMinVolume(AudioManager.STREAM_NOTIFICATION));
                mobAudioBean.setMinAlarm(mAudioManager.getStreamMinVolume(AudioManager.STREAM_ALARM));
                mobAudioBean.setMinMusic(mAudioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC));
                mobAudioBean.setMinRing(mAudioManager.getStreamMinVolume(AudioManager.STREAM_RING));
                mobAudioBean.setMinSystem(mAudioManager.getStreamMinVolume(AudioManager.STREAM_SYSTEM));
                mobAudioBean.setMinVoiceCall(mAudioManager.getStreamMinVolume(AudioManager.STREAM_VOICE_CALL));
                mobAudioBean.setMinAccessibility(mAudioManager.getStreamMinVolume(AudioManager.STREAM_ACCESSIBILITY));
            }

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return mobAudioBean.toJSONObject();
    }
}
