package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.signal.SignalHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class SignalFragment extends BaseFragment {

    public static SignalFragment newInstance() {
        Bundle args = new Bundle();
        SignalFragment fragment = new SignalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(SignalHelper.mobGetNetRssi());
    }


}
