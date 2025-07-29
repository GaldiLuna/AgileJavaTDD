package Tests.sis.testing.compatibilidade;

import org.junit.Ignore;

import java.lang.reflect.Method;
import java.lang.annotation.AnnotationFormatError; //Para capturar o erro
import java.lang.reflect.InvocationTargetException;

//@Ignore("Classe ignorada por motivos de manutenção")
//public class CompatibilidadeTeste {
//
//    @Ignore("Método ignorado por motivos de manutenção")
//    public static void main(String[] args) throws Exception {
//        //Habilita asserções para este teste
//        assert true;
//
//        try {
//            //Carrega a classe que foi COMPILADA com a VERSÂO ANTIGA da anotação
//            Class<?> classeAntiga = Class.forName("Tests.sis.testing.compatibilidade.ClasseComAnotacao");
//            Method metodo = classeAntiga.getMethod("metodoComAnotacao");
//
//            //Tenta obter a anotação usando a nova definição de MinhaAnotacao (que está no classpath atual)
//            MinhaAnotacao an = metodo.getAnnotation(MinhaAnotacao.class);
//
//            if (an != null) {
//                System.out.println("Anotação encontrada. Tentando acessar membros:");
//                try {
//                    //Tentando acessar o membro 'valor' que agora NÃO TEM default (se a classe antiga foi compilada sem o default removido)
//                    String valor = an.valor();
//                    System.out.println("Valor do membro 'valor': " + valor);
//                } catch (Exception e) {
//                    System.out.println("Erro ao acessar 'valor' (esperado se default removido): " + e.getMessage());
//                    assert e instanceof IncompatibleClassChangeError || e instanceof AnnotationFormatError : "Esperado erro ao acessar membro sem default";
//                }
//
//                try {
//                    // Tentando acessar o membro 'infoV1' que foi REMOVIDO
//                    // Isso DEVE causar uma exceção ao tentar carregar a anotação
//                    // ou ao tentar acessá-lo via reflexão
//                    // O compilador não deixaria você chamar an.infoV1() aqui diretamente
//                    // Se você conseguir chegar aqui, o erro será em tempo de execução
//                    // via o próprio Class.getAnnotation que falharia
//                } catch (Exception e) {
//                    System.out.println("Erro ao acessar 'infoV1' (esperado se membro removido): " + e.getMessage());
//                    assert e instanceof IncompatibleClassChangeError || e instanceof AnnotationFormatError : "Esperado erro ao acessar membro removido";
//                }
//
//                //Tentando acessar um novo membro adicionado na V2
//                try {
//                    String infoV2 = an.infoV2();
//                    System.out.println("Valor do membro 'infoV2' (novo membro): " + infoV2);
//                } catch (Exception e) {
//                    System.out.println("Erro ao acessar 'infoV2' (esperado se V1 não tinha): " + e.getMessage());
//                    assert e instanceof IncompatibleClassChangeError || e instanceof AnnotationFormatError : "Esperado erro ao acessar membro com anotação antiga";
//                }
//            } else {
//                System.out.println("Anotação MinhaAnotacao não encontrada no método. Isso pode indicar um problema de retenção.");
//            }
//        } catch (AnnotationFormatError e) {
//            System.err.println("SUCCESS: Erro esperado de formato de anotação (AnnotationFormatError): " + e.getMessage());
//            // Este é o erro esperado quando a estrutura da anotação no .class não corresponde
//            // à definição atual no classpath (ex: membro removido ou tipo alterado)
//        } catch (IncompatibleClassChangeError e) {
//            System.err.println("SUCCESS: Erro esperado de mudança incompatível de classe (IncompatibleClassChangeError): " + e.getMessage());
//            // Este também é um erro comum quando o bytecode da anotação está inconsistente
//        }
//        catch (Exception e) {
//            System.err.println("Outro tipo de erro ocorreu: " + e.getClass().getName() + " - " + e.getMessage());
//            e.printStackTrace();
//            System.exit(1); //Falha no teste
//        }
//        System.out.println("Teste de compatibilidade concluído.");
//    }
//}

