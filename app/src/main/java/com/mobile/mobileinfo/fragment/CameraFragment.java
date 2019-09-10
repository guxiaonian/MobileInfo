package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.camera.CameraHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import org.json.JSONException;

import java.util.List;


public class CameraFragment extends BaseFragment {

    public static CameraFragment newInstance() {
        Bundle args = new Bundle();
        CameraFragment fragment = new CameraFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        try {
            return getListParam(CameraHelper.getCameraInfo(getContext()).getJSONArray("cameraInfo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
