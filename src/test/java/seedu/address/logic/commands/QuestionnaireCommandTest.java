package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.QuestionnaireCommand.SHOWING_QUESTIONNAIRE_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class QuestionnaireCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_questionnaire_success() {
        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_QUESTIONNAIRE_MESSAGE, false, true, false,
                        false);
        assertCommandSuccess(new QuestionnaireCommand(), model, expectedCommandResult, expectedModel);
    }
}
