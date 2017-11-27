package com.ut.vrautocycling.dagger2.one;

/**
 * @author zhangnan
 * @company YouTu
 * @date 2017/10/5
 */

public interface IContract {

    interface IView<P extends IPresenter> {
        void setPresenter(P presenter);
    }

    interface IPresenter {
    }
}
