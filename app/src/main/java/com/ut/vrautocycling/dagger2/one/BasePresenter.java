package com.ut.vrautocycling.dagger2.one;

/**
 * @author zhangnan
 * @company YouTu
 * @date 2017/10/5
 */

public class BasePresenter<V extends IContract.IView>
        implements IContract.IPresenter {
    public V mView;

    public BasePresenter(V view) {
        mView = view;
    }
}
