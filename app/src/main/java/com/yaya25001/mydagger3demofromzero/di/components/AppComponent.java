package com.yaya25001.mydagger3demofromzero.di.components;

import android.content.Context;

import com.yaya25001.mydagger3demofromzero.di.modules.AppModule;
import com.yaya25001.mydagger3demofromzero.di.scopes.PerApp;

import dagger.Component;

/**
 * Created by toothwind on 2017/4/6.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
@PerApp //module添加该标记 此处也要加
@Component(modules = AppModule.class)
public interface AppComponent {

    //向下层提供context
    Context proContext();

}
