package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_FAILURE_UNDO_WITH_NO_UNDOABLE_COMMANDS;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_DELETE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_undoDelete_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UndoCommand undoCommand = new UndoCommand();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        String expectedResult = String.format(MESSAGE_UNDO_DELETE_SUCCESS, Messages.format(personToDelete));
        expectedModel.undoDelete();

        model.deletePerson(personToDelete);
        model.storePreviousUndoableCommand("delete");
        assertCommandSuccess(undoCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_noPreviousUndoableCommands_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UndoCommand undoCommand = new UndoCommand();

        //Do a simple delete and undo. Hence, there should be no previous undoable commands.
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        expectedModel.undoDelete();

        //Do a simple delete and undo. Hence, there should be no previous undoable commands.
        model.deletePerson(personToDelete);
        model.storePreviousUndoableCommand("delete");
        model.undoDelete();
        model.removePreviousUndoableCommand();

        //Trying to undo when there are 0 previous undoable commands, there is a CommandFailure.
        assertCommandFailure(undoCommand, model, MESSAGE_FAILURE_UNDO_WITH_NO_UNDOABLE_COMMANDS);
    }

}
