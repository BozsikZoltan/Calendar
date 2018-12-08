package calendar.view;

import calendar.controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import calendar.model.Event;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for Listing the Events for today.
 */
class TodayEventsPopUp {
    /**
     * List to store the output View.
     */
    private List<HBox> eventsOut;
    private Controller controller;
    private MonthView monthView;
    private final HBox header;

    /**
     * Constructor to initialize the TodayEventsPopUp.
     *
     * @param controller: to reach the Mode[Calendar].
     * @param monthView:  to refresh the .
     */
    TodayEventsPopUp(Controller controller, MonthView monthView) {
        this.controller = controller;
        this.monthView = monthView;
        eventsOut = new ArrayList<>();

        Label number = new Label("Nr.:");
        Label date = new Label("Event's date:");
        Label name = new Label("Event's name:");
        Label location = new Label("Event's location:");
        Label buttons = new Label("Buttons:");

        number.setPrefSize(30, 0);
        date.setPrefSize(190, 0);
        name.setPrefSize(280, 0);
        location.setPrefSize(280, 0);
        buttons.setPrefSize(160, 0);
        buttons.setAlignment(Pos.CENTER);

        header = new HBox(number, date, name, location, buttons);
        header.setPadding(new Insets(10));
        header.setSpacing(10);
    }

    /**
     * Shows the events of the day.
     *
     * @param events:     the Events for the actual day.
     * @param actualDate: the actual Date.
     */
    void display(ArrayList<Event> events, LocalDate actualDate) {
        eventsOut.clear();
        Integer sizeX = 300;
        Integer sizeY = 50;

        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Events for: " + actualDate.toString());

        if (!events.isEmpty()) {
            sizeX = 1000;
            sizeY = 55 * (events.size() + 1);
        }
        VBox layout = new VBox(10);

        for (int i = 0; i < events.size(); i++) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
            String date = DATE_FORMAT.format(events.get(i).getEventDate());

            Integer event = i + 1;
            Label eventNumber = new Label(event.toString() + ".");
            Label eventDate = new Label(date);
            Label eventName = new Label(events.get(i).getEventName());
            Label eventLocation = new Label(events.get(i).getEventLocation());

            Button modifyButton = new Button("Modify");
            modifyButton.setPrefSize(75, 0);
            modifyButton.setOnMouseClicked(mouseEvent -> {
                ModifyPopUp modifyPopUp = new ModifyPopUp();
                Event eventNew = modifyPopUp.display(events.get(event - 1));
                controller.modify(events.get(event - 1), eventNew);
                monthView.populateCalendar(monthView.getCurrentYearMonth());
                if (checkTheEventsDate(events.get(event - 1), eventNew)) {
                    events.set(event - 1, eventNew);
                    Collections.sort(events);
                    eventsOut.clear();
                    eventsOut = eventIntoHBox(layout, events);

                    layout.getChildren().clear();
                    layout.getChildren().add(header);
                    layout.getChildren().addAll(eventsOut);
                } else {
                    if (eventsOut.size() != 1) {
                        events.remove(event - 1);
                        Collections.sort(events);
                        eventsOut.clear();
                        eventsOut = eventIntoHBox(layout, events);

                        layout.getChildren().clear();
                        layout.getChildren().add(header);
                        layout.getChildren().addAll(eventsOut);
                    } else {
                        eventsOut.clear();
                        addEmptyLabel(eventsOut);
                        layout.getChildren().clear();
                        layout.getChildren().addAll(eventsOut);
                    }
                }
            });
            Button deleteButton = new Button("Delete");
            deleteButton.setPrefSize(75, 0);
            deleteButton.setOnMouseClicked(mouseEvent -> {
                controller.remove(events.get(event - 1));
                monthView.populateCalendar(monthView.getCurrentYearMonth());
                if (eventsOut.size() != 1) {
                    events.remove(event - 1);
                    Collections.sort(events);
                    eventsOut.clear();
                    eventsOut = eventIntoHBox(layout, events);

                    layout.getChildren().clear();
                    layout.getChildren().add(header);
                    layout.getChildren().addAll(eventsOut);
                } else {
                    eventsOut.clear();
                    addEmptyLabel(eventsOut);

                    layout.getChildren().clear();
                    layout.getChildren().addAll(eventsOut);
                }
            });

            eventNumber.setPrefSize(30, 0);
            eventDate.setPrefSize(190, 0);
            eventName.setPrefSize(280, 0);
            eventLocation.setPrefSize(280, 0);
            HBox e = new HBox(eventNumber, eventDate, eventName, eventLocation, modifyButton, deleteButton);
            e.setSpacing(10);
            e.setPadding(new Insets(10));
            eventsOut.add(e);
        }
        addEmptyLabel(eventsOut, eventsOut.isEmpty());

