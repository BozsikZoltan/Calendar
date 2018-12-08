package calendar.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Class to define the Event.
 * Implements Comparable, Serializable.
 */
public class Event implements Comparable<Event>, Serializable {
    private Date eventDate;
    private String eventName;
    private String eventLocation;

    /**
     * Constructor to create Event.
     *
     * @param eventDate:     date of the Event.
     * @param eventName:     name of the Event.
     * @param eventLocation: location of the Event.
     */
    public Event(Date eventDate, String eventName, String eventLocation) {
        this.eventDate = eventDate;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
    }

    /**
     * CopyConstructor to copy events.
     *
     * @param event: the original Event what we want to Copy.
     */
    public Event(Event event) {
        this.eventDate = event.eventDate;
        this.eventName = event.eventName;
        this.eventLocation = event.eventLocation;
    }

    /**
     * Comparable interface's method, to compare dates.
     * Needed for the Shoring.
     *
     * @param event: which we want to know when will be.
     */
    @Override
    public int compareTo(Event event) {
        return this.eventDate.compareTo(event.eventDate);
    }

    /**
     * Comparable interface's method, to compare to Events.
     *
     * @param o: what we are curious about.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return eventDate.equals(event.eventDate) && eventName.equals(event.eventName) && eventLocation.equals(event.eventLocation);
    }

    /**
     * For super method, throws the same Event Date.
     */
    @Override
    public int hashCode() {
        return Objects.hash(eventDate, eventName, eventLocation);
    }

    /**
     * toString method, to get the Event in String form.
     *
     * @return String: Event String form.
     */
    @Override
    public String toString() {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        String date = DATE_FORMAT.format(eventDate);
        return date + ";" + eventName + ";" + eventLocation + "\r\n";
    }

    /**
     * Getter.
     *
     * @return Date: Event'a Date.
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * Getter.
     *
     * @return String: Event'a Name.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Getter.
     *
     * @return String: Event'a Location.
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * Setter.
     *
     * @param eventName: new Name of the Event.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Setter.
     *
     * @param eventLocation: new Location of the Event.
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}