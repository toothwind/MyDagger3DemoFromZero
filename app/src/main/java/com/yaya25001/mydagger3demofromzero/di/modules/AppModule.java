package com.yaya25001.mydagger3demofromzero.di.modules;

import android.content.Context;

import com.yaya25001.mydagger3demofromzero.di.scopes.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by toothwind on 2017/4/6.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    //这里提供context
    @Provides
    @PerApp
    //添加该标记标明 该方法只产生一个 实例
    Context provideContext(){
        return context;
    }

}
