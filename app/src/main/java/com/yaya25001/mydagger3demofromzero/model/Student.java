package com.yaya25001.mydagger3demofromzero.model;

import android.util.Log;

/**
 * Created by toothwind on 2017/4/7.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
        Log.d("dagger", "创建==name=" + name);
    }
}
