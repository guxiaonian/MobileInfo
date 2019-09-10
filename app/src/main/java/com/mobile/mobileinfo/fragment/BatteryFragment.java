package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.battery.BatteryHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class BatteryFragment extends BaseFragment {

    public static BatteryFragment newInstance() {
        Bundle args = new Bundle();
        BatteryFragment fragment = new BatteryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(BatteryHelper.mobGetBattery(getContext()));
    }


}
