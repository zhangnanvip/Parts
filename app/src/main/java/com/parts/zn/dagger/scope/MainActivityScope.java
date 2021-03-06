package com.parts.zn.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author zhangnan
 * @date 2017/10/7
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface MainActivityScope {
}
