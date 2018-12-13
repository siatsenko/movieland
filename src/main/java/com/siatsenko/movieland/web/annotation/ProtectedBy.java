package com.siatsenko.movieland.web.annotation;

import com.siatsenko.movieland.entity.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ProtectedBy {

    Role acceptedRole() default Role.GUEST;

}
