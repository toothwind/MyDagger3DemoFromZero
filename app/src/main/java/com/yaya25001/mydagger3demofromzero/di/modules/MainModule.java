package com.yaya25001.mydagger3demofromzero.di.modules;

import com.yaya25001.mydagger3demofromzero.model.Person;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by toothwind on 2017/4/6.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
@Module //提供依赖对象的实例 --> 工厂类
public class MainModule {

    @Provides @Singleton
        //标明此方法提供依赖对象
    Person providePerson(){

        return new Person();
    }

}
