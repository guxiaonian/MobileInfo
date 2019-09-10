package com.mobile.mobileinfo.fragment;


import android.os.Bundle;

import com.mobile.mobilehardware.applist.ListAppHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import org.json.JSONArray;

import java.util.List;


public class AppListFragment extends BaseFragment {

    public static AppListFragment newInstance() {
        Bundle args = new Bundle();
        AppListFragment fragment = new AppListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(new JSONArray(ListAppHelper.mobListApp(getContext())));
    }


}
