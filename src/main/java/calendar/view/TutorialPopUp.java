package calendar.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class to hold the hints for the calendar.application.
 */
class TutorialPopUp {

    /**
     * Shows the tooltips.
     */
    void display() {
        Stage popUpWindow = new Stage();

        popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Tutorial!");

        Label dateLabel = new Label("" +
                "Click on the tile to reach the actual day's Events.\r\n" +
                "General notifications at standard output.\r\n" +
                "It loads data at startup from a .txt file.\r\n" +
                "It will print to a .txt file when it's closed.\r\n\n" +
                "<<:\tback one Month in the Calendar.\r\n" +
                ">>:\tforward one Month in the Calendar.\r\n\n" +
                "New Event:\r\n" +
                "\t-Accessible from the main window.\r\n" +
                "\t-Giving the exact Date is obligatory.\r\n" +
                "\t-In [YearMonthDayHourMinute] format.\r\n\n" +
                "Delete Event:\r\n" +
                "\t-Accessible from the Daily View.\r\n" +
                "\t-By pushing the 'Delete' button next to an Event.\r\n" +
                "\t-Delete's the Event without confirmation!\r\n\n" +
                "Modify Event:\r\n" +
                "\t-Accessible from the Daily View.\r\n" +
                "\t-By pushing the 'Modify' button next to an Event.\r\n" +
                "\t-Asks for the modifications!\r\n\n");

        HBox dateBox = new HBox(dateLabel);
        dateBox.setPadding(new Insets(10));
        VBox layout = new VBox(10);

        layout.getChildren().addAll(dateBox);
        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 400, 450);

        popUpWindow.setScene(scene1);
        popUpWindow.showAndWait();
    }
}