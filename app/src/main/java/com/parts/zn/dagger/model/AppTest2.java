package com.parts.zn.dagger.model;

import javax.inject.Inject;

/**
 * @author zhangnan
 * @date 2017/10/6
 */

public class AppTest2 {

    private String name;

    @Inject
    public AppTest2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "{" + "\"name\":\"" + name + '\"' + '}';
//    }

}
