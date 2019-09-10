package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.debug.DebugHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class DebugFragment extends BaseFragment {

    public static DebugFragment newInstance() {
        Bundle args = new Bundle();
        DebugFragment fragment = new DebugFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(DebugHelper.getDebuggingData(getContext()));
    }


}
