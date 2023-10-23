package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Undoes a delete command, adding the deleted person back.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the deletion of a person done in the most recent command.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_FAILURE = "Cannot undo! You have already undone previously!";

    public static final String MESSAGE_UNDO_SUCCESS = "Undo Successful! Contact added back: %1$s";

    /**
     * Constructor for UndoCommand is empty.
     */
    public UndoCommand() {};

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person deletedPerson = model.getDeletedPerson();

        //catch multiple undo statements done in a row.
        if (model.hasPerson(deletedPerson)) {
            throw new CommandException(MESSAGE_UNDO_FAILURE);
        }
        model.undo();
        return new CommandResult(String.format(MESSAGE_UNDO_SUCCESS, Messages.format(deletedPerson)));
    }



}
