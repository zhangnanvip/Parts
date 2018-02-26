package com.parts.zn.one;

/**
 * @author zhangnan
 * @date 2017/10/5
 */

public class BasePresenter<V extends IContract.IView>
        implements IContract.IPresenter {
    public V mView;

    public BasePresenter(V view) {
        mView = view;
    }
}
