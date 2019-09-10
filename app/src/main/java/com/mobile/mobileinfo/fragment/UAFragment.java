package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.uniqueid.PhoneIdHelper;
import com.mobile.mobilehardware.useragent.UserAgentHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class UAFragment extends BaseFragment {

    public static UAFragment newInstance() {
        Bundle args = new Bundle();
        UAFragment fragment = new UAFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam("userAgent", UserAgentHelper.getDefaultUserAgent(getContext()));
    }


}
