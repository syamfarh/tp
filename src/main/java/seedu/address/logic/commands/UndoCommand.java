package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Undoes the most recent undoable command. Undoable commands are: delete, clear, add, edit.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the most recent undoable command. Undoable commands are: delete, clear, add, edit.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_FAILURE_UNDO_WITH_NO_UNDOABLE_COMMANDS = "There is no command to undo!";
    public static final String MESSAGE_UNDO_DELETE_SUCCESS = "Undo Successful! Contact(s) added back: %1$s";
    public static final String MESSAGE_UNDO_DELETE_FAILURE = "Undo Failure.";
    public static final String MESSAGE_UNDO_CLEAR_SUCCESS = "Undo Successful! All contacts have been added back!";
    public static final String MESSAGE_UNDO_ADD_SUCCESS = "Undo Successful! Deleted Person: %1$s";
    public static final String MESSAGE_UNDO_EDIT_SUCCESS = "Undo Successful! Reverted back to: %1$s";


    /**
     * Constructor for UndoCommand is empty.
     */
    public UndoCommand() {};

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getPreviousUndoableCommandsSize() == 0) {
            throw new CommandException(MESSAGE_FAILURE_UNDO_WITH_NO_UNDOABLE_COMMANDS);
        }

        switch(model.getPreviousUndoableCommand()) {
        case "delete":
            return executeUndoDelete(model);
        case "clear":
            return executeUndoClear(model);
        case "add":
        case "clone":
            return executeUndoAdd(model);
        case "edit":
            return executeUndoEdit(model);
        default:
            throw new AssertionError("Not an undoable command! There is an error!");
        }
    }

    /**
     * Undoes a delete command.
     * @return returns CommandResult of the message when the undo is a success.
     */
    public CommandResult executeUndoDelete(Model model) {
        ArrayList<Person> deletedPersons = model.getDeletedPersons();
        int numberOfDeletes = model.getLastDeletedNumber();
        List<Person> undoDeletedPersons = new ArrayList<>(deletedPersons.subList(deletedPersons.size()
            - numberOfDeletes, deletedPersons.size()));
        String deletedPersonsDetails = Messages.formatPersons(undoDeletedPersons);

        if (deletedPersons.isEmpty() || model.getDeletedNumberList().isEmpty()) {
            return new CommandResult(MESSAGE_UNDO_DELETE_FAILURE);
        }

        for (Person deletedPerson : undoDeletedPersons) {
            model.addPerson(deletedPerson);
            model.removeDeletedPerson();
            model.removePreviousUndoableCommand();
        }


        // Remove the corresponding number of deletes
        model.removeLastNumber();

        String resultMessage = String.format(MESSAGE_UNDO_DELETE_SUCCESS, deletedPersonsDetails);

        return new CommandResult(resultMessage);
    }

    /**
     * Undoes a clear command.
     * @return returns CommandResult of the message when the undo is a success.
     */
    public CommandResult executeUndoClear(Model model) {

        int numberOfPreviousDeleteCommands = model.getNumberOfPreviousDeleteCommands();
        while (model.getDeletedPersonsSize() > numberOfPreviousDeleteCommands) {
            model.undoDelete();
        }
        model.removePreviousUndoableCommand();
        return new CommandResult(String.format(MESSAGE_UNDO_CLEAR_SUCCESS));
    }

    /**
     * Undoes an add command.
     * @return returns CommandResult of the message when the undo is a success.
     */
    public CommandResult executeUndoAdd(Model model) {

        List<Person> lastShownList = model.getFilteredPersonList();
        int lastIndex = model.getAddressBookSize() - 1;
        Person personToDelete = lastShownList.get(lastIndex);
        model.deletePerson(personToDelete);
        model.removePreviousUndoableCommand();
        return new CommandResult(String.format(MESSAGE_UNDO_ADD_SUCCESS, Messages.format(personToDelete)));
    }

    /**
     * Undoes an edit command.
     * @return returns CommandResult of the message when the undo is a success.
     */
    public CommandResult executeUndoEdit(Model model) {
        Pair<Person, Person> pairToRestore = model.getEditedPersonsPair();
        Person editedPerson = pairToRestore.getKey();
        Person originalPerson = pairToRestore.getValue();
        model.setPerson(editedPerson, originalPerson);
        model.removeEditedPersonsPair();
        model.removePreviousUndoableCommand();

        return new CommandResult(String.format(MESSAGE_UNDO_EDIT_SUCCESS, Messages.format(originalPerson)));
    }
}
