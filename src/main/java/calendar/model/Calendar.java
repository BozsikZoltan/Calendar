package calendar.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Calendar class, to hold the Days of the event.
 */
public class Calendar {
    /**
     * Map to hold the Dates and the Days.
     */
    private Map<Date, Day> happenings;

    /**
     * Constructor to initialize the map.
     */
    public Calendar() {
        happenings = new HashMap<>();
    }

    /**
     * Add method, to add Day to the Map.
     *
     * @param event: what we want to add.
     */
    public void addEventToCalendar(Event event) {
        if (happenings.containsKey(event.getEventDate())) {
            happenings.get(event.getEventDate()).addEvent(event);
        } else {
            Day day = new Day();
            day.addEvent(event);
            happenings.put(event.getEventDate(), day);
        }
    }

    /**
     * Remove method, to remove Day from the Map.
     *
     * @param event: what we want to remove.
     */
    public void removeEventFromCalendar(Event event) {
        if (happenings.containsKey(event.getEventDate())) {
            happenings.get(event.getEventDate()).removeEvent(event);
            if (!happenings.get(event.getEventDate()).isEventDay(event.getEventDate())) {
                happenings.remove(event.getEventDate());
            }
        } else {
            System.out.println("Error[Calendar Remove]: The given event did not exist!");
        }
    }

    /**
     * Modify method, to modify Day in the Map.
     *
     * @param eventOld: what we want to modify from.
     * @param eventNew: which we want to modify to.
     */
    public void modifyEventInTheCalendar(Event eventOld, Event eventNew) {
        if (happenings.containsKey(eventOld.getEventDate())) {
            removeEventFromCalendar(eventOld);
            addEventToCalendar(eventNew);
            System.out.println("Updated from:" + eventOld + "to:" + eventNew);
        } else {
            System.out.println("Error[Calendar Modify]: The given event did not exist!");
        }
    }

    /**
     * Printing method to list the Calendar.
     * Creates TreeMap to Short the Map.
     * Creates StringBuilder to store the data.
     *
     * @return String: for the file output.
     */
    public String printCalendarDays() {
        Map<Date, Day> sortMap = new TreeMap<>(happenings);
        StringBuilder stringBuilder = new StringBuilder();

        for (Date date : sortMap.keySet()) {
            stringBuilder.append(sortMap.get(date).printDayEvents());
        }
        return stringBuilder.toString();
    }

    /**
     * Returns an ArrayList with Events
     *
     * @param calendarDate: what we want to know.
     * @return ArrayList: the day's Events.
     */
    public ArrayList<Event> getEventsFromCalendar(LocalDate calendarDate) {
        ArrayList<Event> events = new ArrayList<>();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

        for (Date day : happenings.keySet()) {
            String date = DATE_FORMAT.format(day);
            if (calendarDate.toString().equals(date)) {
                events.addAll(happenings.get(day).getEvents());
            }
        }
        return events;
    }

    public Map<Date, Day> getHappenings() {
        return happenings;
    }
}