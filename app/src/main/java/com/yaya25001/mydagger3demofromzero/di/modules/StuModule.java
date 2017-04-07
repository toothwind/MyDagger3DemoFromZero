package com.yaya25001.mydagger3demofromzero.di.modules;

import com.yaya25001.mydagger3demofromzero.di.qualifiers.StuForName123;
import com.yaya25001.mydagger3demofromzero.di.qualifiers.StuForName456;
import com.yaya25001.mydagger3demofromzero.model.Student;

import dagger.Module;
import dagger.Provides;

/**
 * Created by toothwind on 2017/4/7.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
@Module
public class StuModule {

//    @Named("123")
    @StuForName123
    @Provides
    Student provideStudent(){
        return new Student("123");
    }

//    @Named("456")
    @StuForName456
    @Provides
    Student provideStudent2(){
        return new Student("456");
    }

}
