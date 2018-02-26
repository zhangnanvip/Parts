package com.parts.zn.one;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parts.zn.R;
import com.parts.zn.dagger.component.DaggerMainActivityComponent;
import com.parts.zn.dagger.model.AppTest1;
import com.parts.zn.dagger.model.AppTest2;
import com.parts.zn.dagger.model.User;
import com.parts.zn.dagger.module.MainActivityModule;
import com.parts.zn.two.TwoActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.View {

    @Inject
    User mUser;

    @Inject
    User mUserx;

    @Inject
    AppTest1 mAppTest1;

    @Inject
    AppTest1 mAppTest11;

    @Inject
    AppTest2 mAppTest2;

    @Inject
    AppTest2 mAppTest22;

    private TextView mTvToString;

    private Button mBtnToNextActivity;

    private Thread mThread;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        DaggerMainActivityComponent.builder()
                .appComponent(((DaggerApplication) getApplication()).getAppComponent())
                .mainActivityModule(new MainActivityModule())
                .build()
                .inject(this);

        new MainPresenter(this, mUser);
//        mPresenter.showUserInfo();
//        Toast.makeText(this, mAppTest1.toString(), Toast.LENGTH_LONG).show();
        mTvToString.setText(mUser.toString()
                + "\n"
                + mUserx.toString()
                + "\n" +
                mAppTest1.toString()
                + "\n"
                + mAppTest11.toString()
                + "\n" +
                mAppTest2.toString()
                + "\n"
                + mAppTest22.toString());
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "" + i++, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        mThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mThread.isAlive()) {
            mThread.interrupt();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mThread.isAlive()) {
            mThread.start();
        }
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showUserInfo(User user) {
        Toast.makeText(this, user.toString(), Toast.LENGTH_LONG).show();
    }

    private void initView() {
        mTvToString = findViewById(R.id.tv_to_string);
        mBtnToNextActivity = findViewById(R.id.btn_to_next_activity);
        mBtnToNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TwoActivity.class));
            }
        });
    }

}
