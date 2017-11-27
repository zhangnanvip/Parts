package com.ut.vrautocycling.dagger2.four;

import com.ut.vrautocycling.dagger2.one.BasePresenter;

/**
 * @author zhangnan
 * @date 2017/11/24
 */

public class FourPresenter extends BasePresenter<FourContract.View>
        implements FourContract.Presenter {

    public FourPresenter(FourContract.View view) {
        super(view);
        view.setPresenter(this);
    }

    @Override
    public void handleSolution() {
        String result = SolutionFactory.obtainSolution(1).handle();
        mView.handleSolutionCallBack(result);
    }
}
