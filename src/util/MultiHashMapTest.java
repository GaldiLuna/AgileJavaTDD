package util;

import Tests.sis.studentinfo.DateUtil;
import junit.framework.TestCase;

import java.util.*;
//import java.sql.Date;

public class MultiHashMapTest extends TestCase {
    private static final java.util.Date today = new java.util.Date();
    private static final java.util.Date tomorrow = new java.util.Date(today.getTime() + 86400000); //Adiciona 24horas em milissegundos
    private static final String eventA = "wake up";
    private static final String eventB = "eat";

    private MultiHashMap<java.util.Date, String> events;

    protected void setUp() {
        events = new MultiHashMap<java.util.Date, String>();
    }

    public void testCreate() {
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

    public void testFilter() {
        MultiHashMap<String, java.sql.Date> meetings = new MultiHashMap<String, java.sql.Date>();
        meetings.put("iteration start", createSqlDate(2005, 9, 12));
        meetings.put("iteration start", createSqlDate(2005, 9, 26));
        meetings.put("VP blather", createSqlDate(2005, 9, 12));
        meetings.put("brown bags", createSqlDate(2005, 9, 14));

        MultiHashMap<String, Date> mondayMeetings = new MultiHashMap<String, Date>(); //Novo mapa com java.util.Date

        MultiHashMap.filter(mondayMeetings, meetings, new MultiHashMap.Filter<Date>() { //Chamada do metodo filter com filtro para java.util.Date
           public boolean apply(Date date) {
               return isMonday(date);
           }
        });

        assertEquals(2, mondayMeetings.size());
        assertEquals(2, mondayMeetings.get("iteration start").size());
        assertNull(mondayMeetings.get("brown bags"));
        assertEquals(1, mondayMeetings.get("VP blather").size());
    }

    private boolean isMonday(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    private java.sql.Date createSqlDate(int year, int month, int day) {
        java.util.Date date = DateUtil.createDate(year, month, day); //Supondo que DateUtil.createDate() exista
        return new java.sql.Date(date.getTime());
    }
}
