package com.yaya25001.mydagger3demofromzero.di.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by toothwind on 2017/4/7.
 * you can contact me at : toothwind@163.com.
 * All Rights Reserved
 */
@Qualifier //关键词
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonForContext {
}
