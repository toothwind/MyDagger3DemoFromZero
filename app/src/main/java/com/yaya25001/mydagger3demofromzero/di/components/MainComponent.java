package com.yaya25001.mydagger3demofromzero.di.components;

import com.yaya25001.mydagger3demofromzero.MainActivity;
import com.yaya25001.mydagger3demofromzero.di.modules.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by toothwind on 2017/4/6.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
@Singleton
@Component(modules = {MainModule.class}) //沟通部分  调用者和依赖的对象库
public interface MainComponent {

    //定义注入的方法
    void inject(MainActivity mainActivity);

//    //定义注入的方法
//    void inject(Main2Activity mainActivity);

}
