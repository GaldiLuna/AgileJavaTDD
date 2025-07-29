package Tests.sis.testing.compatibilidade;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MinhaAnotacao {
    String valor() default "valorPadraoV1"; //Membro com default
    //String valor(); //Membro modificado para sem default
    String infoV1(); //Membro exclusivo da V1
    //String infoV2() default "infoV2_default"; //Novo membro (se quiser testar adição)
}
