package com.mobile.mobileinfo.fragment;



import android.os.Bundle;

import com.mobile.mobilehardware.network.NetWorkHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;

import java.util.List;


public class NetWorkFragment extends BaseFragment {

    public static NetWorkFragment newInstance() {
        Bundle args = new Bundle();
        NetWorkFragment fragment = new NetWorkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<Param> addListView() {
        return getListParam(NetWorkHelper.mobGetMobNetWork());
    }


}
