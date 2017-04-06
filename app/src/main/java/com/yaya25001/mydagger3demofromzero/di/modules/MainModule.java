package com.yaya25001.mydagger3demofromzero.di.modules;

import android.content.Context;

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

    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides //提供 上下文
    Context provideContext(){

        return context;
    }

    @Provides @Singleton
        //标明此方法提供依赖对象
    Person providePerson(Context context){

        return new Person(context);
    }

}
