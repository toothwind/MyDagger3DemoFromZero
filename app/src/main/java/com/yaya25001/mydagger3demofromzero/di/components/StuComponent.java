package com.yaya25001.mydagger3demofromzero.di.components;

import com.yaya25001.mydagger3demofromzero.Main2Activity;
import com.yaya25001.mydagger3demofromzero.di.modules.StuModule;

import dagger.Component;

/**
 * Created by toothwind on 2017/4/7.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
@Component(modules = {StuModule.class})
public interface StuComponent {

    void inject(Main2Activity mainActivity);

}
