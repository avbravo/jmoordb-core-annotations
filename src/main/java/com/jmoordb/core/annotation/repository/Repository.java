package com.jmoordb.core.annotation.repository;

import com.jmoordb.core.annotation.enumerations.ConfigEngine;
import com.jmoordb.core.annotation.enumerations.JakartaSource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Repository {

   Class<?> entity();

    JakartaSource jakartaSource() default JakartaSource.JAKARTA;
    ConfigEngine configEngine() default ConfigEngine.MICROPROFILE_CONFIG;

    String collection() default "";

    /**
     * database_name --> Es un nombre de base de datos que indique el
     * desarrollador {mongodb.database} --> Es el parametro en
     * Microorofile-config.properties
     *
     * @return
     */
    String database() default "{mongodb.database}";

}
