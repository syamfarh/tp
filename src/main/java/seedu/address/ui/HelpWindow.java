package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {
    public static final String SINGLE_NEWLINE = "\n";
    public static final String DOUBLE_NEWLINE = "\n\n";

    public static final String USERGUIDE_URL = "https://ay2324s1-cs2103t-w09-1.github.io/tp/UserGuide.html";

    public static final String ADD_COMMAND_GUIDE =
            "1. ADD" + SINGLE_NEWLINE
            + "Adds a contact to the address book." + SINGLE_NEWLINE
            + "Format: add n/NAME p/PHONE_NUMBER e/EMAIL o/OCCUPATION "
            + "a/ADDRESS [t/TAG]…\u200B [appt/APPOINTMENT_DATE]" + SINGLE_NEWLINE
            + "Example: add n/John Doe, p/+65 98765432, e/johnd@example.com, o/Software Engineer, "
            + "a/John Street, Block 123, 138123, t/New Client";

    public static final String DELETE_COMMAND_GUIDE =
            "2. DELETE" + SINGLE_NEWLINE
            + "Deletes a contact from the address book." + SINGLE_NEWLINE
            + "Format: delete INDEX [MORE INDEXES]" + SINGLE_NEWLINE
            + "⚫Deletes the contact at the specified INDEX, must be a positive integer" + SINGLE_NEWLINE
            + "⚫The INDEX refers to the INDEX number shown in the displayed person list." + SINGLE_NEWLINE
            + "Example: delete 2 5 -> deletes the 2nd and 5th contacts in the address book.";
    public static final String UNDO_COMMAND_GUIDE =
            "3. UNDO" + SINGLE_NEWLINE
            + "Undoes the most recent undoable command. "
            + "Undoable commands include: add, clone, delete, clear, edit." + SINGLE_NEWLINE
            + "Format: undo" + SINGLE_NEWLINE
            + "Example: delete 1 -> deletes the 1st contact, followed by undo -> add the contact back.";
    public static final String FIND_COMMAND_GUIDE =
            "4. FIND" + SINGLE_NEWLINE
            + "Finds persons based on the given keywords." + SINGLE_NEWLINE
            + "Format: find n/NAME [MORE NAMES]" + SINGLE_NEWLINE
            + "             find a/ADDRESS [MORE ADDRESSES]" + SINGLE_NEWLINE
            + "             find appt/APPOINTMENT DATE" + SINGLE_NEWLINE
            + "Example: find n/John Alice -> finds all contacts whose names include 'John' and 'Alice'."
            + SINGLE_NEWLINE
            + "               find a/Tokyo Geylang -> finds all contacts whose address include 'Tokyo' and 'Geylang'."
            + SINGLE_NEWLINE
            + "               find appt/2024-01-01 -> finds all contacts whose appointment date matches '2024-01-01'.";
    public static final String EDIT_COMMAND_GUIDE =
            "5. EDIT" + SINGLE_NEWLINE
            + "Edits an existing contact in the address book." + SINGLE_NEWLINE
            + "Format: edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [o/OCCUPATION] "
            + "[appt/APPOINTMENT_DATE] [a/ADDRESS] [t/TAG]…\u200B" + SINGLE_NEWLINE
            + "⚫Edits the person at the specified INDEX. " + SINGLE_NEWLINE
            + "⚫At least one of the optional fields must be provided." + SINGLE_NEWLINE
            + "Example: edit 2 n/Betsy t/ -> Edits the name of the 2nd person to be 'Betsy' "
            + "and clears all existing tags.";

    public static final String HELP_MESSAGE =
            ADD_COMMAND_GUIDE + DOUBLE_NEWLINE
            + DELETE_COMMAND_GUIDE + DOUBLE_NEWLINE
            + UNDO_COMMAND_GUIDE + DOUBLE_NEWLINE
            + FIND_COMMAND_GUIDE + DOUBLE_NEWLINE
            + EDIT_COMMAND_GUIDE + DOUBLE_NEWLINE
            + "For other commands, please visit our user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    @FXML
    private Label website;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        assert helpMessage != null : "helpMessage must be initialized";
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
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
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
