package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.screen.ScreenHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class ScreenFragment extends BaseFragment {

    public static ScreenFragment newInstance() {
        Bundle args = new Bundle();
        ScreenFragment fragment = new ScreenFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(ScreenHelper.mobGetMobScreen(getActivity().getWindow()));
    }

    @Override
    public String getDescription() {
        return "Current phone network screen";
    }


}
