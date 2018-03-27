package com.parts.zn.seven;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parts.zn.R;
import com.parts.zn.seven.dragrecyclerview.DataAdapter;
import com.parts.zn.seven.dragrecyclerview.WrapContentLinearLayoutManager;
import com.parts.zn.seven.recyclerdividerview.NumRecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 RecyclerView.ItemDecoration 模仿时间轴
 *
 * @author zhangnan
 */
public class DividerFragment extends Fragment {

    private RecyclerView mRvDivider;

    private List<String> mData;

    public DividerFragment() {

    }

    public static DividerFragment newInstance(@Nullable Bundle bundle) {
        DividerFragment fragment = new DividerFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_divider, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("" + i);
        }
        mRvDivider = view.findViewById(R.id.rv_divider);
        mRvDivider.setHasFixedSize(true);
        mRvDivider.setLayoutManager(new WrapContentLinearLayoutManager(getActivity()));
        DataAdapter adapter = new DataAdapter(mData);
        mRvDivider.setAdapter(adapter);
        mRvDivider.addItemDecoration(new NumRecyclerViewDivider(getActivity(), DividerItemDecoration.VERTICAL));
    }
}
