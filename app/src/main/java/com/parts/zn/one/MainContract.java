package com.parts.zn.one;

import com.parts.zn.dagger.model.User;

/**
 * @author zhangnan
 * @date 2017/10/5
 */

public interface MainContract {

    interface View extends IContract.IView<Presenter> {

        void showUserInfo(User user);

    }

    interface Presenter extends IContract.IPresenter {

        void showUserInfo();

    }
}
