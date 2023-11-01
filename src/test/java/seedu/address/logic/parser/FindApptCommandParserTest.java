package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindApptCommand;
import seedu.address.model.person.CalendarContainsKeywordsPredicate;

public class FindApptCommandParserTest {
    private FindApptCommandParser parser = new FindApptCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindApptCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsCalendarCommand() {
        // no leading and trailing whitespaces
        FindApptCommand expectedFindApptCommand =
                new FindApptCommand(new CalendarContainsKeywordsPredicate(List.of("2023-12-12")));
        assertParseSuccess(parser, "2023-12-12", expectedFindApptCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "hello", String.format(Messages.MESSAGE_WRONG_DATE_FORMAT));
        assertParseFailure(parser, "1999-10-10", String.format(Messages.MESSAGE_INVALID_INPUT_DATE));
    }
}
