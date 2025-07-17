package util;

import junit.framework.TestCase;
import java.util.*;

public class EventMapTest extends TestCase {

    public void testSingleElement() {
        EventMap<java.sql.Date, String> map = new EventMap<java.sql.Date, String>();
        final java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        final String value = "abc";
        map.put(date, value);
        List<String> values = map.get(date);
        assertEquals(value, values.get(0));
    }

    public void testGetPastEvents() {
        EventMap<Date, String> events = new EventMap<Date, String>();
        final Date today = new java.util.Date();
        final Date yesterday = new Date(today.getTime() - 86400000); //24h em milissegundos
        events.put(today, "sleep");
        final String descriptionA = "birthday";
        final String descriptionB = "encontro";
        events.put(yesterday, descriptionA);
        events.put(yesterday, descriptionB);
        List<String> descriptions = events.getPastEvents();
        assertTrue(descriptions.contains(descriptionA));
        assertTrue(descriptions.contains(descriptionB));
    }
}
