package com.springclass.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IsLockable {

//    void lock(String pattern);
//
//    String getLockPattern();
//
//    boolean matches(String methodName);

} // The End...
