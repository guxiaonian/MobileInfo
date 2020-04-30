package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.sdcard.SDCardHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class SDcardFragment extends BaseFragment {

    public static SDcardFragment newInstance() {
        Bundle args = new Bundle();
        SDcardFragment fragment = new SDcardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(SDCardHelper.mobGetSdCard());
    }

    @Override
    public String getDescription() {
        return "Current phone sdcard information";
    }


}
