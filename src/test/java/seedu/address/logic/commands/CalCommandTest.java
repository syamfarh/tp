package seedu.address.logic.commands;

import static seedu.address.logic.commands.CalCommand.SHOWING_CAL_MESSAGE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class CalCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_cal_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_CAL_MESSAGE, false, false,
                        true, false);
        assertCommandSuccess(new CalCommand(), model, expectedCommandResult, expectedModel);
    }

}
