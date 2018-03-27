package com.parts.zn.seven.recyclerdividerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author zhangnan
 * @date 2018/3/26
 */

public class NumRecyclerViewDivider extends RecyclerView.ItemDecoration {

    public static final String TAG = NumRecyclerViewDivider.class.getSimpleName();

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private Paint mPaint;
    private Rect mBounds;

    private int mOrientation;
    private int mDividerWidth = 50;

    public NumRecyclerViewDivider(Context context, int direction) {
        mOrientation = direction;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mBounds = new Rect();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getLayoutManager() == null) {
            return;
        }
        switch (mOrientation) {
            case HORIZONTAL:
                drawHorizontal(c, parent);
                break;
            case VERTICAL:
                drawVertical(c, parent);
                break;
            default:
                break;
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        c.save();
        mPaint.setColor(Color.GRAY);
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            c.drawRect(mBounds, mPaint);
        }
        c.restore();
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        c.save();
        int width = 10;
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            String itemPosition = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition() + i + "";
            View view = parent.getChildAt(i);
            mPaint.setColor(Color.BLUE);
            Rect rect = new Rect(view.getLeft() + width, view.getTop() - mDividerWidth * 2, view.getLeft() + width * 2, view.getBottom());
            c.drawRect(rect, mPaint);
            mPaint.setColor(Color.RED);
            c.drawCircle(view.getLeft() + width + width / 2, view.getTop() - mDividerWidth / 2, width, mPaint);
            mPaint.setColor(Color.WHITE);
            float textLength = mPaint.measureText(itemPosition);
            c.drawText(itemPosition, view.getLeft() + width + width / 2 - textLength / 2, view.getTop() - mDividerWidth / 2 + textLength / 2, mPaint);
        }
        c.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        switch (mOrientation) {
            case HORIZONTAL:
                outRect.set(0, 0, mDividerWidth, 0);
                break;
            case VERTICAL:
                outRect.set(0, mDividerWidth, 0, 0);
                break;
            default:
                outRect.set(0, 0, 0, 0);
                break;
        }
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
    }

    public void setDividerWidth(int dividerWidth) {
        if (dividerWidth < 0) {
            dividerWidth = 0;
        }
        mDividerWidth = dividerWidth;
    }
}
