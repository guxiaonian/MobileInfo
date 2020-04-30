package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.band.BandHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class BandFragment extends BaseFragment {

    public static BandFragment newInstance() {
        Bundle args = new Bundle();
        BandFragment fragment = new BandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(BandHelper.mobGetBandInfo());
    }

    @Override
    public String getDescription() {
        return "Current phone band information";
    }


}
