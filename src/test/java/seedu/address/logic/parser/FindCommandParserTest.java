package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.getErrorMessageForMultiplePrefixes;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAddCommand;
import seedu.address.logic.commands.FindApptCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindNameCommand;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.CalendarContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindNameCommand expectedFindNameCommand =
                new FindNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " n/Alice Bob", expectedFindNameCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Alice \n \t Bob  \t", expectedFindNameCommand);
    }

    @Test
    public void parse_validArgs_returnsFindAddCommand() {
        // no leading and trailing whitespaces
        FindAddCommand expectedFindAddCommand =
                new FindAddCommand(new AddressContainsKeywordsPredicate(Arrays.asList("geylang", "Tokyo")));
        assertParseSuccess(parser, " a/geylang Tokyo", expectedFindAddCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " a/ \n geylang \n \t Tokyo  \t", expectedFindAddCommand);
    }

    @Test
    public void parse_validArgs_returnsCalendarCommand() {
        // no leading and trailing whitespaces
        FindApptCommand expectedFindApptCommand =
                new FindApptCommand(new CalendarContainsKeywordsPredicate(Arrays.asList("2023-12-12")));
        assertParseSuccess(parser, " appt/2023-12-12", expectedFindApptCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " appt/ \n 2023-12-12", expectedFindApptCommand);
    }

    @Test
    public void parse_multiplePrefix_throwsParseException() {
        assertParseFailure(parser, " n/Alice a/geylang", getErrorMessageForMultiplePrefixes());
    }
}
