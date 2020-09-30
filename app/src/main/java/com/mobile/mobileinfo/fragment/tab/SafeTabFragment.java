package com.mobile.mobileinfo.fragment.tab;


import android.os.Bundle;

import com.mobile.mobileinfo.fragment.AttestationFragment;
import com.mobile.mobileinfo.fragment.CompleteFragment;
import com.mobile.mobileinfo.fragment.DebugFragment;
import com.mobile.mobileinfo.fragment.EmulatorFragment;
import com.mobile.mobileinfo.fragment.HookFragment;
import com.mobile.mobileinfo.fragment.MoreOpenFragment;
import com.mobile.mobileinfo.fragment.RootFragment;
import com.mobile.mobileinfo.fragment.XposedFragment;


public class SafeTabFragment extends BaseFragment {

    public static SafeTabFragment newInstance() {
        Bundle args = new Bundle();
        SafeTabFragment fragment = new SafeTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void addParam() {
        mList.add(DebugFragment.newInstance());
        mList.add(EmulatorFragment.newInstance());
        mList.add(HookFragment.newInstance());
        mList.add(MoreOpenFragment.newInstance());
        mList.add(RootFragment.newInstance());
        mList.add(XposedFragment.newInstance());
        mList.add(CompleteFragment.newInstance());
        mList.add(AttestationFragment.newInstance());


    }

    @Override
    protected void getIntentUrl() {
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Debug");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Emulator");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Hook");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/MoreOpen");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Root");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Xposed");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Complete");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Attestation");

    }

    @Override
    protected void getTitleList() {
        titleList.add("debug");
        titleList.add("emulator");
        titleList.add("hook");
        titleList.add("moreOpen");
        titleList.add("root");
        titleList.add("xposed");
        titleList.add("complete");
        titleList.add("attestation");
    }
}
