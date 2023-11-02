package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_FAILURE_UNDO_WITH_NO_UNDOABLE_COMMANDS;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_ADD_SUCCESS;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_CLEAR_SUCCESS;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_DELETE_SUCCESS;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_EDIT_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEXES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;


public class UndoCommandTest {
    private static final Person TEST = new PersonBuilder().withName("Test Best").withPhone("9482442")
            .withAppointmentDate("").withEmail("Test@example.com").withOccupation("Test")
            .withAddress("4th Test").build();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private UndoCommand undoCommand = new UndoCommand();


    @Test
    public void execute_undoDelete_success() throws CommandException {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        List<Person> personsToDelete = new ArrayList<>();
        List<Index> indexesToDelete = FIRST_INDEXES;

        DeleteCommand deleteCommand = new DeleteCommand(indexesToDelete);

        for (Index targetIndex : FIRST_INDEXES) {
            Person personToDelete = model.getFilteredPersonList().get(targetIndex.getZeroBased());
            personsToDelete.add(personToDelete);
        }

        String expectedResult = String.format(MESSAGE_UNDO_DELETE_SUCCESS, Messages.formatPersons(personsToDelete));

        deleteCommand.execute(expectedModel);

        for (Person deletedPerson : personsToDelete) {
            expectedModel.addPerson(deletedPerson);
            expectedModel.removeDeletedPerson();
            expectedModel.removePreviousUndoableCommand();
        }

        expectedModel.removeLastDeletedNumber();

        deleteCommand.execute(model);

        assertCommandSuccess(undoCommand, model, expectedResult, expectedModel);
    }


    @Test
    public void execute_undoClear_success() throws CommandException {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        ClearCommand clearCommand = new ClearCommand();

        String expectedResult = String.format(MESSAGE_UNDO_CLEAR_SUCCESS);
        clearCommand.execute(expectedModel);

        while (expectedModel.getDeletedPersonsSize() > expectedModel.getNumberOfPreviousDeleteCommands()) {
            expectedModel.undoDelete();
        }

        clearCommand.execute(model);
        model.storePreviousUndoableCommand("clear");
        assertCommandSuccess(undoCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_undoAdd_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        String expectedResult = String.format(MESSAGE_UNDO_ADD_SUCCESS, Messages.format(TEST));
        expectedModel.addPerson(TEST);
        expectedModel.deletePerson(TEST);

        model.addPerson(TEST);
        model.storePreviousUndoableCommand("add");
        assertCommandSuccess(undoCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_undoEdit_success() throws CommandException {
        Person editedPerson = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Person originalPersonBeforeEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedResult = String.format(MESSAGE_UNDO_EDIT_SUCCESS, Messages.format(originalPersonBeforeEdit));

        editCommand.execute(model);
        model.storePreviousUndoableCommand("edit");
        assertCommandSuccess(undoCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_noPreviousUndoableCommands_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UndoCommand undoCommand = new UndoCommand();

        //Do a simple delete and undo. Hence, there should be no previous undoable commands.
        model.deletePerson(personToDelete);
        model.storePreviousUndoableCommand("delete");
        model.undoDelete();
        model.removePreviousUndoableCommand();

        //Trying to undo when there are 0 previous undoable commands, there is a CommandFailure.
        assertCommandFailure(undoCommand, model, MESSAGE_FAILURE_UNDO_WITH_NO_UNDOABLE_COMMANDS);
    }


}
