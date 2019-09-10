package com.mobile.mobileinfo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobile.mobileinfo.adapter.MobPageAdapter;
import com.mobile.mobileinfo.fragment.AppFragment;
import com.mobile.mobileinfo.fragment.AppListFragment;
import com.mobile.mobileinfo.fragment.AudioFragment;
import com.mobile.mobileinfo.fragment.BandFragment;
import com.mobile.mobileinfo.fragment.BatteryFragment;
import com.mobile.mobileinfo.fragment.BluetoothFragment;
import com.mobile.mobileinfo.fragment.BuildFragment;
import com.mobile.mobileinfo.fragment.CameraFragment;
import com.mobile.mobileinfo.fragment.CpuFragment;
import com.mobile.mobileinfo.fragment.DebugFragment;
import com.mobile.mobileinfo.fragment.EmulatorFragment;
import com.mobile.mobileinfo.fragment.HookFragment;
import com.mobile.mobileinfo.fragment.HostFragment;
import com.mobile.mobileinfo.fragment.IDFragment;
import com.mobile.mobileinfo.fragment.LocalFragment;
import com.mobile.mobileinfo.fragment.MemoryFragment;
import com.mobile.mobileinfo.fragment.MoreOpenFragment;
import com.mobile.mobileinfo.fragment.NetFragment;
import com.mobile.mobileinfo.fragment.NetWorkFragment;
import com.mobile.mobileinfo.fragment.RootFragment;
import com.mobile.mobileinfo.fragment.SDcardFragment;
import com.mobile.mobileinfo.fragment.ScreenFragment;
import com.mobile.mobileinfo.fragment.SettingFragment;
import com.mobile.mobileinfo.fragment.SignalFragment;
import com.mobile.mobileinfo.fragment.SimCardFragment;
import com.mobile.mobileinfo.fragment.UAFragment;
import com.mobile.mobileinfo.fragment.XposedFragment;
import com.mobile.mobileinfo.util.PermissionUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mVp;
    private MobPageAdapter mobPageAdapter;
    private static List<Fragment> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtil.checkPermission(this);
        initView();
        initData();

    }

    private void initView() {
        mList = new ArrayList<>();
        mTab = findViewById(R.id.tab_activity_main);
        mVp = findViewById(R.id.vp_activity_main);
        addParam();
        mobPageAdapter = new MobPageAdapter(getSupportFragmentManager(), mList);
    }

    private void addParam() {
        mList.add(AppFragment.newInstance());
        mList.add(AudioFragment.newInstance());
        mList.add(BandFragment.newInstance());
        mList.add(BatteryFragment.newInstance());
        mList.add(BluetoothFragment.newInstance());
        mList.add(BuildFragment.newInstance());
        mList.add(CameraFragment.newInstance());
        mList.add(CpuFragment.newInstance());
        mList.add(DebugFragment.newInstance());
        mList.add(EmulatorFragment.newInstance());
        mList.add(HookFragment.newInstance());
        mList.add(LocalFragment.newInstance());
        mList.add(MemoryFragment.newInstance());
        mList.add(MoreOpenFragment.newInstance());
        mList.add(NetWorkFragment.newInstance());
        mList.add(RootFragment.newInstance());
        mList.add(ScreenFragment.newInstance());
        mList.add(SDcardFragment.newInstance());
        mList.add(SettingFragment.newInstance());
        mList.add(SignalFragment.newInstance());
        mList.add(NetFragment.newInstance());
        mList.add(SimCardFragment.newInstance());
        mList.add(IDFragment.newInstance());
        mList.add(UAFragment.newInstance());
        mList.add(HostFragment.newInstance());
        mList.add(XposedFragment.newInstance());
        mList.add(AppListFragment.newInstance());
    }

    private void initData() {
        mVp.setAdapter(mobPageAdapter);
        mTab.setupWithViewPager(mVp);
    }


}
