package util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Dump {
    int order() default Integer.MAX_VALUE; //Valor padrão alto para ordenar por último
    boolean quote() default false;
    String[] outputMethods() default {"toString"};
}
