package Tests.ExpTestes;

import junit.framework.TestCase;

import java.util.List;
import java.util.ArrayList;

public class TesteArrays extends TestCase {
    public void testArrays() {
        List<?>[] namesTable = new List<?>[100]; // Isso compila!
        Object[] objects = (Object[])namesTable;
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        objects[0] = numbers;
        try {
            String name = (String)namesTable[0].get(0); // Exige cast, pode lançar ClassCastException
        }
        catch (ClassCastException expected) {
            // Captura a exceção esperada
        }
    }
}
