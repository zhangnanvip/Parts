package com.ut.vrautocycling.dagger2.dagger.module;

import com.ut.vrautocycling.dagger2.dagger.model.AppTest1;
import com.ut.vrautocycling.dagger2.dagger.model.AppTest2;
import com.ut.vrautocycling.dagger2.dagger.scope.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author zhangnan
 * @date 2017/10/6
 */

@Module
public class AppTestModule {

    @Provides
    public AppTest1 provideAppTest1() {
        AppTest1 appTest1 = new AppTest1();
        appTest1.setName("AppTest1");
        return appTest1;
    }

    @AppScope
    @Provides
    public AppTest2 provideAppTest2() {
        AppTest2 appTest2 = new AppTest2();
        appTest2.setName("AppTest2");
        return appTest2;
    }

}
