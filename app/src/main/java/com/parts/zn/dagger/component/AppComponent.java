package com.parts.zn.dagger.component;

import android.app.Application;

import com.parts.zn.dagger.model.AppTest1;
import com.parts.zn.dagger.model.AppTest2;
import com.parts.zn.dagger.module.AppModule;
import com.parts.zn.dagger.module.AppTestModule;
import com.parts.zn.dagger.scope.AppScope;

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
