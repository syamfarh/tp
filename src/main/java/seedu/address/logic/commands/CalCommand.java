package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class CalCommand extends Command {

    public static final String COMMAND_WORD = "calendar";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows calendar window.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_CAL_MESSAGE = "Opened calendar window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_CAL_MESSAGE, false,
                false, true, false);
    }
}
