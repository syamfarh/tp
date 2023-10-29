package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.getErrorMessageForMultiplePrefixes;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAddCommand;
import seedu.address.logic.commands.FindNameCommand;
import seedu.address.logic.commands.UFindCommand;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class UFindCommandParserTest {

    private UFindCommandParser parser = new UFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, UFindCommand.MESSAGE_USAGE));
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
    public void parse_multiplePrefix_throwsParseException() {
        assertParseFailure(parser, " n/Alice a/geylang", getErrorMessageForMultiplePrefixes());
    }
}
