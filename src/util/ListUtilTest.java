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
}
