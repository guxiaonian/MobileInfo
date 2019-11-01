package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.audio.AudioHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class AudioFragment extends BaseFragment {

    public static AudioFragment newInstance() {
        Bundle args = new Bundle();
        AudioFragment fragment = new AudioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(AudioHelper.mobGetMobAudio());
    }


}
