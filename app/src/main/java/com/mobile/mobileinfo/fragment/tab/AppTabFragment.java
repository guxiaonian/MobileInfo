package com.mobile.mobileinfo.fragment.tab;


import android.os.Bundle;

import com.mobile.mobileinfo.fragment.AppFragment;
import com.mobile.mobileinfo.fragment.AppListFragment;


public class AppTabFragment extends BaseFragment {

    public static AppTabFragment newInstance() {
        Bundle args = new Bundle();
        AppTabFragment fragment = new AppTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void addParam() {
        mList.add(AppFragment.newInstance());
        mList.add(AppListFragment.newInstance());

    }

    @Override
    protected void getIntentUrl() {
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/Package");
        intentUrl.add("https://github.com/guxiaonian/MobileInfo/wiki/AppList");

    }

    @Override
    protected void getTitleList() {
        titleList.add("app");
        titleList.add("appList");
    }
}
