package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.simcard.SimCardHelper;
import com.mobile.mobilehardware.uniqueid.PhoneIdHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class IDFragment extends BaseFragment {

    public static IDFragment newInstance() {
        Bundle args = new Bundle();
        IDFragment fragment = new IDFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam("uniqueID", PhoneIdHelper.getUniqueID());
    }


}
