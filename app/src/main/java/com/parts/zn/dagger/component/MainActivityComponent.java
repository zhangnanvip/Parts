package com.parts.zn.dagger.component;

import com.parts.zn.one.MainActivity;
import com.parts.zn.dagger.module.MainActivityModule;
import com.parts.zn.dagger.scope.MainActivityScope;

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
