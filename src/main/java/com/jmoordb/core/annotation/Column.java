package com.jmoordb.core.annotation;

import com.jmoordb.core.annotation.enumerations.Unique;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    String value() default "";

    String commentary() default "";

    boolean generateQuery() default false;

    Unique unique() default Unique.NO;
}
