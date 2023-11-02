package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKPROFILE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKPROFILE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.RiskProfile;
import seedu.address.testutil.PersonBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for Risk Profile Command.
 */
public class RiskProfileCommandTest {
    private final String riskProfileStub = "a,a,b,c,c,d,d,e";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addRiskProfileUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRiskProfile(riskProfileStub).build();

        RiskProfileCommand riskProfileCommand =
            new RiskProfileCommand(INDEX_FIRST_PERSON, new RiskProfile(editedPerson.getRiskProfile().value));

        int totalScore = RiskProfileCommand.calculateTotalScore(riskProfileStub);
        String expectedRiskLevel = RiskProfileCommand.calculateRiskLevel(totalScore);

        String expectedMessage = String.format(RiskProfileCommand.MESSAGE_ADD_RISKPROFILE_SUCCESS,
            editedPerson.getName(), editedPerson.getPhone(), editedPerson.getEmail(),
            editedPerson.getOccupation(), editedPerson.getAddress(), editedPerson.getApptDate(),
            expectedRiskLevel, editedPerson.getTags());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(riskProfileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
            .withRiskProfile(riskProfileStub).build();

        RiskProfileCommand riskProfileCommand =
            new RiskProfileCommand(INDEX_FIRST_PERSON, new RiskProfile(editedPerson.getRiskProfile().value));

        int totalScore = RiskProfileCommand.calculateTotalScore(riskProfileStub);
        String expectedRiskLevel = RiskProfileCommand.calculateRiskLevel(totalScore);

        String expectedMessage = String.format(RiskProfileCommand.MESSAGE_ADD_RISKPROFILE_SUCCESS,
            editedPerson.getName(), editedPerson.getPhone(), editedPerson.getEmail(),
            editedPerson.getOccupation(), editedPerson.getAddress(), editedPerson.getApptDate(),
            expectedRiskLevel, editedPerson.getTags());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(riskProfileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_invalidResultInput_failure() {
        String invalidResultInput = "abcd,e,f,a,b";
        RiskProfileCommand riskProfileCommand =
            new RiskProfileCommand(INDEX_FIRST_PERSON, new RiskProfile(invalidResultInput));

        CommandException exception = assertThrows(CommandException.class, () -> riskProfileCommand.execute(model));

        assertEquals(String.format(RiskProfileCommand.MESSAGE_INVALID_RESULT, RiskProfileCommand.MESSAGE_USAGE),
            exception.getMessage());
    }

    @Test
    public void calculateRiskLevel_boundaryTotalScore_success() {
        assertEquals("Low", RiskProfileCommand.calculateRiskLevel(8));
        assertEquals("Moderately Low", RiskProfileCommand.calculateRiskLevel(16));
        assertEquals("Moderate", RiskProfileCommand.calculateRiskLevel(24));
        assertEquals("Moderately High", RiskProfileCommand.calculateRiskLevel(32));
        assertEquals("High", RiskProfileCommand.calculateRiskLevel(40));
    }

    @Test
    public void calculateRiskLevel_invalidTotalScore_returnsEmptyString() {
        assertEquals("", RiskProfileCommand.calculateRiskLevel(0));
        assertEquals("", RiskProfileCommand.calculateRiskLevel(41));
    }

    @Test
    public void isValidResult_invalidResultLength_failure() {
        assertFalse(RiskProfileCommand.isValidResult("a,b,c,d"));
        assertFalse(RiskProfileCommand.isValidResult("a,b,c,d,e,d,b,c,a"));
    }

    @Test
    public void isValidResult_invalidResultCharacter_failure() {
        assertFalse(RiskProfileCommand.isValidResult("a,b,c,d,e,f,g,h"));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RiskProfileCommand riskProfileCommand =
            new RiskProfileCommand(outOfBoundIndex, new RiskProfile(VALID_RISKPROFILE_BOB));

        assertCommandFailure(riskProfileCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        RiskProfileCommand riskProfileCommand =
            new RiskProfileCommand(outOfBoundIndex, new RiskProfile(VALID_RISKPROFILE_BOB));
        assertCommandFailure(riskProfileCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RiskProfileCommand standardCommand =
            new RiskProfileCommand(INDEX_FIRST_PERSON, new RiskProfile(VALID_RISKPROFILE_AMY));

        // same values -> returns true
        RiskProfileCommand commandWithSameValues =
            new RiskProfileCommand(INDEX_FIRST_PERSON, new RiskProfile(VALID_RISKPROFILE_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RiskProfileCommand(INDEX_SECOND_PERSON,
            new RiskProfile(VALID_RISKPROFILE_AMY))));

        // different risk profile -> returns false
        assertFalse(standardCommand.equals(new RiskProfileCommand(INDEX_FIRST_PERSON,
            new RiskProfile(VALID_RISKPROFILE_BOB))));
    }
}
