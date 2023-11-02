package seedu.address.logic.commands;

import static seedu.address.logic.commands.CalendarCommand.SHOWING_CALENDAR_MESSAGE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class CalendarCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_calendar_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_CALENDAR_MESSAGE, false, false,
                        true, false);
        assertCommandSuccess(new CalendarCommand(), model, expectedCommandResult, expectedModel);
    }
}
