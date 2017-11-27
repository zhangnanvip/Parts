package com.ut.vrautocycling.dagger2.dagger.module;

import com.ut.vrautocycling.dagger2.dagger.model.User;
import com.ut.vrautocycling.dagger2.dagger.scope.MainActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author zhangnan
 * @company YouTu
 * @date 2017/10/5
 */

@Module
public class MainActivityModule {

    public MainActivityModule() {
    }

    @MainActivityScope
    @Provides
    public User provideUser() {
        return new User("First Dagger");
    }

}
