package calendar.view;

import calendar.controller.Controller;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import calendar.model.Event;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Create an AnchorPane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    /**
     * ArrayList to store the Events to a AnchorPane, what belongs to the actual Day.
     */
    private ArrayList<Event> events = new ArrayList<>();
    private LocalDate date;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     *
     * @param controller: to reach the Calendar.
     * @param monthView:  the View for the refreshes.
     * @param children:   children of the anchor pane.
     */
    AnchorPaneNode(Controller controller, MonthView monthView, Node... children) {
        super(children);

        TodayEventsPopUp todayEventsPopUp = new TodayEventsPopUp(controller, monthView);
        this.setOnMouseClicked(e -> todayEventsPopUp.display(events, date));
    }

    /**
     * Getter.
     *
     * @return LocalDate: the Date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter.
     *
     * @param date: the new Date.
     */
    void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Setter.
     *
     * @param events: the AnchorPane's Events.
     */
    void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}