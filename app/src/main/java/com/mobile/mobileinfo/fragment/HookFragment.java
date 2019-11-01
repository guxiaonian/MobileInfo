package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.hook.HookHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class HookFragment extends BaseFragment {

    public static HookFragment newInstance() {
        Bundle args = new Bundle();
        HookFragment fragment = new HookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(HookHelper.isXposedHook());
    }


}
