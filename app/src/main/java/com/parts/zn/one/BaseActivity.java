package com.parts.zn.one;

import android.support.v7.app.AppCompatActivity;

/**
 * @author zhangnan
 * @date 2017/10/5
 */

public abstract class BaseActivity<P extends IContract.IPresenter> extends AppCompatActivity
        implements IContract.IView<P> {

    protected String TAG = getClass().getSimpleName();

    public P mPresenter;

}
