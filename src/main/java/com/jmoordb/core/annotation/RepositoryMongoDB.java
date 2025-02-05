package com.jmoordb.core.annotation;

import com.jmoordb.core.annotation.enumerations.ConfigEngine;
import com.jmoordb.core.annotation.enumerations.JakartaSource;
import com.jmoordb.core.annotation.enumerations.TypeReferenced;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface RepositoryMongoDB {

    /**
     * The name of the implementation that will be generated. It should be a
     * valid/unique java qualifier name
     *
     * @return
     */
//   String entity();
    Class<?> entity();

    /**
     * If true a corresponding builder (based on builder design pattern) is also
     * generated.
     *
     * @return
     */
    JakartaSource jakartaSource() default JakartaSource.JAKARTA;
    ConfigEngine configEngine() default ConfigEngine.MICROPROFILE_CONFIG;

    String commentary() default "";

}
