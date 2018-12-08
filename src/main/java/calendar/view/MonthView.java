package calendar.view;

import calendar.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import calendar.model.Event;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;

import static javafx.scene.text.TextAlignment.CENTER;

/**
 * Class to hold the MonthView.
 */
public class MonthView {

    /**
     * ArrayList to hold the actual Month.
     */
    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private VBox view;
    private Text calendarTitle;
    private YearMonth currentYearMonth;
    private Controller controller;

    /**
     * Create a Month View.
     * Create buttons, to navigate in the Calendar, and for Adding new Event to the Calendar.
     *
     * @param yearMonth:  the actual year and month to create the calendar of.
     * @param controller: to reach to Calendar.
     */
    public MonthView(YearMonth yearMonth, Controller controller) {
        this.controller = controller;
        currentYearMonth = yearMonth;
        GridPane calendar = new GridPane();
        calendar.setPrefSize(1050, 750);
        calendar.setGridLinesVisible(true);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode(this.controller, this);
                ap.setPrefSize(150, 150);
                calendar.add(ap, j, i);
                allCalendarDays.add(ap);
            }
        }
        Text[] dayNames = new Text[]{new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
                new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                new Text("Saturday")};
        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(150);
        Integer col = 0;

        for (Text dayText : dayNames) {
            AnchorPane ap = new AnchorPane();
            ap.setPrefSize(150, 7);
            AnchorPane.setBottomAnchor(dayText, 5.0);
            ap.getChildren().add(dayText);
            dayLabels.add(ap, col++, 0);
        }
        calendarTitle = new Text();
        calendarTitle.setWrappingWidth(150);
        calendarTitle.setTextAlignment(CENTER);

        Button previousMonth = new Button("<<");
        previousMonth.setOnAction(e -> previousMonth());
        Button nextMonth = new Button(">>");
        nextMonth.setOnAction(e -> nextMonth());
        Button addEvent = new Button("New event!");
        addEvent.setPrefSize(100, 0);
        addEvent.setOnAction(e -> addEvent());
        Button tutorial = new Button("Tutorial!");
        tutorial.setPrefSize(100, 0);
        tutorial.setOnAction(e -> tutorialEvent());

        HBox navigationLine = new HBox(tutorial, previousMonth, calendarTitle, nextMonth, addEvent);
        navigationLine.setSpacing(25);
        navigationLine.setAlignment(Pos.BASELINE_CENTER);

        populateCalendar(yearMonth); // Populate calendar with the day numbers
        view = new VBox(navigationLine, dayLabels, calendar);
    }

    /**
     * Set the days of the calendar, to correspond to the appropriate date.
     *
     * @param yearMonth: year and month of month to render.
     */
    void populateCalendar(YearMonth yearMonth) {
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) { // Go back the day until it is SUNDAY
            calendarDate = calendarDate.minusDays(1);
        }

        for (AnchorPaneNode ap : allCalendarDays) { // Populate the calendar with day numbers
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
                ap.setEvents(null);
            }

            String eventString = "";
            ArrayList<Event> events = new ArrayList<>(controller.getEvents(calendarDate));
            Collections.sort(events);

            if (!events.isEmpty()) {
                ap.setEvents(events);
                if (events.size() == 1) {
                    eventString = "\r\n\n\n\n      Event for today!";
                } else {
                    Integer size = events.size();
                    eventString = "\r\n\n\n\n     " + size.toString() + " events for today!";
                }
            } else {
                events.clear();
                ap.setEvents(events);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()) + eventString);
            ap.setDate(calendarDate);

            AnchorPane.setTopAnchor(txt, 5.0);
            AnchorPane.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);

            calendarDate = calendarDate.plusDays(1);
        }
        calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
    }

    /**
     * Move the month back by one. Reload the calendar with the correct dates.
     */
    private void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Move the month forward by one. Reload the calendar with the correct dates.
     */
    private void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    /**
     * Add Event to the Calendar.
     */
    private void addEvent() {
        AddPopUp addPopUp = new AddPopUp();
        Event event = new Event(addPopUp.display());
        if (event.getEventDate() != null) {
            controller.add(controller.eventChecker(event));
        }
        populateCalendar(currentYearMonth);
    }

    /**
     * Help desk.
     */
    private void tutorialEvent() {
        TutorialPopUp tutorialPopUp = new TutorialPopUp();
        tutorialPopUp.display();
    }

    /**
     * Getter
     *
     * @return VBox: calendar.view.
     */
    public VBox getView() {
        return view;
    }

    /**
     * Getter
     *
     * @return YearMonth: the current year and month Date.
     */
    YearMonth getCurrentYearMonth() {
        return currentYearMonth;
    }
}