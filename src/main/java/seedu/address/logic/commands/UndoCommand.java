package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.List;

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

    public static final String MESSAGE_UNDO_DELETE_FAILURE = "Cannot undo! There's nothing to undo!";
    public static final String MESSAGE_UNDO_DELETE_SUCCESS = "Undo Successful! Contact added back: %1$s";
    public static final String MESSAGE_UNDO_CLEAR_FAILURE = "Cannot undo! There's nothing to undo!";
    public static final String MESSAGE_UNDO_CLEAR_SUCCESS = "Undo Successful! All contacts have been added back!";

    public static final String MESSAGE_UNDO_ADD_FAILURE = "Cannot undo! There's nothing to undo!";
    public static final String MESSAGE_UNDO_ADD_SUCCESS = "Undo Successful! Deleted Person: %1$s";
    /**
     * Constructor for UndoCommand is empty.
     */
    public UndoCommand() {};

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch(model.getPreviousUndoableCommand()) {
            case "delete":
                return executeUndoDelete(model);
            case "clear":
                return executeUndoClear(model);
            case "add":
                return executeUndoAdd(model);
            default:
                return null;
        }

    }

    public CommandResult executeUndoDelete(Model model) throws CommandException {
        //catch duplicate person undo
        if (model.getDeletedPersonsSize() == 0) {
            throw new CommandException(MESSAGE_UNDO_DELETE_FAILURE);
        }
        Person deletedPerson = model.getDeletedPerson();
        model.undoDelete();
        model.removePreviousUndoableCommand();
        return new CommandResult(String.format(MESSAGE_UNDO_DELETE_SUCCESS, Messages.format(deletedPerson)));
    }

    public CommandResult executeUndoClear(Model model) throws CommandException {

        if (model.getDeletedPersonsSize() == 0) {
            throw new CommandException(MESSAGE_UNDO_CLEAR_FAILURE);
        }

        while (model.getDeletedPersonsSize() > 0) {
            model.undoDelete();
        }
        model.removePreviousUndoableCommand();
        return new CommandResult(String.format(MESSAGE_UNDO_CLEAR_SUCCESS));
    }

    public CommandResult executeUndoAdd(Model model) {
        List<Person> lastShownList = model.getFilteredPersonList();
        int lastIndex = model.getAddressBookSize() - 1;
        Person personToDelete = lastShownList.get(lastIndex);
        model.deletePerson(personToDelete);

        return new CommandResult(String.format(MESSAGE_UNDO_ADD_SUCCESS, Messages.format(personToDelete)));
    }


}
