package util;

import junit.framework.TestCase;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class MultiHashMapTest extends TestCase {
    private static final Date today = new Date();
    private static final Date tomorrow = new Date(today.getTime() + 86400000); //Adiciona 24horas em milissegundos
    private static final String eventA = "wake up";
    private static final String eventB = "eat";

    private MultiHashMap<Date, String> events;

    protected void setUp() {
        events = new MultiHashMap<Date, String>();
    }

    protected void testCreate() {
        assertEquals(0, events.size());
    }

    public void testSingleEntry() {
        events.put(today, eventA);
        assertEquals(1, events.size());
        assertEquals(eventA, getSoleEvent(today));
    }

    public void testMultipleEntriesDifferentKey() {
        events.put(today, eventA);
        events.put(tomorrow, eventB);
        assertEquals(2, events.size());
        assertEquals(eventA, getSoleEvent(today));
        assertEquals(eventB, getSoleEvent(tomorrow));
    }

    public void testMultpleEntriesSameKey() {
        events.put(today, eventA);
        events.put(today, eventB);
        assertEquals(1, events.size());
        Collection<String> retrievedEvents = events.get(today);
        assertEquals(2, retrievedEvents.size());
        assertTrue(retrievedEvents.contains(eventA));
        assertTrue(retrievedEvents.contains(eventB));
    }

    private String getSoleEvent(Date date) {
        Collection<String> retrievedEvents = events.get(date);
        assertEquals(1, retrievedEvents.size());
        Iterator<String> it = retrievedEvents.iterator();
        return it.next();
    }
}
