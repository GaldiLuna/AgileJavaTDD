package Tests.ExpTestes;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

public class TesteCheckedCollections extends TestCase {
    public void testCheckedCollections() {
        List ages = Collections.checkedList(new ArrayList<>(), Integer.class);
        try {
            ages.add("17");
            fail("expected ClassCastException on invalid insert");
        }
        catch (ClassCastException success) {
            //Captura a exceção esperada, indicando que o teste passou
        }
    }
}
