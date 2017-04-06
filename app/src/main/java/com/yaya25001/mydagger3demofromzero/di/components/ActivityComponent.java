package com.yaya25001.mydagger3demofromzero.di.components;

import com.yaya25001.mydagger3demofromzero.MainActivity;
import com.yaya25001.mydagger3demofromzero.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by toothwind on 2017/4/6.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
@Component(dependencies = AppComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {

    //子component
    void inject(MainActivity mainActivity);

}
