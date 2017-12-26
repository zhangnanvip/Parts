package com.ut.vrautocycling.dagger2.dagger.component;

import com.ut.vrautocycling.dagger2.one.MainActivity;
import com.ut.vrautocycling.dagger2.dagger.module.MainActivityModule;
import com.ut.vrautocycling.dagger2.dagger.scope.MainActivityScope;

import dagger.Component;

/**
 * @author zhangnan
 * @date 2017/10/5
 */

@MainActivityScope
@Component(dependencies = AppComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}
