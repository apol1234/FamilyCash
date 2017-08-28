package com.softstyle.familycash.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by ap on 28.08.2017.
 */


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
