package com.mobile.mobileinfo.fragment.tab;


import android.os.Bundle;

import com.mobile.mobileinfo.fragment.AudioFragment;
import com.mobile.mobileinfo.fragment.BandFragment;
import com.mobile.mobileinfo.fragment.BatteryFragment;
import com.mobile.mobileinfo.fragment.BluetoothFragment;
import com.mobile.mobileinfo.fragment.BuildFragment;
import com.mobile.mobileinfo.fragment.CameraFragment;
import com.mobile.mobileinfo.fragment.CpuFragment;
import com.mobile.mobileinfo.fragment.IDFragment;
import com.mobile.mobileinfo.fragment.LocalFragment;
import com.mobile.mobileinfo.fragment.MemoryFragment;
import com.mobile.mobileinfo.fragment.OaidFragment;
import com.mobile.mobileinfo.fragment.RandomFragment;
import com.mobile.mobileinfo.fragment.SDcardFragment;
import com.mobile.mobileinfo.fragment.ScreenFragment;
import com.mobile.mobileinfo.fragment.SettingFragment;
import com.mobile.mobileinfo.fragment.UAFragment;


public class IdTabFragment extends BaseFragment {

    public static IdTabFragment newInstance() {
        Bundle args = new Bundle();
        IdTabFragment fragment = new IdTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void addParam() {
        mList.add(OaidFragment.newInstance());
        mList.add(IDFragment.newInstance());

    }

    @Override
    protected void getIntentUrl() {
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Oaid");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/PhoneId");

    }

    @Override
    protected void getTitleList() {
        titleList.add("oaid");
        titleList.add("phoneId");
    }
}
