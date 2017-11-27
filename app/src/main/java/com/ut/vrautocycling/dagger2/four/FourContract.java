package com.ut.vrautocycling.dagger2.four;

import com.ut.vrautocycling.dagger2.one.IContract;

/**
 * @author zhangnan
 * @company YouTu
 * @date 2017/11/24
 */

public interface FourContract {

    interface Presenter extends IContract.IPresenter {

        /**
         * 执行解决方案
         */
        void handleSolution();

    }

    interface View extends IContract.IView<Presenter> {

        /**
         * 执行结果
         *
         * @param result 执行结果
         */
        void handleSolutionCallBack(String result);
    }

}
