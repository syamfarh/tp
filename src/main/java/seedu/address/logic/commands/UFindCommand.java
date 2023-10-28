package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class UFindCommand {

    public static final String COMMAND_WORD = "ufind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names/addresses contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: n/ or a/ KEYWORD [MORE_KEYWORDS]...\n"
            + "Note: You can only search for either name or address at one time.\n"
            + "Example: " + COMMAND_WORD + " n/ alice bob charlie";


}
