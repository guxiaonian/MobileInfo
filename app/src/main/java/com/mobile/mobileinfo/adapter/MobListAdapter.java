package com.mobile.mobileinfo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.mobileinfo.R;
import com.mobile.mobileinfo.data.Param;

import java.util.List;

public class MobListAdapter extends BaseAdapter {
    private Context context;
    private List<Param> params;

    public MobListAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<Param> params) {
        this.params = params;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return params == null ? 0 : params.size();
    }

    public Param getParam(int position) {
        return params == null ? null : params.get(position);
    }

    @Override
    public Object getItem(int position) {
        return params == null ? null : params.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Param param = (Param) getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.mob_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.key = view.findViewById(R.id.mob_list_tv_key);
            viewHolder.value = view.findViewById(R.id.mob_list_tv_param);
            viewHolder.iv = view.findViewById(R.id.mob_list_iv_logo);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.key.setText(param.getKey());
        Object value = param.getValue();
        if (value instanceof Drawable) {
            viewHolder.iv.setVisibility(View.VISIBLE);
            viewHolder.iv.setImageDrawable((Drawable) value);
            viewHolder.value.setVisibility(View.GONE);
        } else {
            viewHolder.iv.setVisibility(View.GONE);
            viewHolder.value.setText(param.getValue().toString());
        }
        return view;
    }

    class ViewHolder {
        TextView key;
        TextView value;
        ImageView iv;
    }
}
