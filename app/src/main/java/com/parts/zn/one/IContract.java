package com.parts.zn.one;

/**
 * @author zhangnan
 * @date 2017/10/5
 */

public interface IContract {

    interface IView<P extends IPresenter> {
        void setPresenter(P presenter);
    }

    interface IPresenter {
    }
}
