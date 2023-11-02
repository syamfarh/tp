package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RedoCommand.MESSAGE_REDO_FAILURE;
import static seedu.address.logic.commands.RedoCommand.MESSAGE_REDO_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class RedoCommandTest {

    private static final Person TEST = new PersonBuilder().withName("Test Best").withPhone("9482442")
            .withAppointmentDate("").withEmail("Test@example.com").withOccupation("Test")
            .withAddress("4th Test").build();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Model modelAfterUndoAdd = getModelAfterUndoAdd();

    private RedoCommand redoCommand = new RedoCommand();

    public Model getModelAfterUndoAdd() {
        Model modelToEdit = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelToEdit.addPerson(TEST);
        modelToEdit.storePreviousUndoableCommand("add");
        modelToEdit.addToRedoableStateList();
        modelToEdit.undoAdd();
        modelToEdit.removePreviousUndoableCommand();
        return modelToEdit;
    }


    @Test
    public void execute_redo_success() {
        Model expectedModel = modelAfterUndoAdd;
        expectedModel.addToUndoableStateList();
        expectedModel.storePreviousUndoableCommand("redo");
        expectedModel.restoreUndoableState();
        String expectedResult = MESSAGE_REDO_SUCCESS;

        assertCommandSuccess(redoCommand, modelAfterUndoAdd, expectedResult, expectedModel);
    }

    @Test
    public void execute_noPreviousRedoableState() {
        assertCommandFailure(redoCommand, model, MESSAGE_REDO_FAILURE);
    }
}
