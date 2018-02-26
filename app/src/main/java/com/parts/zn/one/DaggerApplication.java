package com.parts.zn.one;

import android.app.Application;

import com.parts.zn.dagger.component.AppComponent;
import com.parts.zn.dagger.component.DaggerAppComponent;
import com.parts.zn.dagger.module.AppModule;
import com.parts.zn.dagger.module.AppTestModule;

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
