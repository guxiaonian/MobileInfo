package com.mobile.mobileinfo.fragment;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mobile.mobilehardware.band.BandHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;
import com.mobile.mobileinfo.oaid.AppIdsUpdater;
import com.mobile.mobileinfo.oaid.MiIdHelper;

import org.json.JSONObject;

import java.util.List;


public class OaidFragment extends BaseFragment {

    public static OaidFragment newInstance() {
        Bundle args = new Bundle();
        OaidFragment fragment = new OaidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        MiIdHelper miIdHelper = new MiIdHelper();
        return getListParam(miIdHelper.getDeviceIds(getContext()));
    }


}
