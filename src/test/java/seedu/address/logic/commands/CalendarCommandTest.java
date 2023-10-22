package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.JOHN;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.CalendarContainsKeywordsPredicate;

public class CalendarCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CalendarContainsKeywordsPredicate firstPredicate =
                new CalendarContainsKeywordsPredicate(Collections.singletonList("first"));
        CalendarContainsKeywordsPredicate secondPredicate =
                new CalendarContainsKeywordsPredicate(Collections.singletonList("second"));

        CalendarCommand calendarFirstCommand = new CalendarCommand(firstPredicate);
        CalendarCommand calendarSecondCommand = new CalendarCommand(secondPredicate);

        // same object -> returns true
        assertTrue(calendarFirstCommand.equals(calendarFirstCommand));

        // same values -> returns true
        CalendarCommand calendarFirstCommandCopy = new CalendarCommand(firstPredicate);
        assertTrue(calendarFirstCommand.equals(calendarFirstCommandCopy));

        // different types -> returns false
        assertFalse(calendarFirstCommand.equals(1));

        // null -> returns false
        assertFalse(calendarFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(calendarFirstCommand.equals(calendarSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        CalendarContainsKeywordsPredicate predicate = preparePredicate(" ");
        CalendarCommand command = new CalendarCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_onePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        CalendarContainsKeywordsPredicate predicate = preparePredicate("2023-12-12");
        CalendarCommand command = new CalendarCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(JOHN), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        CalendarContainsKeywordsPredicate predicate =
                new CalendarContainsKeywordsPredicate(Arrays.asList("2023-12-12"));
        CalendarCommand calendarCommand = new CalendarCommand(predicate);
        String expected = CalendarCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, calendarCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private CalendarContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CalendarContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
