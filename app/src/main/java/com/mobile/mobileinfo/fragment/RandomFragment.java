package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.MobileNativeHelper;
import com.mobile.mobilehardware.audio.AudioHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class RandomFragment extends BaseFragment {

    public static RandomFragment newInstance() {
        Bundle args = new Bundle();
        RandomFragment fragment = new RandomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(MobileNativeHelper.getRandomData());
    }


}
