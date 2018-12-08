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
 * Class for the Modify PopUpWindow.
 */
class ModifyPopUp {
    private String nameS = null;
    private String locationS = null;
    private String dateS = null;
    private Date date = null;

    /**
     * Asks for the Modify parameters.
     * Exception handling for multiply occasion.
     *
     * @param eventFrom: the Event what we want to change.
     * @return Event: to change this.
     */
    Event display(Event eventFrom) {
        nameS = eventFrom.getEventName();
        locationS = eventFrom.getEventLocation();
        dateS = eventFrom.getEventDate().toString();
        date = eventFrom.getEventDate();

        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Modify event!");

        Label deleteEvent = new Label("Modify the following event:");
        Label nameLabel = new Label("Name:");
        nameLabel.setPrefSize(75, 0);
        Label locationLabel = new Label("Location:");
        locationLabel.setPrefSize(75, 0);
        Label dateLabel = new Label("Date:");
        dateLabel.setPrefSize(75, 0);

        TextField nameText = new TextField(eventFrom.getEventName());
        nameText.setAccessibleText(CENTER);
        nameText.setPrefColumnCount(20);
        TextField locationText = new TextField(eventFrom.getEventLocation());
        locationText.setAccessibleText(CENTER);
        locationText.setPrefColumnCount(20);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        String dateOut = format.format(eventFrom.getEventDate());
        TextField dateText = new TextField(dateOut);
        dateText.setAccessibleText(CENTER);
        dateText.setPrefColumnCount(20);

        HBox nameBox = new HBox(nameLabel, nameText);
        nameBox.setPadding(new Insets(10));
        HBox locationBox = new HBox(locationLabel, locationText);
        locationBox.setPadding(new Insets(10));
        HBox dateBox = new HBox(dateLabel, dateText);
        dateBox.setPadding(new Insets(10));

        Button button1 = new Button("Modify Event!");
        button1.setOnAction(e -> {
            date = eventFrom.getEventDate();
            Boolean bool = true;
            nameS = nameText.getText();
            locationS = locationText.getText();
            dateS = dateText.getText();
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

        Scene scene1 = new Scene(layout, 350, 250);

        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();

        return new Event(date, nameS, locationS);
    }
}