        if (!events.isEmpty()) {
            layout.getChildren().add(header);
        }

        layout.getChildren().addAll(eventsOut);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, sizeX, sizeY);
        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();
    }

    /**
     * Recursive method for the modify button.
     *
     * @param layout: the original View.
     * @param events: the new ArrayList with the modified event.
     * @return ArrayList: the new View.
     */
    private List<HBox> eventIntoHBox(VBox layout, ArrayList<Event> events) {
        for (int i = 0; i < events.size(); i++) {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
            String date = DATE_FORMAT.format(events.get(i).getEventDate());

            Integer event = i + 1;
            Label eventNumber = new Label(event.toString() + ".");
            Label eventDate = new Label(date);
            Label eventName = new Label(events.get(i).getEventName());
            Label eventLocation = new Label(events.get(i).getEventLocation());

            Button modifyButton = new Button("Modify");
            modifyButton.setPrefSize(75, 0);
            modifyButton.setOnMouseClicked(mouseEvent -> {
                ModifyPopUp modifyPopUp = new ModifyPopUp();
                Event eventNew = modifyPopUp.display(events.get(event - 1));
                controller.modify(events.get(event - 1), eventNew);
                monthView.populateCalendar(monthView.getCurrentYearMonth());
                if (checkTheEventsDate(events.get(event - 1), eventNew)) {
                    events.set(event - 1, eventNew);
                    Collections.sort(events);
                    eventsOut.clear();
                    eventsOut = eventIntoHBox(layout, events);

                    layout.getChildren().clear();
                    layout.getChildren().add(header);
                    layout.getChildren().addAll(eventsOut);
                } else {
                    if (eventsOut.size() != 1) {
                        events.remove(event - 1);
                        Collections.sort(events);
                        eventsOut.clear();
                        eventsOut = eventIntoHBox(layout, events);

                        layout.getChildren().clear();
                        layout.getChildren().add(header);
                        layout.getChildren().addAll(eventsOut);
                    } else {
                        eventsOut.clear();
                        addEmptyLabel(eventsOut);

                        layout.getChildren().clear();
                        layout.getChildren().addAll(eventsOut);
                    }
                }
            });

            Button deleteButton = new Button("Delete");
            deleteButton.setPrefSize(75, 0);
            deleteButton.setOnMouseClicked(mouseEvent -> {
                controller.remove(events.get(event - 1));
                monthView.populateCalendar(monthView.getCurrentYearMonth());
                if (eventsOut.size() != 1) {
                    events.remove(event - 1);
                    Collections.sort(events);
                    eventsOut.clear();
                    eventsOut = eventIntoHBox(layout, events);

                    layout.getChildren().clear();
                    layout.getChildren().add(header);
                    layout.getChildren().addAll(eventsOut);
                } else {
                    eventsOut.clear();
                    addEmptyLabel(eventsOut);

                    layout.getChildren().clear();
                    layout.getChildren().addAll(eventsOut);
                }
            });
            eventNumber.setPrefSize(30, 0);
            eventDate.setPrefSize(190, 0);
            eventName.setPrefSize(280, 0);
            eventLocation.setPrefSize(280, 0);
            HBox e = new HBox(eventNumber, eventDate, eventName, eventLocation, modifyButton, deleteButton);
            e.setPadding(new Insets(10));
            e.setSpacing(10);
            eventsOut.add(e);
        }
        layout.getChildren().clear();
        layout.getChildren().addAll(eventsOut);
        return eventsOut;
    }

    /**
     * Checks whether the twe Date at the same Date, or not.
     *
     * @param oldEvent: what we want to check.
     * @param newEvent: which we want to check.
     * @return boolean: true if equals.
     */
    private boolean checkTheEventsDate(Event oldEvent, Event newEvent) {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        String oEvent = DATE_FORMAT.format(oldEvent.getEventDate());
        String nEvent = DATE_FORMAT.format(newEvent.getEventDate());

        return oEvent.equals(nEvent);
    }

    /**
     * Add empty label to the window.
     *
     * @param eventsOut: the View storage.
     */
    private void addEmptyLabel(List<HBox> eventsOut) {
        Label eventError = new Label("There aren't any event left!");
        HBox e = new HBox(eventError);
        e.setPadding(new Insets(10));
        e.setAlignment(Pos.CENTER);
        eventsOut.add(e);
    }

    /**
     * Add empty label to the window.
     *
     * @param eventsOut: the View storage.
     * @param empty:     boolean value, from the List.
     */
    private void addEmptyLabel(List<HBox> eventsOut, boolean empty) {
        if (empty) {
            Label eventError = new Label("This day is empty!");
            HBox e = new HBox(eventError);
            e.setPadding(new Insets(10));
            e.setAlignment(Pos.CENTER);
            eventsOut.add(e);
        }
    }
}