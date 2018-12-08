package model;

import calendar.model.Day;
import calendar.model.Event;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Day.
 * Contains 3 tests.
 */
public class DayTest {
    private Day day = new Day();
    private Date date = new java.util.Date();
    private Event event1 = new Event(date, "Aladdin", "London");
    private Event event2 = new Event(new java.util.Date(), "Banana", "Taiwan");

    /**
     * Setter for the Test.
     */
    @Before
    public void setUp() {
        day.addEvent(event1);
    }

    /**
     * Test: Checks a Date whether it's an Event Date.
     */
    @Test
    public void testIsEventDay() {
        Assume.assumeTrue("Event is in Day failure!", day.isEventDay(date));
    }

    /**
     * Test: Add Event to a Day.
     */
    @Test
    public void testAdd() {
        day.addEvent(event2);
        assertEquals("Event add to day failure!", 1, day.compareTo(event2));
    }

    /**
     * Test: Remove Event from a Day.
     */
    @Test
    public void testRemove() {
        day.removeEvent(event1);
        assertEquals("Event remove from day failure!", 0, day.compareTo(event1));
    }
}