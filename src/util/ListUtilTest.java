package util;

import junit.framework.TestCase;

import java.util.*;

public class ListUtilTest extends TestCase {

    public void testPad() {
        final int count = 5;

        List<java.util.Date> list = new ArrayList<java.util.Date>();
        final java.util.Date element = new java.util.Date();
        ListUtil.pad(list, element, count);
        assertEquals(count, list.size());

        for (int i = 0; i < count; i++)
            assertEquals("unexpected element at " + i, element, list.get(i));
    }

    public void testWildcardCapture() {
        List<String> names = new ArrayList<String>();
        names.add("alpha");
        names.add("betha");
        inPlaceReverse(names);
        assertEquals("betha", names.get(0));
        assertEquals("alpha", names.get(1));
    }

    static void inPlaceReverse(List<?> list) { //Metodo com wildcard
        int size = list.size();
        for (int i = 0; i < size / 2; i++)
            swap(list, i, size - 1 - i); //Delega para o metodo genérico swap
    }

    private static <T> void swap(List<T> list, int i, int opposite) { //Metodo genérico
        T temp = list.get(i);
        list.set(i, list.get(opposite));
        list.set(opposite, temp);
    }
}
