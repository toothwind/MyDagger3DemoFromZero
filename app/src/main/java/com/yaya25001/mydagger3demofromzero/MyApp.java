package com.yaya25001.mydagger3demofromzero;

import android.app.Application;

import com.yaya25001.mydagger3demofromzero.di.components.AppComponent;
import com.yaya25001.mydagger3demofromzero.di.components.DaggerAppComponent;
import com.yaya25001.mydagger3demofromzero.di.modules.AppModule;

/**
 * Created by toothwind on 2017/4/7.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
public class MyApp extends Application {

    //AppComponent 对应着app的生命周期
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
