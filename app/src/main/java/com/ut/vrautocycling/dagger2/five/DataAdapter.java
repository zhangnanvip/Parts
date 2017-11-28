package com.ut.vrautocycling.dagger2.five;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

class DataAdapter extends BaseAdapter implements Filterable {
    private List<String> mData;

    DataAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTvText.setText(mData.get(position));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new SimpleFilter(this, mData);
    }

    class ViewHolder {
        TextView mTvText;

        ViewHolder(View view) {
            mTvText = view.findViewById(android.R.id.text1);
        }
    }
}