package com.ut.vrautocycling.dagger2.seven.dragrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ut.vrautocycling.dagger2.R;

import java.util.List;

/**
 * @author zhangnan
 * @date 2018/1/9
 */

public class DataAdapter extends RecyclerView.Adapter {

    private List<String> mData;

    public DataAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag_tv, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DataHolder) holder).mTvData.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        TextView mTvData;

        DataHolder(View itemView) {
            super(itemView);
            itemView.setBackgroundColor(0);
            mTvData = itemView.findViewById(R.id.tv_data);
        }

    }
}
