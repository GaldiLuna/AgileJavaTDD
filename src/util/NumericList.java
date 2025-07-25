package util;

import java.util.ArrayList;
import java.util.List;

public class NumericList<T extends Number> {
    private List<T> data = new ArrayList<T>();
    // Adicionamos um campo para armazenar a referência Class<T>
    private Class<T> type;

    // O construtor agora recebe a referência Class<T>
    public NumericList(Class<T> type) {
        this.type = type;
    }

    public void initialize(int size) {
        data.clear();
//        T zero = new T(0); //Não compila!
//        for (int i = 0; i < size; i++)
//            data.add(zero);
        T zero;
        try {
            // Tentamos criar uma instância usando o construtor padrão (sem args)
            // Para Numbers, isso pode não ser ideal, pois Integer, Double, etc., não têm construtor padrão
            // Uma abordagem mais robusta seria usar um valor "zero" pré-definido ou um factory.
            // Para Integer e Double, new Integer(0) ou new Double(0.0) funcionaria, mas T zero = new T(0) não.
            // Vamos assumir aqui que você passaria Integer.class e precisaria de Integer.valueOf(0)

            // Opção 1: Usando valueOf (se o tipo tiver um metodo estático apropriado)
            if (type == Integer.class) {
                zero = (T) Integer.valueOf(0);
            } else if (type == Double.class) {
                zero = (T) Double.valueOf(0.0);
            } else if (type == Long.class) {
                zero = (T) Long.valueOf(0);
            } else if (type == Float.class) {
                zero = (T) Float.valueOf(0);
            } else {
                // Se nenhum tipo comum, tenta o construtor padrão, mas isso pode falhar.
                // Outra opção é lançar uma exceção ou exigir um factory.
                zero = type.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            // Lidar com a exceção, talvez relançá-la como uma RuntimeException
            throw new RuntimeException("Could not create zero instance for type " + type.getName(), e);
        }

        for (int i = 0; i < size; i++)
            data.add(zero);
    }

//    // No seu metodo de teste ou código cliente
//    NumericList<Integer> intList = new NumericList<>(Integer.class);
//    intList.initialize(5); // Agora funciona!
//
//    NumericList<Double> doubleList = new NumericList<>(Double.class);
//    doubleList.initialize(3); // Agora funciona!

}
