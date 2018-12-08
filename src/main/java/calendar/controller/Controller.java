package calendar.controller;

import calendar.model.Calendar;
import calendar.model.Event;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controller class, to link the Model and the View.
 * Creates a Calendar.
 */
public class Controller {

    private Calendar calendar = new Calendar();

    /**
     * Add event to Calendar
     *
     * @param event: what we want to add.
     */
    public void add(Event event) {
        calendar.addEventToCalendar(event);
    }

    /**
     * Remove event from the Calendar
     *
     * @param event: what we want to remove.
     */
    public void remove(Event event) {
        calendar.removeEventFromCalendar(event);
    }

    /**
     * Modify event in the Calendar
     *
     * @param eventOld: what we want to modify from.
     * @param eventNew: which we want to modify to.
     */
    public void modify(Event eventOld, Event eventNew) {
        calendar.modifyEventInTheCalendar(eventOld, eventNew);
    }

    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Returns a valid Event.
     *
     * @param event: what we want to validate.
     * @return Event: a valid form of a Event.
     */
    public Event eventChecker(Event event) {
        if (event.getEventLocation().isEmpty()) {
            event.setEventLocation("");
        }
        if (event.getEventName().isEmpty()) {
            event.setEventName("");
        }
        return event;
    }

    /**
     * Returns an ArrayList with Events
     *
     * @param calendarDate: what we want to know.
     * @return ArrayList: the day's Events.
     */
    public ArrayList<Event> getEvents(LocalDate calendarDate) {
        ArrayList<Event> events;
        events = calendar.getEventsFromCalendar(calendarDate);
        return events;
    }
}