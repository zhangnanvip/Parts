package com.ut.vrautocycling.dagger2.one;

import android.app.Application;

import com.ut.vrautocycling.dagger2.dagger.component.AppComponent;
import com.ut.vrautocycling.dagger2.dagger.component.DaggerAppComponent;
import com.ut.vrautocycling.dagger2.dagger.module.AppModule;
import com.ut.vrautocycling.dagger2.dagger.module.AppTestModule;

/**
 * @author zhangnan
 * @date 2017/10/6
 */

public class DaggerApplication extends Application {

    private String TAG = getClass().getSimpleName();

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .appTestModule(new AppTestModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
