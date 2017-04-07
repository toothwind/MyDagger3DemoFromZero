package com.yaya25001.mydagger3demofromzero.model;

import android.content.Context;
import android.util.Log;

/**
 * Created by toothwind on 2017/4/6.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
public class Person {

    private Context context;

    //    @Inject
    public Person(Context context) {
        this.context = context;
        Log.d("dagger", "person  被创建了~");
    }
}
