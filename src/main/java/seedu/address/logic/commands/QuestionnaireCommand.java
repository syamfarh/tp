package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Opens Questionnaire window.
 */
public class QuestionnaireCommand extends Command {
    public static final String COMMAND_WORD = "questionnaire";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows risk assessment questionnaire.\n"
        + "Example: " + COMMAND_WORD;

    public static final String SHOWING_QUESTIONNAIRE_MESSAGE = "Opened questionnaire window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_QUESTIONNAIRE_MESSAGE, false, true, false,
                false);
    }
}
