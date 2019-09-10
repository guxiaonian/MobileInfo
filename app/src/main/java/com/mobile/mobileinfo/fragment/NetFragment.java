package com.mobile.mobileinfo.fragment;


import android.os.Bundle;

import com.mobile.mobilehardware.applist.ListAppHelper;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.fragment.base.BaseFragment;
import com.mobile.mobileinfo.fragment.base.BaseNetgment;
import com.mobile.mobileinfo.util.OkHttpUrlLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import fairy.easy.httpmodel.HttpModelHelper;
import fairy.easy.httpmodel.model.HttpNormalUrlLoader;
import fairy.easy.httpmodel.resource.HttpListener;
import fairy.easy.httpmodel.resource.HttpType;


public class NetFragment extends BaseNetgment {

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
