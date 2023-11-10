package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.util.Pair;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Undoes the most recent undoable command. Undoable commands are: delete, clear, add, clone, edit.
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

    public static final String MESSAGE_UNDO_REDO_SUCCESS = "Undo Successful!";

    private static Logger logger = Logger.getLogger("UndoCommand");

    /**
     * Constructor for UndoCommand is empty.
     */
    public UndoCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getPreviousUndoableCommandsSize() == 0) {
            throw new CommandException(MESSAGE_FAILURE_UNDO_WITH_NO_UNDOABLE_COMMANDS);
        }

        switch(model.getPreviousUndoableCommand()) {
        case "delete":
            logger.log(Level.INFO, "case: delete");
            return executeUndoDelete(model);
        case "clear":
            logger.log(Level.INFO, "case: clear");
            return executeUndoClear(model);
        case "add":
        case "clone":
            logger.log(Level.INFO, "case: add");
            return executeUndoAdd(model);
        case "edit":
            logger.log(Level.INFO, "case: edit");
            return executeUndoEdit(model);
        case "redo":
            return executeUndoRedo(model);
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

        // Make a new list containing only the persons deleted from the previous delete command.
        List<Person> undoDeletedPersons = new ArrayList<>(deletedPersons.subList(deletedPersons.size()
            - numberOfDeletes, deletedPersons.size()));
        // can catch errors here. should assert first that deletedPersons is not empty.
        // also possible to check if deletedPersons.size() == sum of model.getLastdeletednumber()

        String deletedPersonsDetails = Messages.formatPersons(undoDeletedPersons);

        //should never reach this!
        if (deletedPersons.isEmpty() || model.getDeletedNumberList().isEmpty()) {
            return new CommandResult(MESSAGE_UNDO_DELETE_FAILURE);
        }

        model.addToRedoableStateList();

        /* Undo the deletion of each person deleted from a single command. */
        for (Person deletedPerson : undoDeletedPersons) {
            model.undoDelete(deletedPerson);
            model.removePreviousUndoableCommand();
        }

        // Remove the corresponding number of deletes
        model.removeLastDeletedNumber();

        String resultMessage = String.format(MESSAGE_UNDO_DELETE_SUCCESS, deletedPersonsDetails);

        return new CommandResult(resultMessage);
    }

    /**
     * Undoes a clear command.
     * @return returns CommandResult of the message when the undo is a success.
     */
    public CommandResult executeUndoClear(Model model) {

        model.addToRedoableStateList();
        //int numberOfPreviousDeleteCommands = model.getNumberOfPreviousDeleteCommands();

        /* Undo each individual delete command */
        for (int i = 0; i < model.getLastClearedNumber(); i++) {
            model.undoDelete();
        }

        // Remove 'clear' from the list of previous undoable commands.
        model.removePreviousUndoableCommand();
        model.removeLastClearedNumber();

        return new CommandResult(String.format(MESSAGE_UNDO_CLEAR_SUCCESS));
    }

    /**
     * Undoes an add command.
     * @return returns CommandResult of the message when the undo is a success.
     */
    public CommandResult executeUndoAdd(Model model) {

        model.addToRedoableStateList();

        Person personToDelete = model.getAddedPerson();
        model.undoAdd();
        model.removePreviousUndoableCommand();

        return new CommandResult(String.format(MESSAGE_UNDO_ADD_SUCCESS, Messages.format(personToDelete)));
    }

    /**
     * Undoes an edit command.
     * @return returns CommandResult of the message when the undo is a success.
     */
    public CommandResult executeUndoEdit(Model model) {

        model.addToRedoableStateList();

        Pair<Person, Person> pairToRestore = model.getEditedPersonsPair();
        Person originalPerson = pairToRestore.getValue();

        model.undoEdit();

        model.removePreviousUndoableCommand();

        return new CommandResult(String.format(MESSAGE_UNDO_EDIT_SUCCESS, Messages.format(originalPerson)));
    }

    /**
     * Undoes a redo command.
     * @return returns CommandResult of the message when the undo is a success.
     */
    public CommandResult executeUndoRedo(Model model) {

        model.addToRedoableStateList();
        model.removePreviousUndoableCommand();
        model.restoreUndoableState();

        return new CommandResult(MESSAGE_UNDO_REDO_SUCCESS);
    }
}
