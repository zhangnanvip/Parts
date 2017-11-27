package com.ut.vrautocycling.dagger2.four;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ut.vrautocycling.dagger2.R;
import com.ut.vrautocycling.dagger2.one.BaseActivity;

/**
 * @author zhangnan
 */
public class FourActivity extends BaseActivity<FourContract.Presenter>
        implements FourContract.View {

    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        mTvResult = findViewById(R.id.tv_result);

        new FourPresenter(this);
        mPresenter.handleSolution();
    }

    @Override
    public void setPresenter(FourContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void handleSolutionCallBack(String result) {
        Log.i(TAG, result);
        mTvResult.setText(result);
    }
}
