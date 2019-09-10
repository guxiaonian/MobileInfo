package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.xposed.XposedHookHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class XposedFragment extends BaseFragment {

    public static XposedFragment newInstance() {
        Bundle args = new Bundle();
        XposedFragment fragment = new XposedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(XposedHookHelper.checkXposedInjet(getContext()));
    }


}
