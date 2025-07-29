package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

public class ToStringer {
    private Object object;

    public ToStringer(Object object) {
        this.object = object;
    }

    public String dump() throws IllegalAccessException {
        //StringBuilder sb = new StringBuilder();
        Class<?> clazz = object.getClass(); //Obtém a classe do objeto

        //Lista para campos anotados com @Dump (e que podem ter 'order')
        List<Field> dumpFields = new ArrayList<>();
        //Lista para campos não anotados com @Dump
        List<Field> nonDumpFields = new ArrayList<>();

        //Percorre todos os campos declarados na classe
        for (Field field : clazz.getDeclaredFields()) {
            //Verifica se o campo tem a anotação @Dump
            if (field.isAnnotationPresent(Dump.class)) {
                dumpFields.add(field);
//                field.setAccessible(true); //Permite acesso a campos privados
//                sb.append(field.getName()).append("=").append(field.get(object)).append("\n");
//                //Adiciona o nome do campo 'getName()' e o valor do campo 'get(object)'
            } else {
                nonDumpFields.add(field);
            }
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

//        //Adiciona os campos ordenados ao StringBuilder
//        appendFields(sb, dumpFields);
//        //Adiciona os campos não anotados (que devem vir por último)
//        appendFields(sb, nonDumpFields);
//
//        return sb.toString();

        StringBuilder sb = new StringBuilder();
        // Adiciona os campos ordenados ao StringBuilder
        appendFields(sb, dumpFields);
        // Adiciona os campos não anotados (que devem vir por último)
        // Note: Campos não anotados não terão a anotação @Dump, então 'quote' não se aplica diretamente a eles
        // para este exercício, assumimos que 'quote' só afeta campos @Dumped.
        // Se precisar de aspas para não-anotados, teria que ser uma lógica separada ou um default global
        for (Field field : nonDumpFields) {
            field.setAccessible(true);
            sb.append(field.getName()).append("=").append(field.get(object)).append("\n");
        }

        return sb.toString();
    }

    private void appendFields(StringBuilder sb, List<Field> fields) throws IllegalAccessException {
        for (Field field : fields) {
            field.setAccessible(true); //Permite acesso a campos privados
            Object value = field.get(object);

            Dump dumpAnnotation = field.getAnnotation(Dump.class);

            String printableValue = "";
            if (value != null) {
                StringBuilder fieldOutput = new StringBuilder();
                // Itera sobre os nomes dos métodos em outputMethods
                for (String methodName : dumpAnnotation.outputMethod()) {
                    try {
                        // Tenta encontrar e invocar o metodo especificado em outputMethod
                        Method outputMethod = value.getClass().getMethod(methodName);
                        // Adiciona o resultado da invocação, separado por espaço
                        if (fieldOutput.length() > 0) {
                            fieldOutput.append(" ");
                        }
                        fieldOutput.append(outputMethod.invoke(value).toString());
                    } catch (NoSuchMethodException | InvocationTargetException e) {
                        // Se o metodo não for encontrado ou houver erro na invocação,
                        // volta para o toString() padrão ou um valor padrão.
                        System.err.println("Método '" + methodName + "' não encontrado ou erro ao invocar para " + field.getName() + ". Pulando este método. Erro: " + e.getMessage());
                        // Pode optar por adicionar o toString() padrão se um metodo falhar, ou ignorar.
                        // Para este exercício, vamos apenas pular.
                    }
                }
                printableValue = fieldOutput.toString();
                // Se nenhum metodo customizado funcionou, ou se outputMethods estava vazio/apenas toString(),
                // e o campoOutput ainda está vazio, caímos de volta ao toString() padrão.
                if (printableValue.isEmpty() && !Arrays.asList(dumpAnnotation.outputMethod()).contains("toString")) {
                    printableValue = value.toString(); // Fallback para toString se nenhum metodo customizado funcionou e toString não estava na lista
                } else if (printableValue.isEmpty() && Arrays.asList(dumpAnnotation.outputMethod()).contains("toString") && value != null) {
                    printableValue = value.toString(); // Se só tinha toString e funcionou
                }
            } else {
                printableValue = "null"; // Lida com valores nulos
            }

            sb.append(field.getName()).append("=");
            if (dumpAnnotation != null && dumpAnnotation.quote()) {
                sb.append("\"").append(value).append("\"");
            } else {
                sb.append(value);
            }
            sb.append("\n");
        }
    }
}
