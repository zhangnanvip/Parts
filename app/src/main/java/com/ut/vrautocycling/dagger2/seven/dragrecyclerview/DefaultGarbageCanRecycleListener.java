package com.ut.vrautocycling.dagger2.seven.dragrecyclerview;

import android.view.View;

import com.ut.vrautocycling.dagger2.seven.garbagecanview.GarbageCanView;

/**
 * @author zhangnan
 * @date 2018/1/19
 */

public class DefaultGarbageCanRecycleListener<T> implements GarbageCanView.GarbageCanRecycleListener {
    @Override
    public void readyRecycle() {

    }

    @Override
    public void startRecycle(float x, float y) {

    }

    @Override
    public void recycling(float x, float y) {

    }

    @Override
    public T recycled(View view, int position) {
        return null;
    }

    @Override
    public void cancelRecycle() {

    }
}
