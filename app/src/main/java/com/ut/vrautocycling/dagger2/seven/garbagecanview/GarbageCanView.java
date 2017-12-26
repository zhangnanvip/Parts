package com.ut.vrautocycling.dagger2.seven.garbagecanview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ut.vrautocycling.dagger2.R;
import com.ut.vrautocycling.dagger2.seven.dragbubbleview.DragBubbleView;

/**
 * 自定义垃圾桶 View 测试
 *
 * @author zhangnan
 * @date 2017/12/26
 */
public class GarbageCanView extends FrameLayout {

    private ImageView mIvGarbageCan;
    private DragBubbleView mDbvGarbageNum;

    private GarbageCanCleanedListener mGarbageCanCleanedListener;

    private int mGarbageNum;
    /**
     * 已被清空
     */
    private boolean mCleaned;

    public GarbageCanView(@NonNull Context context) {
        this(context, null);
    }

    public GarbageCanView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GarbageCanView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_garbage_can, this);
        initView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GarbageCanView, defStyleAttr, 0);
        // 初始化垃圾数量
        mGarbageNum = typedArray.getInt(R.styleable.GarbageCanView_garbageNum, 0);
        setGarbageNum(mGarbageNum);
        // 不对布局内视图在超出布局时剪切
        setClipChildren(false);
        typedArray.recycle();
    }

    private void initView() {
        mIvGarbageCan = findViewById(R.id.iv_garbage_can);
        mDbvGarbageNum = findViewById(R.id.dbv_garbage_num);
        mDbvGarbageNum.setOnBubbleStateListener(new DragBubbleView.OnBubbleStateListener() {
            @Override
            public void onDrag() {
                Log.e("---> ", "拖拽气泡");
            }

            @Override
            public void onMove() {
                Log.e("---> ", "移动气泡");
            }

            @Override
            public void onRestore() {
                Log.e("---> ", "气泡恢复原来位置");
            }

            @Override
            public void onDismiss() {
                if (mGarbageCanCleanedListener != null) {
                    mCleaned = true;
                    mGarbageCanCleanedListener.cleaned();
                }
                Log.e("---> ", "气泡消失");
            }
        });
    }

    /**
     * 设置垃圾桶内垃圾数量
     *
     * @param garbageNum 垃圾数量
     */
    public void setGarbageNum(int garbageNum) {
        mGarbageNum = garbageNum;
        if (mGarbageNum == 0) {
            mDbvGarbageNum.setVisibility(GONE);
        } else {
            if (mCleaned) {
                mDbvGarbageNum.reCreate();
                mCleaned = false;
            }
            mDbvGarbageNum.setVisibility(VISIBLE);
            mDbvGarbageNum.setText(mGarbageNum + "");
        }
    }

    public void setGarbageCanCleanedListener(GarbageCanCleanedListener garbageCanCleanedListener) {
        mGarbageCanCleanedListener = garbageCanCleanedListener;
    }

    public interface GarbageCanCleanedListener {

        void cleaned();

    }
}
