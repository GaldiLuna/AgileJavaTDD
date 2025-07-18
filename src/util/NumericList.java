package util;

import java.util.ArrayList;
import java.util.List;

public class NumericList<T extends Number> {
    private List<T> data = new ArrayList<T>();

    public void initialize(int size) {
        data.clear();
        T zero = new T(0); //Não compila!
        for (int i = 0; i < size; i++)
            data.add(zero);
    }
}
