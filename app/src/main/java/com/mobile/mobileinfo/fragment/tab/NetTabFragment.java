package com.mobile.mobileinfo.fragment.tab;


import android.os.Bundle;

import com.mobile.mobileinfo.fragment.HostFragment;
import com.mobile.mobileinfo.fragment.NetFragment;
import com.mobile.mobileinfo.fragment.NetWorkFragment;
import com.mobile.mobileinfo.fragment.SignalFragment;
import com.mobile.mobileinfo.fragment.SimCardFragment;
import com.mobile.mobileinfo.fragment.WifiListFragment;


public class NetTabFragment extends BaseFragment {

    public static NetTabFragment newInstance() {
        Bundle args = new Bundle();
        NetTabFragment fragment = new NetTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void addParam() {
        mList.add(NetWorkFragment.newInstance());
        mList.add(SignalFragment.newInstance());
        mList.add(NetFragment.newInstance());
        mList.add(SimCardFragment.newInstance());
        mList.add(HostFragment.newInstance());
        mList.add(WifiListFragment.newInstance());

    }

    @Override
    protected void getIntentUrl() {
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/NetWork");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Signal");
        intentUrl.add("https://github.com/guxiaonian/HttpInfo/wiki/Net");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/SimCard");
        intentUrl.add("https://github.com/guxiaonian/HttpInfo/wiki/Host");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/WifiList");

    }

    @Override
    protected void getTitleList() {
        titleList.add("network");
        titleList.add("signal");
        titleList.add("net");
        titleList.add("simCard");
        titleList.add("host");
        titleList.add("wifiList");
    }
}