//OUTRA VERSÃO PARA O CÓDIGO DESSE ARQUIVO
/**
 * package Tests.sis.testing.compatibilidade;
 *
 * import java.lang.reflect.Method;
 * import java.lang.annotation.AnnotationFormatError;
 * import java.lang.reflect.InvocationTargetException;
 *
 * public class CompatibilidadeTeste {
 *
 *     public static void main(String[] args) { // Removendo 'throws Exception' aqui e tratando no catch
 *         // Habilita asserções para este teste
 *         assert true;
 *
 *         try {
 *             // Carrega a classe que foi COMPILADA com a VERSÃO ANTIGA da anotação
 *             Class<?> classeAntiga = Class.forName("Tests.sis.testing.compatibilidade.ClasseComAnotacao");
 *             Method metodo = classeAntiga.getMethod("metodoComAnotacao");
 *
 *             // Tenta obter a anotação usando a NOVA definição de MinhaAnotacao
 *             // (que está no classpath atual, pois ela será recompilada agora)
 *             MinhaAnotacao an = null;
 *             try {
 *                  an = metodo.getAnnotation(MinhaAnotacao.class);
 *             } catch (Throwable t) { // Captura Throwable para ser mais abrangente aqui
 *                  // Este é o cenário mais provável de erro se a V2 for incompatível com a V1
 *                  // A JVM tenta carregar a anotação do bytecode V1 com a estrutura V2
 *                  System.err.println("SUCCESS: Erro esperado (AnnotationFormatError ou IncompatibleClassChangeError) ao obter a anotação: " + t.getClass().getName() + " - " + t.getMessage());
 *                  System.exit(0); // Sucesso, pois era o erro esperado
 *             }
 *
 *
 *             if (an != null) {
 *                 System.out.println("Anotação encontrada. Tentando acessar membros:");
 *
 *                 // Cenario 1: Acessar membro 'valor' que perdeu o default na V2
 *                 try {
 *                     // Se 'ClasseComAnotacao' foi compilada com MinhaAnotacao V1 e usou o default,
 *                     // e agora MinhaAnotacao V2 não tem default e nenhum valor foi especificado,
 *                     // isso pode causar um erro em tempo de execução ao acessar 'an.valor()'.
 *                     // Se a V1 tinha valor explícito para 'valor', isso ainda pode funcionar.
 *                     String valor = an.valor();
 *                     System.out.println("Valor do membro 'valor': " + valor);
 *                 } catch (Throwable t) { // Captura Throwable para ser mais abrangente
 *                     System.out.println("Erro ao acessar 'valor' (esperado se default foi removido e não havia valor explícito na V1): " + t.getClass().getName() + " - " + t.getMessage());
 *                     assert t instanceof IncompatibleClassChangeError || t instanceof AnnotationFormatError || t instanceof NullPointerException : "Esperado erro ao acessar membro sem default";
 *                 }
 *
 *                 // Cenario 2: Acessar membro 'infoV2' que foi ADICIONADO na V2
 *                 // ClasseComAnotacao foi compilada com V1, que NÃO tinha infoV2.
 *                 try {
 *                     String infoV2 = an.infoV2();
 *                     System.out.println("Valor do membro 'infoV2' (novo membro): " + infoV2);
 *                 } catch (Throwable t) { // Captura Throwable para ser mais abrangente
 *                     System.out.println("Erro ao acessar 'infoV2' (esperado, pois a classe foi compilada com anotação V1 sem este membro): " + t.getClass().getName() + " - " + t.getMessage());
 *                     assert t instanceof IncompatibleClassChangeError || t instanceof AnnotationFormatError : "Esperado erro ao acessar novo membro com anotação antiga";
 *                 }
 *             } else {
 *                 System.out.println("Anotação MinhaAnotacao não encontrada no método. Isso indica um problema de retenção ou incompatibilidade inicial.");
 *             }
 *         } catch (Throwable t) { // Captura qualquer erro inesperado aqui
 *             System.err.println("Outro tipo de erro inesperado ocorreu: " + t.getClass().getName() + " - " + t.getMessage());
 *             t.printStackTrace();
 *             System.exit(1); // Falha no teste
 *         }
 *         System.out.println("Teste de compatibilidade concluído.");
 *     }
 * }
 */