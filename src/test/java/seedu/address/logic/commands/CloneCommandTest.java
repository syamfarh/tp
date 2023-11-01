package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CloneCommand.MESSAGE_CLONE_PERSON_SUCCESS;
import static seedu.address.logic.commands.CloneCommand.clonePerson;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_NINTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_TENTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_TWELFTH_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class CloneCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredListSpacesNoSuffix_success() throws CommandException {
        Person personToClone = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        CloneCommand cloneCommand = new CloneCommand(INDEX_SECOND_PERSON);

        // Creating the expected cloned person
        Person expectedClonedPerson = clonePerson(personToClone);

        StringBuilder builder = new StringBuilder();
        builder.append(personToClone.getName())
                .append("; Phone: ")
                .append(expectedClonedPerson.getPhone())
                .append("; Email: ")
                .append(expectedClonedPerson.getEmail())
                .append("; Occupation: ")
                .append(expectedClonedPerson.getOccupation())
                .append("; Address: ")
                .append(expectedClonedPerson.getAddress())
                .append("; AppointmentDate: ")
                .append(expectedClonedPerson.getApptDate())
                .append("; Tags: ");
        expectedClonedPerson.getTags().forEach(builder::append);
        String expectedClonedPersonS = builder.toString();

        String expectedMessage = String.format(MESSAGE_CLONE_PERSON_SUCCESS,
            expectedClonedPersonS); // Only compare the name


        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedClonedPerson);

        assertCommandSuccess(cloneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredListNoSpacesNoSuffix_success() throws CommandException {
        Person personToClone = model.getFilteredPersonList().get(INDEX_NINTH_PERSON.getZeroBased());
        CloneCommand cloneCommand = new CloneCommand(INDEX_NINTH_PERSON);

        // Creating the expected cloned person
        Person expectedClonedPerson = clonePerson(personToClone);

        StringBuilder builder = new StringBuilder();
        builder.append(personToClone.getName())
                .append("; Phone: ")
                .append(expectedClonedPerson.getPhone())
                .append("; Email: ")
                .append(expectedClonedPerson.getEmail())
                .append("; Occupation: ")
                .append(expectedClonedPerson.getOccupation())
                .append("; Address: ")
                .append(expectedClonedPerson.getAddress())
                .append("; AppointmentDate: ")
                .append(expectedClonedPerson.getApptDate())
                .append("; Tags: ");
        expectedClonedPerson.getTags().forEach(builder::append);
        String expectedClonedPersonS = builder.toString();

        String expectedMessage = String.format(MESSAGE_CLONE_PERSON_SUCCESS,
                expectedClonedPersonS); // Only compare the name


        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedClonedPerson);

        assertCommandSuccess(cloneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredCloneInList_success() throws CommandException {
        Person personToClone = model.getFilteredPersonList().get(INDEX_TENTH_PERSON.getZeroBased());
        CloneCommand cloneCommand = new CloneCommand(INDEX_TENTH_PERSON);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Person expectedClonedPerson = new PersonBuilder().withName("Lenny David 2").withAddress("6th Street")
            .withEmail("lenny@example.com").withPhone("94932143").withOccupation("Fisherman")
            .withAppointmentDate("2023-12-21 08:30").withRiskProfile("").withTags("friends").build();

        String expectedMessage = String.format(MESSAGE_CLONE_PERSON_SUCCESS, Messages.format(personToClone));

        expectedModel.addPerson(expectedClonedPerson);

        assertCommandSuccess(cloneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CloneCommand cloneCommand = new CloneCommand(outOfBoundIndex);

        assertCommandFailure(cloneCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        // Try to clone the first person in the filtered list
        CloneCommand cloneCommand = new CloneCommand(outOfBoundIndex);

        assertCommandFailure(cloneCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CloneCommand cloneFirstCommand = new CloneCommand(INDEX_FIRST_PERSON);
        CloneCommand cloneSecondCommand = new CloneCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(cloneFirstCommand.equals(cloneFirstCommand));

        // same values -> returns true
        CloneCommand cloneFirstCommandCopy = new CloneCommand(INDEX_FIRST_PERSON);
        assertTrue(cloneFirstCommand.equals(cloneFirstCommandCopy));

        // different types -> returns false
        assertFalse(cloneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(cloneFirstCommand.equals(null));

        // different indexes -> returns false
        assertFalse(cloneFirstCommand.equals(cloneSecondCommand));
    }

    @Test
    public void execute_validIndexUnfilteredListInvalidSuffix_throwsCommandException() {
        // Create a scenario where the name has an invalid numeric suffix
        Person personToClone = model.getFilteredPersonList().get(INDEX_TWELFTH_PERSON.getZeroBased());
        CloneCommand cloneCommand = new CloneCommand(INDEX_TWELFTH_PERSON);

        assertCommandFailure(cloneCommand, model, Messages.MESSAGE_PERSON_SUFFIX_OUT_OF_RANGE);
    }


    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        CloneCommand cloneCommand = new CloneCommand(targetIndex);
        String expected = CloneCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, cloneCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);
        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
