package com.yaya25001.mydagger3demofromzero.di.modules;

import android.content.Context;

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
    Context provideContext(){
        return context;
    }

}
