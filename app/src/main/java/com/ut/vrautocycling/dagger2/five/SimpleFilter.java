package com.ut.vrautocycling.dagger2.five;

import android.widget.BaseAdapter;
import android.widget.Filter;

import java.util.List;

class SimpleFilter extends Filter {

    private BaseAdapter mAdapter;
    private List<String> mData;

    SimpleFilter(BaseAdapter adapter, List<String> data) {
        mAdapter = adapter;
        mData = data;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        List<String> values = Matcher.match(Matcher.CONTAINS,constraint, mData);
        results.values = values;
        results.count = values.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        // 有数据进行更新
        if (results.count > 0) {
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.notifyDataSetInvalidated();
        }
    }
}