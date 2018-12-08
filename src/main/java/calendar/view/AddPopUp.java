package calendar.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import calendar.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.awt.BorderLayout.CENTER;

/**
 * Class for the Adding PopUpWindow.
 */
class AddPopUp {
    private String nameS = null;
    private String locationS = null;
    private String dateS = null;
    private Date date = null;

    /**
     * Asks for the Adding parameters.
     * Exception handling for multiply occasion.
     *
     * @return Event: what we want to Add to the Calendar.
     */
    Event display() {
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("New event!");

        Label deleteEvent = new Label("Adding the following event:");
        Label nameLabel = new Label("Name:");
        nameLabel.setPrefSize(75, 0);
        Label locationLabel = new Label("Location:");
        locationLabel.setPrefSize(75, 0);
        Label dateLabel = new Label("Date:");
        dateLabel.setPrefSize(75, 0);

        TextField nameText = new TextField("name");
        nameText.setAccessibleText(CENTER);
        nameText.setPrefColumnCount(25);
        TextField locationText = new TextField("location");
        locationText.setAccessibleText(CENTER);
        locationText.setPrefColumnCount(25);
        TextField year = new TextField("year");
        year.setAlignment(Pos.CENTER);
        year.setAccessibleText(CENTER);
        year.setPrefColumnCount(4);
        TextField month = new TextField("month");
        month.setAlignment(Pos.CENTER);
        month.setAccessibleText(CENTER);
        month.setPrefColumnCount(4);
        TextField day = new TextField("day");
        day.setAlignment(Pos.CENTER);
        day.setAccessibleText(CENTER);
        day.setPrefColumnCount(4);
        TextField hour = new TextField("hour");
        hour.setAlignment(Pos.CENTER);
        hour.setAccessibleText(CENTER);
        hour.setPrefColumnCount(4);
        TextField minute = new TextField("minute");
        minute.setAlignment(Pos.CENTER);
        minute.setAccessibleText(CENTER);
        minute.setPrefColumnCount(4);

        HBox nameBox = new HBox(nameLabel, nameText);
        nameBox.setPadding(new Insets(10));
        HBox locationBox = new HBox(locationLabel, locationText);
        locationBox.setPadding(new Insets(10));
        HBox dateBox = new HBox(dateLabel, year, month, day, hour, minute);
        dateBox.setPadding(new Insets(10));

        Button button1 = new Button("Add Event!");
        button1.setOnAction(e -> {
            Boolean bool = true;
            nameS = nameText.getText();
            locationS = locationText.getText();
            dateS = year.getText() + month.getText() + day.getText() + hour.getText() + minute.getText();

            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHmm");
            try {
                Long ez = Long.parseLong(dateS);
                if (ez / 100000000000L < 1) {
                    System.out.println("The given Date was invalid!");
                    bool = false;
                }
            } catch (IllegalArgumentException ez) {
                System.out.println("Invalid argument!");
                bool = false;
            }
            if (bool) {
                try {
                    date = originalFormat.parse(dateS);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
            popUpWindow.close();
        });
        VBox layout = new VBox(10);

        layout.getChildren().addAll(deleteEvent, nameBox, locationBox, dateBox, button1);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 400, 250);

        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();

        return new Event(date, nameS, locationS);
    }
}