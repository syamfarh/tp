package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.ComparatorUtil.APPTCOMPARATOR;
import static seedu.address.commons.util.ComparatorUtil.NAMECOMPARATOR;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void testEquals() {
        SortCommand sortCommandAppt = new SortCommand(APPTCOMPARATOR);
        SortCommand sortCommandName = new SortCommand(NAMECOMPARATOR);

        // same object -> returns true
        assertTrue(sortCommandAppt.equals(sortCommandAppt));

        SortCommand sortCommandApptCopy = new SortCommand(APPTCOMPARATOR);

        // same comparator -> returns true
        assertTrue(sortCommandAppt.equals(sortCommandApptCopy));

        // different types -> returns false
        assertFalse(sortCommandAppt.equals(1));

        // null -> returns false
        assertFalse(sortCommandAppt.equals(null));

        // different comparator -> returns false
        assertFalse(sortCommandAppt.equals(sortCommandName));

    }

    @Test
    public void toStringMethod() {
        SortCommand sortCommandAppt = new SortCommand(APPTCOMPARATOR);
        String expected = SortCommand.class.getCanonicalName() + "{comparator=" + APPTCOMPARATOR + "}";
        assertEquals(expected, sortCommandAppt.toString());
    }

}
