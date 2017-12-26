package com.ut.vrautocycling.dagger2.seven.garbagecanview;

import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ut.vrautocycling.dagger2.R;

/**
 * 自定义垃圾桶 View 测试
 *
 * @author zhangnan
 */
public class FillingFragment extends Fragment
        implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private RelativeLayout mRootLayout;
    /**
     * garbage
     */
    private ImageView mIvGarbage;
    /**
     * 创建垃圾
     */
    private Button mBtnCreateGarbage;
    /**
     * 垃圾桶
     */
    private GarbageCanView mGcvGc;
    /**
     * 垃圾数量
     */
    private int mGarbageNum;

    private OnGarbageLongClickListener mOnGarbageLongClickListener;

    private ClipData.Item mItem = new ClipData.Item("111");
    private ClipData mClipData = new ClipData("222", new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, mItem);

    public FillingFragment() {

    }

    public static FillingFragment newInstance(@Nullable Bundle bundle) {
        FillingFragment fragment = new FillingFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filling, container, false);
        initView(view);
        initListener();
        return view;
    }

    private void initView(View view) {
        mRootLayout = view.findViewById(R.id.rl_root);
        mIvGarbage = view.findViewById(R.id.iv_garbage);
        mBtnCreateGarbage = view.findViewById(R.id.btn_create_garbage);
        mGcvGc = view.findViewById(R.id.gcv_gc);
    }

    private void initListener() {
        mBtnCreateGarbage.setOnClickListener(this);
        mOnGarbageLongClickListener = new OnGarbageLongClickListener();
        mIvGarbage.setOnLongClickListener(mOnGarbageLongClickListener);
        mGcvGc.setGarbageCanCleanedListener(new GarbageCanView.GarbageCanCleanedListener() {
            @Override
            public void cleaned() {
                // 垃圾桶被清空
                mGarbageNum = 0;
            }
        });
        mGcvGc.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        mIvGarbage.setVisibility(View.GONE);
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        return true;
                    case DragEvent.ACTION_DROP:
                        mGcvGc.setGarbageNum(++mGarbageNum);
                        mRootLayout.removeView(mIvGarbage);
                        mIvGarbage = null;
                        Log.d(TAG, "丢入垃圾桶");
                    case DragEvent.ACTION_DRAG_ENDED:
                        if (mIvGarbage != null) {
                            mIvGarbage.setVisibility(View.VISIBLE);
                        }
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_garbage:
                createGarbage();
                break;
            default:
                break;
        }
    }

    private void createGarbage() {
        if (mIvGarbage != null) {
            Toast.makeText(getActivity(), "亲,请不要创造过多垃圾!", Toast.LENGTH_SHORT).show();
        } else {
            mIvGarbage = new ImageView(getActivity());
            mIvGarbage.setImageResource(R.drawable.garbage);
            mIvGarbage.setOnLongClickListener(mOnGarbageLongClickListener);
            mRootLayout.addView(mIvGarbage);
        }
    }

    class OnGarbageLongClickListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mIvGarbage.startDragAndDrop(mClipData, new View.DragShadowBuilder(v), null, 0);
            } else {
                mIvGarbage.startDrag(mClipData, new View.DragShadowBuilder(v), null, 0);
            }
            return true;
        }
    }
}
