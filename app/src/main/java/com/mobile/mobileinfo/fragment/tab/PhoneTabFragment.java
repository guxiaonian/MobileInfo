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


public class PhoneTabFragment extends BaseFragment {

    public static PhoneTabFragment newInstance() {
        Bundle args = new Bundle();
        PhoneTabFragment fragment = new PhoneTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void addParam() {
        mList.add(RandomFragment.newInstance());
        mList.add(AudioFragment.newInstance());
        mList.add(BandFragment.newInstance());
        mList.add(BatteryFragment.newInstance());
        mList.add(BluetoothFragment.newInstance());
        mList.add(BuildFragment.newInstance());
        mList.add(CameraFragment.newInstance());
        mList.add(CpuFragment.newInstance());
        mList.add(LocalFragment.newInstance());
        mList.add(MemoryFragment.newInstance());
        mList.add(ScreenFragment.newInstance());
        mList.add(SDcardFragment.newInstance());
        mList.add(SettingFragment.newInstance());
        mList.add(UAFragment.newInstance());

    }

    @Override
    protected void getIntentUrl() {
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Random");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Audio");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Band");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Battery");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Bluetooth");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Build");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Camera");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Cpu");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Local");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Memory");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Screen");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/SDCard");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Settings");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/UserAgent");

    }

    @Override
    protected void getTitleList() {
        titleList.add("random");
        titleList.add("audio");
        titleList.add("band");
        titleList.add("battery");
        titleList.add("bluetooth");
        titleList.add("build");
        titleList.add("camera");
        titleList.add("cpu");
        titleList.add("local");
        titleList.add("memory");
        titleList.add("screen");
        titleList.add("sdCard");
        titleList.add("setting");
        titleList.add("ua");
    }
}
