package com.ut.vrautocycling.dagger2.dagger.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * @author zhangnan
 * @company YouTu
 * @date 2017/10/6
 */

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

}
