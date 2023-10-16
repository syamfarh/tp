package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_FAILURE;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_undo_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UndoCommand undoCommand = new UndoCommand();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS);
        expectedModel.undo();

        model.deletePerson(personToDelete);
        assertCommandSuccess(undoCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_alreadyUndoneBefore_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UndoCommand undoCommand = new UndoCommand();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        expectedModel.undo();

        model.deletePerson(personToDelete);
        model.undo();
        assertCommandFailure(undoCommand, model, MESSAGE_UNDO_FAILURE);
    }

}
