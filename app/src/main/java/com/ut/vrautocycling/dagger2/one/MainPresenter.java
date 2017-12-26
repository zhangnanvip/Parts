package com.ut.vrautocycling.dagger2.one;

import com.ut.vrautocycling.dagger2.dagger.model.User;

/**
 * @author zhangnan
 * @date 2017/10/5
 */

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    private User mUser;

    public MainPresenter(MainContract.View view, User user) {
        super(view);
        view.setPresenter(this);
        mUser = user;
    }

    @Override
    public void showUserInfo() {
        mView.showUserInfo(mUser);
    }

}
