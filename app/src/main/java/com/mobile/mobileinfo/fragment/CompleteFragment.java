package com.mobile.mobileinfo.fragment;

import android.os.Bundle;

import com.mobile.mobilehardware.complete.CompleteHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;

public class CompleteFragment extends BaseFragment {

    public static CompleteFragment newInstance() {
        Bundle args = new Bundle();
        CompleteFragment fragment = new CompleteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(CompleteHelper.getCompleteData());
    }

    @Override
    public String getDescription() {
        return "Safety information of complete";
    }


}
