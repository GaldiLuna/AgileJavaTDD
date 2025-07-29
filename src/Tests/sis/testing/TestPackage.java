package Tests.sis.testing;

import java.lang.annotation.*;

@Target(ElementType.PACKAGE) //O alvo é um pacote
@Retention(RetentionPolicy.RUNTIME)
public @interface TestPackage {
    boolean isPerformance() default false; //Membro com valor padrão
}
