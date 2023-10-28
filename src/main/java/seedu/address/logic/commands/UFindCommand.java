package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book whose name or address contains any of the argument keywords.
 * Name or address searched depends on prefix in user input.
 * Keyword matching is case-insensitive.
 */
public class UFindCommand extends Command {

    public static final String COMMAND_WORD = "ufind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names/addresses contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: n/ or a/ KEYWORD [MORE_KEYWORDS]...\n"
            + "Note: You can only search for either name or address at one time.\n"
            + "Example: " + COMMAND_WORD + " n/ alice bob charlie";

    // main functionality of finding either by name or address lies within the individual find features
    // themselves, this method has no functionality
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException("This method is not meant to be called!");
    }
}
