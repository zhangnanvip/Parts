package com.ut.vrautocycling.dagger2.dagger.component;

import android.app.Application;

import com.ut.vrautocycling.dagger2.dagger.model.AppTest1;
import com.ut.vrautocycling.dagger2.dagger.model.AppTest2;
import com.ut.vrautocycling.dagger2.dagger.module.AppModule;
import com.ut.vrautocycling.dagger2.dagger.module.AppTestModule;
import com.ut.vrautocycling.dagger2.dagger.scope.AppScope;

import dagger.Component;

/**
 * @author zhangnan
 * @date 2017/10/6
 */

@AppScope
@Component(modules = {AppModule.class, AppTestModule.class})
public interface AppComponent {

    Application getApplication();

    AppTest1 getAppTest1();

    AppTest2 getAppTest2();

}
