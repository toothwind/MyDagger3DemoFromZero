package com.yaya25001.mydagger3demofromzero.di.modules;

import android.content.Context;

import com.yaya25001.mydagger3demofromzero.model.Person;

import dagger.Module;
import dagger.Provides;

/**
 * Created by toothwind on 2017/4/6.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
@Module
public class ActivityModule {

    @Provides
    Person providePerson(Context context){
        return new Person(context);
    }

}
