package com.mobile.mobileinfo.fragment;


import android.os.Bundle;

import com.mobile.mobileinfo.fragment.base.BaseNetFragment;

import fairy.easy.httpmodel.resource.HttpType;


public class NetFragment extends BaseNetFragment {

    public static NetFragment newInstance() {
        Bundle args = new Bundle();
        NetFragment fragment = new NetFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public HttpType getType() {
        return HttpType.NET;
    }
}
