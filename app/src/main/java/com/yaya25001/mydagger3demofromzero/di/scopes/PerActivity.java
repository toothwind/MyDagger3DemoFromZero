package com.yaya25001.mydagger3demofromzero.di.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by toothwind on 2017/4/7.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
