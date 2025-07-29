package Tests.sis.testing;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {
    String[] reasons() default TestRunner.DEFAULT_IGNORE_REASON;
    //String[] value(); //Renomeado de 'value' para 'reasons
    String initials(); //Novo membro para as iniciais
    Date date();
}
