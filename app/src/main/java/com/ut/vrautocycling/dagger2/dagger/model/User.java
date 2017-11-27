package com.ut.vrautocycling.dagger2.dagger.model;

import javax.inject.Inject;

public class User {

    public String name;

    @Inject
    public User(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "{" + "\"name\":\"" + name + '\"' + '}';
//    }

}