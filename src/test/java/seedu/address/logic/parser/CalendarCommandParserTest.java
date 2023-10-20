package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CalendarCommand;
import seedu.address.model.person.CalendarContainsKeywordsPredicate;

public class CalendarCommandParserTest {
    private CalendarCommandParser parser = new CalendarCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CalendarCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsCalendarCommand() {
        // no leading and trailing whitespaces
        CalendarCommand expectedCalendarCommand =
                new CalendarCommand(new CalendarContainsKeywordsPredicate(List.of("2023-12-12")));
        assertParseSuccess(parser, "2023-12-12", expectedCalendarCommand);
    }
}
