package model;

import calendar.model.Calendar;
import calendar.model.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test class for the Calendar.
 * Contains 3 tests.
 */
public class CalendarTest {
    private Calendar calendar = new Calendar();
    private Date date = new java.util.Date();
    private Event event1 = new Event(date, "Aladdin", "London");
    private Event event2 = new Event(new java.util.Date(), "Banana", "Taiwan");

    /**
     * Setter for the Test.
     */
    @Before
    public void setUp() {
        calendar.addEventToCalendar(event1);
    }

    /**
     * Test: Remove Event from the Calendar.
     */
    @Test
    public void testRemove() {
        calendar.removeEventFromCalendar(event1);
        assertFalse("Event remove from calendar failure!", calendar.getHappenings().containsKey(event1.getEventDate()));
    }

    /**
     * Test: Add Event to the Calendar.
     */
    @Test
    public void testAdd() {
        calendar.addEventToCalendar(event2);
        assertTrue("Event add to calendar failure!", calendar.getHappenings().containsKey(event2.getEventDate()));
    }

    /**
     * Test: Modify Event into the Calendar.
     */
    @Test
    public void testModify() {
        calendar.addEventToCalendar(event1);
        calendar.modifyEventInTheCalendar(event1, event2);
        assertEquals("Event modify in calendar failure!", 1, calendar.getHappenings().get(date).compareTo(event2));
    }
}