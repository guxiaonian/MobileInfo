package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.build.BuildHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class BuildFragment extends BaseFragment {

    public static BuildFragment newInstance() {
        Bundle args = new Bundle();
        BuildFragment fragment = new BuildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(BuildHelper.mobGetBuildInfo());
    }

    @Override
    public String getDescription() {
        return "Current phone \"android.os.Build\" information";
    }


}
