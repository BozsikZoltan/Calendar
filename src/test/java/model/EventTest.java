package model;

import calendar.model.Event;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the Events.
 * Contains 4 tests.
 */
public class EventTest {
    private Event event1, event2;

    /**
     * Setter for the Test.
     */
    @Before
    public void setUp() {
        Long value = 201811112222L;
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new java.util.Date();
        try {
            date = DATE_FORMAT.parse(value.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        event1 = new Event(date, "Thomas", "Ween");
        event2 = new Event(date, "Rafael", "Balaton");
    }

    /**
     * Test: the Location Getter method.
     */
    @Test
    public void testGetLocation() {
        assertEquals("Get event location failure!", "Ween", event1.getEventLocation());
    }

    /**
     * Test: the Name Getter method.
     */
    @Test
    public void testGetName() {
        assertEquals("Get event name failure!", "Rafael", event2.getEventName());
    }

    /**
     * Test: the Location Setter method.
     */
    @Test
    public void testSetLocation() {
        String testString = "Balaton";
        event1.setEventLocation(testString);
        assertEquals("Set event location failure!", "Balaton", event1.getEventLocation());
    }

    /**
     * Test: the Name Setter method.
     */
    @Test
    public void testSetName() {
        event2.setEventName(event1.getEventName());
        assertEquals("Set event name failure!", "Thomas", event2.getEventName());
    }
}