package com.parts.zn.seven.garbagecanview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.parts.zn.R;
import com.parts.zn.seven.dragbubbleview.DragBubbleView;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 自定义垃圾桶 View 测试
 *
 * @author zhangnan
 * @date 2017/12/26
 */
public class GarbageCanView extends FrameLayout
        implements View.OnDragListener {

    private ImageView mIvGarbageCan;
    private DragBubbleView mDbvGarbageNum;

    /**
     * 回收的垃圾
     */
    private final LinkedList mGarbageList = new LinkedList();
    private final ArrayList mGarbageCopyList = new ArrayList(0);

    private GarbageCanRecycleListener mGarbageCanRecycleListener;
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
                clearGarbageCan();
                if (mGarbageCanCleanedListener != null) {
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
    private void setGarbageNum(int garbageNum) {
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

    /**
     * 获取垃圾桶中垃圾数量
     *
     * @return 垃圾数量
     */
    public int getGarbageNum() {
        return mGarbageList.size();
    }

    /**
     * 回收垃圾
     *
     * @param garbage 垃圾
     */
    public void recycle(Object garbage) {
        mGarbageList.add(garbage);
        setGarbageNum(mGarbageList.size());
    }

    /**
     * 查看垃圾桶
     */
    public ArrayList checkGarbage() {
        mGarbageCopyList.clear();
        mGarbageCopyList.addAll(mGarbageList);
        return mGarbageCopyList;
    }

    /**
     * 清空垃圾桶
     */
    public void clearGarbageCan() {
        mCleaned = true;
        mGarbageList.clear();
        setGarbageNum(mGarbageList.size());
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (mGarbageCanRecycleListener == null) {
            return false;
        }
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                mGarbageCanRecycleListener.readyRecycle();
                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
                mGarbageCanRecycleListener.startRecycle(event.getX(), event.getY());
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                mGarbageCanRecycleListener.recycling(event.getX(), event.getY());
                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                mGarbageCanRecycleListener.cancelRecycle();
                return true;
            case DragEvent.ACTION_DROP:
                int position = Integer.parseInt(event.getClipData().getItemAt(0).getText().toString());
                recycle(mGarbageCanRecycleListener.recycled(v, position));
                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                return true;
            default:
                break;
        }
        return false;
    }

    public void setGarbageCanRecycleListener(GarbageCanRecycleListener garbageCanRecycleListener) {
        mGarbageCanRecycleListener = garbageCanRecycleListener;
        setOnDragListener(this);
    }

    /**
     * 垃圾回收垃圾状态监听
     */
    public interface GarbageCanRecycleListener<T> {

        /**
         * 某个垃圾被选中
         */
        void readyRecycle();

        /**
         * 进入垃圾桶范围内
         */
        void startRecycle(float x, float y);

        /**
         * 正在向垃圾桶拖动垃圾
         */
        void recycling(float x, float y);

        /**
         * 垃圾放入垃圾桶
         */
        T recycled(View view, int position);

        /**
         * 从垃圾桶区域拖出
         */
        void cancelRecycle();

    }

    public void setGarbageCanCleanedListener(GarbageCanCleanedListener garbageCanCleanedListener) {
        mGarbageCanCleanedListener = garbageCanCleanedListener;
    }

    /**
     * 垃圾桶清空监听
     */
    public interface GarbageCanCleanedListener {

        void cleaned();

    }
}
