package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.local.LocalHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class LocalFragment extends BaseFragment {

    public static LocalFragment newInstance() {
        Bundle args = new Bundle();
        LocalFragment fragment = new LocalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(LocalHelper.mobGetMobLocal());
    }

    @Override
    public String getDescription() {
        return "Current phone local information";
    }


}
