package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

import com.calendarfx.model.Calendar;
import com.calendarfx.view.CalendarView;

public class CalendarWindow extends UiPart<Stage>{

    private static final String FXML = "CalendarWindow.fxml";

    private static final Logger logger = LogsCenter.getLogger(CalendarWindow.class);

    private Calendar clients;

    private CalendarView calendarView;
    public CalendarWindow(Stage root) {
        super(FXML, root);
        calendarView = new CalendarView(); // (1)
        clients = new Calendar("Clients");
        clients.setStyle(Calendar.Style.STYLE1);

        calendarView.setRequestedTime(LocalTime.now());

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

        Scene scene = new Scene(calendarView.getMonthPage());
        root.setScene(scene);
    }

    /**
     * Creates a new CalendarWindow.
     */
    public CalendarWindow() {
        this(new Stage());
    }

    public void show() {
        logger.fine("Showing Risk Assessment Questionnaire page.");
        assert !isShowing() : "Questionnaire window is already showing";
        getRoot().show();
        getRoot().centerOnScreen();
        assert isShowing() : "Questionnaire window is not showing after show()";
    }

    /**
     * Returns true if the questionnaire window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the questionnaire window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the questionnaire window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
