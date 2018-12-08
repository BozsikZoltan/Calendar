package calendar.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Class to hold the Days Events.
 * Creates a SortedArrayList.
 */
public class Day implements Comparable<Event> {

    /**
     * List, to store the Events for the day.
     */
    private List<Event> planedEvents;

    /**
     * Constructor to initialize the ArrayList.
     */
    public Day() {
        planedEvents = new ArrayList<>();
    }

    /**
     * Add method, to add Events to the Set.
     *
     * @param event: what we want to add.
     */
    public void addEvent(Event event) {
        planedEvents.add(event);
        System.out.println("Add was successful!");
    }

    /**
     * Remove method, to remove Event from the List.
     *
     * @param event: what we want to remove.
     */
    public void removeEvent(Event event) {
        if (planedEvents.contains(event)) {
            planedEvents.remove(event);
            System.out.println("Delete was successful!");
        } else {
            System.out.println("Error[Day Remove]:The given Event didn't exists!");
        }
    }

    /**
     * Checks whether the Date is an Even date or not.
     *
     * @param date: what we want to check.
     * @return boolean: true if its Event.
     */
    public boolean isEventDay(Date date) {
        for (Event e : planedEvents) {
            if (e.getEventDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Printing method to list the Day.
     *
     * @return String: for the file output.
     */
    String printDayEvents() {
        StringBuilder stringBuilder = new StringBuilder();

        planedEvents.sort(Comparators.DATE);
        for (Event event : planedEvents) {
            stringBuilder.append(event.toString());
        }
        return stringBuilder.toString();
    }

    /**
     * Returns an ArrayList with Events
     *
     * @return ArrayList: the day's Events.
     */
    ArrayList<Event> getEvents() {
        return new ArrayList<>(planedEvents);
    }

    /**
     * CompareTo to Compare an Event to a Date's Events
     *
     * @param o: what we want to compare.
     * @return int: true or false.
     */
    @Override
    public int compareTo(Event o) {
        for (Event planedEvent : planedEvents) {
            if (planedEvent.getEventDate().equals(o.getEventDate())) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Comparator, to Sort the ArrayList.
     */
    static class Comparators {
        static Comparator<Event> DATE = Comparator.comparing(Event::getEventDate);
//         static Comparator<Event> NAME = Comparator.comparing(Event::getEventName);
//         static Comparator<Event> LOCATION = Comparator.comparing(Event::getEventLocation);
    }
}