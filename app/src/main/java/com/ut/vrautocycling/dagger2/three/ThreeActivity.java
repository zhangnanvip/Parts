package com.ut.vrautocycling.dagger2.three;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ut.vrautocycling.dagger2.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangnan
 * @company YouTu
 * @date 2017/11/21
 */

public class ThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_flow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FlowAdapter());
    }

    private class FlowAdapter extends RecyclerView.Adapter {

        private final int mFlowSize = 3;
        private int mDataSize = 2;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            mDataSize++;
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_flow, parent, false);
            return new FlowHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mFlowSize;
        }

        class FlowHolder extends RecyclerView.ViewHolder {
            private TagFlowLayout mTagFlowLayout;
            private List<Integer> data;

            FlowHolder(final View itemView) {
                super(itemView);
                data = new ArrayList<>();
                for (int i = 0; i < mDataSize; i++) {
                    data.add(i);
                }
                mTagFlowLayout = itemView.findViewById(R.id.tfl_data);
                mTagFlowLayout.setMaxSelectCount(1);
                mTagFlowLayout.setAdapter(new TagAdapter<Integer>(data) {
                    @Override
                    public View getView(FlowLayout parent, int position, Integer integer) {
                        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.tv, mTagFlowLayout, false);
                        textView.setText(integer + "");
                        return textView;
                    }
                });
                mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        String data = ((TextView) view.findViewById(R.id.tv_data)).getText().toString();
                        Toast.makeText(parent.getContext(), data, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        }
    }
}
