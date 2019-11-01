package com.mobile.mobileinfo.fragment.base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.mobileinfo.R;
import com.mobile.mobileinfo.adapter.MobListAdapter;
import com.mobile.mobileinfo.data.Param;
import com.mobile.mobileinfo.util.OkHttpUrlLoader;
import com.mobile.mobileinfo.util.StringUtil;
import com.mobile.mobileinfo.util.ThreadPoolUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fairy.easy.httpmodel.HttpModelHelper;
import fairy.easy.httpmodel.resource.HttpListener;
import fairy.easy.httpmodel.resource.HttpType;

import static android.content.Context.CLIPBOARD_SERVICE;

public abstract class BaseNetFragment extends Fragment {


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private MobListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app, container, false);
        mListView = view.findViewById(R.id.lv_mob);
        mSwipeRefreshLayout = view.findViewById(R.id.spl_mob);
        onSwipeRefreshLayout();
        adapter = new MobListAdapter(getActivity());
        mListView.setAdapter(adapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    ClipData clipData = ClipData.newPlainText("Label", (String) adapter.getParam(position).getKey()+"\b"+((adapter.getParam(position).getValue() instanceof String)?(String) adapter.getParam(position).getValue():""));
                    ((ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(clipData);
                    Toast.makeText(getContext(), "copy success", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();

                }
                return true;
            }
        });

        return view;
    }

    private void onSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getParam();
            }
        });
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                getParam();

            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
    }

    public abstract HttpType getType();


    private  void getParam() {
        HttpModelHelper.getInstance()
                .init(getContext())
                .setChina(false)
                .setModelLoader(new OkHttpUrlLoader())
                .setFactory()
                .addType(getType())
                .build()
                .startAsync("https://www.baidu.com", new HttpListener() {
                    @Override
                    public void onFail(String data) {
                        adapter.addAll(getListParam(getType().getName(),data));
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFinish(JSONObject result) {

                    }

                    @Override
                    public void onSuccess(HttpType httpType, JSONObject result) {
                        adapter.addAll(getListParam(result));
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    public List<Param> getListParam(String key, Object value) {
        List<Param> list = new ArrayList<>();
        Param param = new Param();
        param.setValue(value + "");
        param.setKey(key);
        list.add(param);
        return list;
    }

    public List<Param> getListParam(JSONObject jsonObject) {
        List<Param> list = new ArrayList<>();
        try {
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                Param param = new Param();
                String key = (String) iterator.next();
                param.setKey(key);
                param.setValue(StringUtil.formatString(jsonObject.getString(key)));
                list.add(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
