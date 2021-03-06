package com.parts.zn.four;

import com.parts.zn.one.IContract;

/**
 * @author zhangnan
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
