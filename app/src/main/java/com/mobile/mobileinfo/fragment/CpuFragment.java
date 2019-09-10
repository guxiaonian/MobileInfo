package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.cpu.CpuHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class CpuFragment extends BaseFragment {

    public static CpuFragment newInstance() {
        Bundle args = new Bundle();
        CpuFragment fragment = new CpuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(CpuHelper.mobGetCpuInfo());
    }


}
