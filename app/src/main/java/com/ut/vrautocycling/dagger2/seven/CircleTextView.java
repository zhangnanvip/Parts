package com.ut.vrautocycling.dagger2.seven;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * 自定义圆形 TextView
 *
 * @author zhangnan
 */
public class CircleTextView extends View {

    private Paint mBGPaint;
    private Paint mBorderPaint;
    private Paint mTextPaint;

    private Rect mTextRect;

    private String mText = "";

    /**
     * 文本字号
     */
    private int mTextSize = 10;

    /**
     * 边框宽度
     */
    private int mBorderWidth = 1;

    /**
     * 圆心 X 坐标
     */
    private int mX;

    /**
     * 圆心 Y 坐标
     */
    private int mY;

    /**
     * 半径
     */
    private int mRadius;

    /**
     * 圆形背景色
     */
    private int mBGColor;

    /**
     * 边框颜色
     */
    private int mBorderColor;

    /**
     * 文本颜色
     */
    private int mTextColor;

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTools();
    }

    private void initTools() {
        mBGPaint = new Paint();
        mBorderPaint = new Paint();
        mTextPaint = new Paint();
        mBGPaint.setAntiAlias(true);
        mBorderPaint.setAntiAlias(true);
        mTextPaint.setAntiAlias(true);
        mTextRect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int newWidth;
        int newHeight;
        if (getPaddingLeft() < getPaddingRight()) {
            mX = (int) Math.ceil((widthSize + mBorderWidth + getPaddingLeft() * 2) / 2.0);
        } else {
            mX = (int) Math.ceil((widthSize + mBorderWidth + getPaddingRight() * 2) / 2.0);
        }
        if (getPaddingTop() < getPaddingBottom()) {
            mY = (int) Math.ceil((heightSize + mBorderWidth + getPaddingTop() * 2) / 2.0);
        } else {
            mY = (int) Math.ceil((heightSize + mBorderWidth + getPaddingBottom() * 2) / 2.0);
        }

        if (widthSize < heightSize) {
            mRadius = mX - getPaddingLeft() - mBorderWidth;
        } else {
            mRadius = mY - getPaddingTop() - mBorderWidth;
        }
        newWidth = 2 * (mRadius + mBorderWidth) + getPaddingLeft() + getPaddingRight();
        newHeight = 2 * (mRadius + mBorderWidth) + getPaddingTop() + getPaddingBottom();
        Log.d("TTT", "半径 = " + mRadius + " 边框宽 = " + mBorderWidth + " 左右边距 = " + getPaddingLeft() + " " + getPaddingRight());
        setMeasuredDimension(newWidth, newHeight);
        Log.d("TTT", newWidth + " 宽高 " + newHeight);
        Log.d("TTT", mX + " XY " + mY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("TTT", getWidth() + " 宽高 " + getHeight());
        // 绘制圆形背景
        mBGPaint.setColor(ContextCompat.getColor(getContext(), mBGColor));
        canvas.drawCircle(mX, mY, mRadius, mBGPaint);
        // 绘制边框
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        canvas.drawCircle(mX, mY, mRadius, mBorderPaint);
        // 绘制文字
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextRect);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(ContextCompat.getColor(getContext(), mTextColor));
        canvas.drawText(mText, mX, mY + (mBorderWidth + mTextRect.height()) / 2f, mTextPaint);
        Log.d("TTT", mX + " 圆心 " + mY);
        Log.d("TTT", "文本宽 = " + mTextRect.width() + " 文本高 = " + mTextRect.height()
                + " 文本XY = " + (mX - mTextRect.width() / 2f) + " " + (mY + mTextRect.height() / 2f));
    }

    public void setBGColor(@ColorRes int colorRes) {
        mBGColor = colorRes;
        invalidate();
    }

    public void setBorderWidth(int borderWidth) {
        mBorderWidth = dp2px(borderWidth);
        invalidate();
    }

    public void setBorderColor(@ColorRes int colorRes) {
        mBorderColor = colorRes;
        invalidate();
    }

    public void setTextColor(@ColorRes int colorRes) {
        mTextColor = colorRes;
        invalidate();
    }

    public void setText(String text) {
        mText = text.trim();
        invalidate();
    }

    public void setTextSize(int textSize) {
        mTextSize = dp2px(textSize);
        invalidate();
    }

    private int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getContext().getResources().getDisplayMetrics());
    }

}