package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redoes the most recent undo command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ":Redoes the most recent undo command. \n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_REDO_SUCCESS = "Redo successful!";

    public static final String MESSAGE_REDO_FAILURE = "Redo unsuccessful! There is nothing to redo!";

    /**
     * Constructor for RedoCommand is empty.
     */
    public RedoCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (model.getRedoableStateListSize() <= 0) {
            throw new CommandException(MESSAGE_REDO_FAILURE);
        }

        model.addToUndoableStateList();

        model.storePreviousUndoableCommand(COMMAND_WORD);
        model.restoreRedoableState();

        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }
}
