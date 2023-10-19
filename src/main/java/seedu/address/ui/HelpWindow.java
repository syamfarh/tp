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
            + "Format: add n/NAME p/PHONE_NUMBER e/EMAIL o/OCCUPATION a/ADDRESS t/TAG" + SINGLE_NEWLINE
            + "Example: add n/John Doe, p/+65 98765432, e/johnd@example.com, o/Software Engineer, "
            + "a/John Street, Block 123, 138123, t/New Client";

    public static final String DELETE_COMMAND_GUIDE =
            "2. DELETE" + SINGLE_NEWLINE
            + "Deletes a contact from the address book." + SINGLE_NEWLINE
            + "Format: delete INDEX" + SINGLE_NEWLINE
            + "⚫Deletes the contact at the specified INDEX, must be a positive integer" + SINGLE_NEWLINE
            + "⚫The index refers to the index number shown in the displayed person list." + SINGLE_NEWLINE
            + "Example: delete 3 - deletes the 3rd contact in the address book.";
    public static final String UNDO_COMMAND_GUIDE =
            "3. UNDO" + SINGLE_NEWLINE
            + "Undoes a single delete done previously." + SINGLE_NEWLINE
            + "Format: undo" + SINGLE_NEWLINE
            + "Example: undo - the contact has been deleted and undo it.";

    public static final String FIND_NAME_COMMAND_GUIDE =
            "4. FIND by NAME" + SINGLE_NEWLINE
            + "Finds contacts whose names contain any of the given keywords." + SINGLE_NEWLINE
            + "Format: find NAME" + SINGLE_NEWLINE
            + "Example: find Jackson - attempts to find a contact name containing 'Jackson'.";
    public static final String FIND_ADDRESS_COMMAND_GUIDE =
            "5. FIND by ADDRESS" + SINGLE_NEWLINE
            + "Finds contacts whose address contain any of the given keywords." + SINGLE_NEWLINE
            + "Format: find_add ADDRESS" + SINGLE_NEWLINE
            + "Example: find_add geylang - returns all contacts whose addresses contain 'geylang'.";

    public static final String EDIT_COMMAND_GUIDE =
            "6. EDIT" + SINGLE_NEWLINE
            + "Edits an existing person in the address book." + SINGLE_NEWLINE
            + "Format: edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [o/OCCUPATION] "
            + "[a/ADDRESS] [t/TAG] [appt/APPOINTMENTDATE]" + SINGLE_NEWLINE
            + "⚫Edits the person at the specified INDEX. " + SINGLE_NEWLINE
            + "⚫At least one of the optional fields must be provided." + SINGLE_NEWLINE
            + "Example: edit 1 n/John Doe - Edits the name of the 1st person to be John Doe.";

    public static final String HELP_MESSAGE =
            ADD_COMMAND_GUIDE + DOUBLE_NEWLINE
            + DELETE_COMMAND_GUIDE + DOUBLE_NEWLINE
            + UNDO_COMMAND_GUIDE + DOUBLE_NEWLINE
            + FIND_NAME_COMMAND_GUIDE + DOUBLE_NEWLINE
            + FIND_ADDRESS_COMMAND_GUIDE + DOUBLE_NEWLINE
            + EDIT_COMMAND_GUIDE + DOUBLE_NEWLINE
            + "For more details, please visit our website: " + USERGUIDE_URL;

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
