package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ToStringer {
    private Object object;

    public ToStringer(Object object) {
        this.object = object;
    }

    public String dump() throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = object.getClass();

        List<Field> dumpFields = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Dump.class)) {
                dumpFields.add(field);
            }
        }

        Collections.sort(dumpFields, new Comparator<Field>() {
            @Override
            public int compare(Field f1, Field f2) {
                Dump d1 = f1.getAnnotation(Dump.class);
                Dump d2 = f2.getAnnotation(Dump.class);
                return Integer.compare(d1.order(), d2.order());
            }
        });

        appendFields(sb, dumpFields);

        return sb.toString();
    }

    private void appendFields(StringBuilder sb, List<Field> fields) throws IllegalAccessException {
        for (Field field : fields) {
            field.setAccessible(true); //Permite acesso a campos privados
            Object fieldValue = field.get(object); //O valor real do campo

            Dump dumpAnnotation = field.getAnnotation(Dump.class);

            String printableValue = "";

            if (fieldValue == null) {
                printableValue = "null";
            } else {
                StringBuilder fieldOutputBuilder = new StringBuilder();
                boolean anyMethodSucceeded = false;

                // Priorize outputMethods se especificados
                if (dumpAnnotation.outputMethods() != null && dumpAnnotation.outputMethods().length > 0) {
                    boolean firstMethodResult = true;
                    // Loop através dos métodos especificados na anotação
                    // Tenta aplicar cada metodo de outputMethods AO VALOR DO CAMPO (fieldValue)
                    for (String methodName : dumpAnnotation.outputMethods()) {
                        try {
                            // Tenta obter o metodo da CLASSE DO VALOR DO CAMPO
                            if (!fieldValue.getClass().isPrimitive() && !(fieldValue instanceof String)) {
                                Method outputMethod = fieldValue.getClass().getMethod(methodName);
                            } else if (methodName.equals("toString")) {
                                Method outputMethod = fieldValue.getClass().getMethod("toString");
                            }
                            Method outputMethod = fieldValue.getClass().getMethod(methodName);
                            Object methodResult = outputMethod.invoke(fieldValue); // Invoca no VALOR do campo

                            if (fieldOutputBuilder.length() > 0) {
                                fieldOutputBuilder.append(" "); //Adiciona espaço entre os resultados
                            }
                            fieldOutputBuilder.append(methodResult != null ? methodResult.toString() : "null");
                            anyMethodSucceeded = true;

                        } catch (NoSuchMethodException e) {
                            System.err.println("Aviso: Método '" + methodName + "' não encontrado na classe " + fieldValue.getClass().getSimpleName() + " para campo '" + field.getName() + "'. " + e.getMessage());
                            // Se um metodo específico não for encontrado, podemos querer que os outros ainda sejam tentados.
                            // Não há necessidade de throw aqui, apenas avisa e continua.);
                        } catch (InvocationTargetException e) {
                            System.err.println("Aviso: Erro ao invocar método '" + methodName + "' para campo '" + field.getName() + "'. Causa: " + e.getCause().getMessage());
                            // Similarmente, apenas avisa.
                        }
                    }

                    // Se nenhum metodo especificado em outputMethods produziu saída, ou se outputMethods era vazio/nulo
                    if (!anyMethodSucceeded || fieldOutputBuilder.isEmpty()) {
                        // Fallback para toString() padrão
                        printableValue = fieldValue.toString();
                    } else {
                        printableValue = fieldOutputBuilder.toString();
                    }
                }

                sb.append(field.getName()).append("=");
                if (dumpAnnotation.quote()) {
                    sb.append("\"").append(printableValue).append("\"");
                } else {
                    sb.append(printableValue);
                }
                sb.append("\n");
            }
        }
    }
}


