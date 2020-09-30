package com.mobile.mobileinfo.fragment.tab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mobile.mobileinfo.R;
import com.mobile.mobileinfo.adapter.MobPageAdapter;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseFragment extends Fragment {
    private TabLayout mTab;
    private ViewPager mVp;
    private MobPageAdapter mobTabAdapter;
    public List<Fragment> mList;
    public List<String> intentUrl;
    public List<String> titleList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mList = new ArrayList<>();
        intentUrl = new ArrayList<>();
        titleList = new ArrayList<>();
        mTab = view.findViewById(R.id.tab_fragment_tb);
        mVp = view.findViewById(R.id.tab_fragment_vp);
        addParam();
        getIntentUrl();
        getTitleList();
        mobTabAdapter = new MobPageAdapter(getChildFragmentManager(), mList, titleList);
        mVp.setAdapter(mobTabAdapter);
        mTab.setupWithViewPager(mVp);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        initView();
        return view;
    }

    private void initView() {
        for (int i = 0; i < mTab.getTabCount(); i++) {
            TabLayout.Tab tab = mTab.getTabAt(i);
            if (tab == null) return;
            Class c = tab.getClass();
            try {
                Field field = c.getDeclaredField("mView");
                field.setAccessible(true);
                final View view = (View) field.get(tab);
                if (view == null) return;
                int index = i;
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Uri uri = Uri.parse(intentUrl.get(index));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        return false;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    protected abstract void addParam();

    protected abstract void getIntentUrl();

    protected abstract void getTitleList();


}
