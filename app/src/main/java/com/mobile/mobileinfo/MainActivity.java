package com.mobile.mobileinfo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobile.mobileinfo.adapter.MobPageAdapter;
import com.mobile.mobileinfo.fragment.tab.AppTabFragment;
import com.mobile.mobileinfo.fragment.tab.IdTabFragment;
import com.mobile.mobileinfo.fragment.tab.NetTabFragment;
import com.mobile.mobileinfo.fragment.tab.PhoneTabFragment;
import com.mobile.mobileinfo.fragment.tab.SafeTabFragment;
import com.mobile.mobileinfo.util.PermissionUtil;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mVp;
    private MobPageAdapter mobPageAdapter;
    private static List<Fragment> mList;
    private static List<String> titleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.checkPermission(this);
        initView();
        initData();
    }

    private void addData() {
        mList.add(SafeTabFragment.newInstance());
        mList.add(AppTabFragment.newInstance());
        mList.add(NetTabFragment.newInstance());
        mList.add(PhoneTabFragment.newInstance());
        mList.add(IdTabFragment.newInstance());
        titleList.add("safe");
        titleList.add("app");
        titleList.add("net");
        titleList.add("phone");
        titleList.add("uniqueId");
    }

    private void initData() {
        mobPageAdapter = new MobPageAdapter(getSupportFragmentManager(), mList, titleList);
        mVp.setOffscreenPageLimit(2);
        mVp.setAdapter(mobPageAdapter);
        mTab.setupWithViewPager(mVp);
    }

    private void initView() {
        mList = new ArrayList<>();
        titleList = new ArrayList<>();
        mTab = findViewById(R.id.tab_activity_main);
        mVp = findViewById(R.id.vp_activity_main);
        addData();
    }


}