/**
    private Object object;

    public ToStringer(Object object) {
        this.object = object;
    }

    public String dump() throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = object.getClass(); //Obtém a classe do objeto

        //Lista para campos anotados com @Dump (e que podem ter 'order')
        List<Field> dumpFields = new ArrayList<>();
        //Lista para campos não anotados com @Dump
//        List<Field> nonDumpFields = new ArrayList<>();
        //Linha acima comentada porque ToStringer deve usar apenas campos anotados @Dump

        //Percorre todos os campos declarados na classe
        for (Field field : clazz.getDeclaredFields()) {
            //Verifica se o campo tem a anotação @Dump
            if (field.isAnnotationPresent(Dump.class)) {
                dumpFields.add(field);
//                field.setAccessible(true); //Permite acesso a campos privados
//                sb.append(field.getName()).append("=").append(field.get(object)).append("\n");
//                //Adiciona o nome do campo 'getName()' e o valor do campo 'get(object)'
            } //else {
//                nonDumpFields.add(field);
//            }
        }
//        return sb.toString();

        //Ordena os campos com @Dump pelo parâmetro 'order'
        Collections.sort(dumpFields, new Comparator<Field>() {
            @Override
            public int compare(Field f1, Field f2) {
                Dump d1 = f1.getAnnotation(Dump.class);
                Dump d2 = f2.getAnnotation(Dump.class);
                //Assume que ambos terão anotação @Dump para este comparador
                return Integer.compare(d1.order(), d2.order());
            }
        });

        //Adiciona os campos ordenados ao StringBuilder
        appendFields(sb, dumpFields);
//        //Adiciona os campos não anotados (que devem vir por último)
//        appendFields(sb, nonDumpFields);
//
//        return sb.toString();

//        StringBuilder sb = new StringBuilder();
//        // Adiciona os campos ordenados ao StringBuilder
//        appendFields(sb, dumpFields);
//        // Adiciona os campos não anotados (que devem vir por último)
//        // Note: Campos não anotados não terão a anotação @Dump, então 'quote' não se aplica diretamente a eles
//        // para este exercício, assumimos que 'quote' só afeta campos @Dumped.
//        // Se precisar de aspas para não-anotados, teria que ser uma lógica separada ou um default global
//        for (Field field : nonDumpFields) {
//            field.setAccessible(true);
//            sb.append(field.getName()).append("=").append(field.get(object)).append("\n");
//        }

        return sb.toString();
    }

    private void appendFields(StringBuilder sb, List<Field> fields) throws IllegalAccessException {
        for (Field field : fields) {
            field.setAccessible(true); //Permite acesso a campos privados
            Object fieldValue = field.get(object);

            Dump dumpAnnotation = field.getAnnotation(Dump.class);

            String printableValue = "";
            if (fieldValue != null) {
                StringBuilder fieldOutput = new StringBuilder();
                boolean firstMethod = true;

//UMA DAS VERSÕES ANTERIORES - ANOTADA PAR CONSULTA
                // Para cada methodName especificado em outputMethods, itera sobre os nomes dos métodos em outputMethods e percorre todos os nomes de métodos especificados na anotação
                for (String methodName : dumpAnnotation.outputMethods()) {
                    try {
                        // Tenta encontrar o metodo NO TIPO DO VALOR DO CAMPO (fieldValue)
                        // Apenas se o fieldValue não for um tipo primitivo ou String (que não tem métodos customizados assim)
                        Method outputMethod = null;
                        if (!fieldValue.getClass().isPrimitive() && !(fieldValue instanceof String)) {
                            outputMethod = fieldValue.getClass().getMethod(methodName);
                        } else if (methodName.equals("toString")) { // toString é sempre válido para qualquer objeto
                            outputMethod = fieldValue.getClass().getMethod("toString");
                        }

                        if (outputMethod != null) {
                            Object methodResult = outputMethod.invoke(fieldValue); // INVOCA NO VALOR DO CAMPO
                            if (!firstMethod) {
                                fieldOutput.append(" ");
                            }
                            fieldOutput.append(methodResult != null ? methodResult.toString() : "null");
                            firstMethod = false;
                        } else {
                            // Se for String ou primitivo e o metodo não for toString, ou se o metodo não existir
                            System.err.println("Metodo '" + methodName + "' não aplicável ou não encontrado para campo '" + field.getName() + "' do tipo " + fieldValue.getClass().getSimpleName() + ". Pulando este método.");
                        }

                    } catch (NoSuchMethodException e) {
                        System.err.println("Metodo '" + methodName + "' não encontrado na classe " + fieldValue.getClass().getSimpleName() + " para campo " + field.getName() + ". Pulando este método. Erro: " + e.getMessage());
                    } catch (InvocationTargetException e) {
                        System.err.println("Erro ao invocar metodo '" + methodName + "' para campo " + field.getName() + ". Erro: " + e.getCause().getMessage());
                    }
                }
                printableValue = fieldOutput.toString();

                // Fallback final: se NADA foi produzido pelos métodos customizados, usa o toString() padrão do fieldValue
                if (printableValue.isEmpty() && fieldValue != null) {
                    printableValue = fieldValue.toString();
                }

            } else {
                printableValue = "null";
            }

            sb.append(field.getName()).append("=");
            if (dumpAnnotation.quote()) {
                sb.append("\"").append(printableValue).append("\"");
            } else {
                sb.append(printableValue);
            }
            sb.append("\n");

//UMA DAS VERSÕES ANTERIORES - ANOTADA PAR CONSULTA
                        // IMPORTANTE: O metodo é chamado no OBJETO PRINCIPAL (this.object)
                        // Tenta encontrar e invocar o metodo especificado em outputMethods
                        Method outputMethods = object.getClass().getMethod(methodName);
                        Object methodResult = outputMethods.invoke(object);

                        if (!firstMethod) {
                            fieldOutput.append(" "); // Adiciona espaço entre os resultados
                        }
                        fieldOutput.append(methodResult != null ? methodResult.toString() : "null");
                        firstMethod = false;

                        // Adiciona o resultado da invocação, separado por espaço
                        if (fieldOutput.length() > 0) {
                            fieldOutput.append(" ");
                        }
                        fieldOutput.append(outputMethods.invoke(fieldValue).toString());
                    } catch (NoSuchMethodException e) {
                        // Se o metodo não for encontrado, tentar o toString() padrão do fieldValue
                        // Isso é um fallback inteligente.
                        System.err.println("Metodo '" + methodName + "' não encontrado na classe " + object.getClass().getSimpleName() + " para campo " + field.getName() + ". Erro: " + e.getMessage());

                        if (Arrays.asList(dumpAnnotation.outputMethods()).size() == 1 && methodName.equals("toString")) {
                            printableValue = fieldValue.toString(); // Se só tinha toString e deu erro, usa ele
                        } else {
                            // Se era um metodo customizado, apenas pule ou lide de outra forma.
                            // Por enquanto, apenas pulamos se não for o toString padrão.
                            System.err.println("Pulando metodo " + methodName + " para o campo " + field.getName());
                            // Se a lista de métodos for só ["toString"] e falhar, o printableValue ficará vazio.
                            // O último 'if' no final de 'appendFields' tratará isso.
                        }
                        // Pode optar por adicionar o toString() padrão se um metodo falhar, ou ignorar.
                        // Para este exercício, vamos apenas pular.
                    } catch (InvocationTargetException e) {
                        System.err.println("Erro ao invocar metodo '" + methodName + "' para campo " + field.getName() + ". Erro: " + e.getCause().getMessage());
                        // Lidar com exceções lançadas pelo metodo invocado
                        // Similarmente, pode cair no toString() padrão
                    }
                }
                printableValue = fieldOutput.toString();
                // Se nenhum metodo customizado funcionou, ou se outputMethods estava vazio/apenas toString(),
                // e o campoOutput ainda está vazio, caímos de volta ao toString() padrão.
                // Fallback final: se nenhum metodo funcionou ou resultou em string vazia,
                // e o outputMethods não era explicitamente vazio para suprimir, use toString() do fieldValue
                if (printableValue.isEmpty() && (dumpAnnotation.outputMethods().length == 0 || (dumpAnnotation.outputMethods().length == 1 && dumpAnnotation.outputMethods()[0].equals("toString")) && fieldValue != null)) {
                    printableValue = fieldValue.toString(); // Fallback para toString se nenhum metodo customizado funcionou e toString não estava na lista
                } else if (printableValue.isEmpty() && fieldValue != null) {
                    // Caso onde múltiplos métodos foram especificados, mas todos falharam ou retornaram vazio
                    printableValue = "FAILED_OUTPUT_METHOD_CALL"; // Sinaliza que algo deu errado
                }
            } else {
                printableValue = "null"; // Lida com valores nulos
            }

            sb.append(field.getName()).append("=");
            if (dumpAnnotation.quote()) {
                sb.append("\"").append(printableValue).append("\"");
            } else {
                sb.append(printableValue);
            }
            sb.append("\n");

        }
    }
 */

