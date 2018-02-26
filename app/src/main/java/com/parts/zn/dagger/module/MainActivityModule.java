package com.parts.zn.dagger.module;

import com.parts.zn.dagger.model.User;
import com.parts.zn.dagger.scope.MainActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author zhangnan
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
