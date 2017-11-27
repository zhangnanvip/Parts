package com.ut.vrautocycling.dagger2.two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ut.vrautocycling.dagger2.R;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView rvSnap = findViewById(R.id.rv_snap);

        SimpleAdapter simpleAdapter = new SimpleAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rvSnap.setHasFixedSize(true);
        rvSnap.setLayoutManager(layoutManager);
        rvSnap.setAdapter(simpleAdapter);
    }

    private class SimpleAdapter extends RecyclerView.Adapter {

        /**
         * 显示条数
         */
        private final int mSimpleNum = 20;
        /**
         * ViewHolder 创建次数
         */
        private int mCreateTimes = 0;
        /**
         * ViewHolder 绑定次数
         */
        private int mBindTimes = 0;
        /**
         * 已经绑定文本变化监听器
         */
        private final boolean mBoundWatcher = true;
        /**
         * 输入框文本缓存
         */
        private SparseArray<String> mTextCache = new SparseArray<>();

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_simpe, parent, false);
            Log.i("onCreateViewHolder", "CreateTimes == " + mCreateTimes++);
            return new SimpleHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            Log.i("onBindViewHolder", "BindTimes == " + mBindTimes++ + " Position == " + holder.getAdapterPosition());
            if (mTextCache.get(holder.getAdapterPosition()) != null) {
                Log.i("TEXT_SHOW", "ShowPosition == " + holder.getAdapterPosition());
                ((SimpleHolder) holder).mEtSimple.setText(mTextCache.get(holder.getAdapterPosition()));
            } else {
                ((SimpleHolder) holder).mEtSimple.setText("");
            }
            // 如果已经绑定文本变化监听器不再次绑定
            if (((SimpleHolder) holder).mEtSimple.getTag() != null && (boolean) ((SimpleHolder) holder).mEtSimple.getTag()) {
                return;
            }
            ((SimpleHolder) holder).mEtSimple.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    // 如果填入数据与缓存数据相同返回
                    if (TextUtils.equals(mTextCache.get(holder.getAdapterPosition()), s.toString())) {
                        return;
                    }
                    mTextCache.put(holder.getAdapterPosition(), s.toString());
                    Log.i("TEXT_PUT", "PutPosition == " + holder.getAdapterPosition());
                }
            });
            ((SimpleHolder) holder).mEtSimple.setTag(mBoundWatcher);
        }

        @Override
        public int getItemCount() {
            return mSimpleNum;
        }

        class SimpleHolder extends RecyclerView.ViewHolder {

            EditText mEtSimple;

            SimpleHolder(View itemView) {
                super(itemView);
                mEtSimple = itemView.findViewById(R.id.et_simple);
            }
        }
    }
}
