package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.app.PackageHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class AppFragment extends BaseFragment {

    public static AppFragment newInstance() {
        Bundle args = new Bundle();
        AppFragment fragment = new AppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(PackageHelper.getPackageInfo());
    }

    @Override
    public String getDescription() {
        return "Detailed information of the current APP";
    }


}
