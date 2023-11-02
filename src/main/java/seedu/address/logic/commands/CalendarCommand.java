package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Opens Calendar window.
 */
public class CalendarCommand extends Command {
    public static final String COMMAND_WORD = "calendar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows calendar.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CALENDAR_MESSAGE = "Opened calendar window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CALENDAR_MESSAGE, false, false,
                true, false);
    }
}
