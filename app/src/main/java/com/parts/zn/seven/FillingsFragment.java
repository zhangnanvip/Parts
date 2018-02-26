package com.parts.zn.seven;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parts.zn.R;
import com.parts.zn.seven.dragrecyclerview.DataAdapter;
import com.parts.zn.seven.dragrecyclerview.DefaultGarbageCanRecycleListener;
import com.parts.zn.seven.dragrecyclerview.RecyclerViewItemDragListener;
import com.parts.zn.seven.dragrecyclerview.WrapContentLinearLayoutManager;
import com.parts.zn.seven.garbagecanview.GarbageCanView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义垃圾桶 View 测试
 *
 * @author zhangnan
 */
public class FillingsFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();

    private RelativeLayout mRootLayout;
    /**
     * garbage list
     */
    private RecyclerView mRvgarbage;
    /**
     * 垃圾桶
     */
    private GarbageCanView mGcvGc;

    private List<String> mData;

    public FillingsFragment() {

    }

    public static FillingsFragment newInstance(@Nullable Bundle bundle) {
        FillingsFragment fragment = new FillingsFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fillings, container, false);
        initView(view);
        enableDrag();
        initListener();
        return view;
    }

    private void initView(View view) {
        // 垃圾列表
        mRvgarbage = view.findViewById(R.id.rv_garbage);
        // 根布局
        mRootLayout = view.findViewById(R.id.rl_root);
        // 垃圾桶
        mGcvGc = view.findViewById(R.id.gcv_gc);
    }

    private void enableDrag() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("" + i);
        }
        mRvgarbage.setHasFixedSize(true);
        mRvgarbage.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        DataAdapter adapter = new DataAdapter(mData);
        mRvgarbage.setAdapter(adapter);
        mRvgarbage.addOnItemTouchListener(new RecyclerViewItemDragListener(mRvgarbage));
    }

    private void initListener() {
        mGcvGc.setGarbageCanCleanedListener(new GarbageCanView.GarbageCanCleanedListener() {
            @Override
            public void cleaned() {
            }
        });
        mGcvGc.setGarbageCanRecycleListener(new DefaultGarbageCanRecycleListener<String>() {

            @Override
            public String recycled(View view, int position) {
                String removeData = mData.remove(position);
                Toast.makeText(getActivity(), removeData, Toast.LENGTH_SHORT).show();
                mRvgarbage.getAdapter().notifyItemRemoved(position);
                Log.d(TAG, removeData + " 被丢入垃圾桶");
                return removeData;
            }
        });
    }
}
