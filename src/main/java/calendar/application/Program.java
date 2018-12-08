package calendar.application;

import calendar.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import calendar.model.Event;
import calendar.view.MonthView;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

/**
 * Class for the main method, extends Application.
 * Creates a calendar.controller, runs the calendar.application.
 */
@SuppressWarnings("unchecked")
public class Program extends Application {
    private Controller controller = new Controller();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method.
     * Reads event from a .txt file.
     * Exception handling for multiple occasion.
     *
     * @param primaryStage: the Scene.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            File file = new File("calendar.txt");
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                while ((st = br.readLine()) != null) {
                    String[] parts = st.split(";");
                    try {
                        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                        Date date;
                        try {
                            date = originalFormat.parse(parts[0]);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            System.out.println("Maybe the Database encoding was insufficient!\r\nThe file had been formatted!");
                            break;
                        }
                        if (parts.length == 3) {
                            Event event = new Event(date, parts[1], parts[2]);
                            controller.add(event);
                        } else {
                            Event event = new Event(date, null, null);
                            controller.add(event);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                System.out.println("Database not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Calendar");
        primaryStage.setScene(new Scene(new MonthView(YearMonth.now(), controller).getView()));
        primaryStage.show();
    }

    /**
     * Stop method.
     * Exception handling for multiple occasion.
     * At closing, prints out the calendar into a .txt file
     */
    @Override
    public void stop() {
        System.out.println("Primary stage is closing!\r\n Printing to file!");
        try {
            try {
                FileWriter writer = new FileWriter("calendar.txt");
                writer.append(controller.getCalendar().printCalendarDays());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error: I/O exception! " + e.getMessage());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}