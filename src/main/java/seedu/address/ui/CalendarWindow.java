package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CalendarUtil;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

/**
 * Displays the Calendar.
 * It contains appointment date of clients.
 */
public class CalendarWindow extends UiPart<Stage> {

    private static final String FXML = "CalendarWindow.fxml";

    private static final Logger logger = LogsCenter.getLogger(CalendarWindow.class);

    private Calendar clients;

    private Logic logic;
    private Thread updateTimeThread;

    private CalendarView calendarView;

    /**
     * Creates a new CalendarWindow.
     *
     * @param root Stage to use as the root of the CalendarWindow.
     */
    public CalendarWindow(Stage root) {
        super(FXML, root);
        calendarView = new CalendarView();
        clients = new Calendar("Clients");
        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().addAll(clients);
        calendarView.getCalendarSources().addAll(myCalendarSource);
        clients.setStyle(Calendar.Style.STYLE1);
        calendarView.setRequestedTime(LocalTime.now());

        updateTimeThread = new Thread("Calendar: Update Time Thread") {
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
        root.setWidth(1000);
        root.setHeight(600);
        Scene scene = new Scene(calendarView.getMonthPage());
        root.setScene(scene);
        calendarView.getMonthPage().getMonthView().setDisable(true);
        root.setResizable(true);
    }

    /**
     * Creates a new CalendarWindow.
     */
    public CalendarWindow(Logic logic) {
        this(new Stage());
        this.logic = logic;
    }

    /**
     * Shows the calendar window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing Calendar page.");
        assert !isShowing() : "Calendar page is already showing";
        update();
        getRoot().show();
        getRoot().centerOnScreen();
        assert isShowing() : "Calendar page is not showing after show()";
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

    /**
     * Update the list of entries in the calendar.
     */
    public void update() {
        ObservableList<Person> lists = logic.getFilteredPersonList();
        clients.clear();
        for (Person p : lists) {
            clients.addEntry(CalendarUtil.createNewEntry(p));
        }
    }

}
