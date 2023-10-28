package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAddCommand;
import seedu.address.model.person.AddressContainsKeywordsPredicate;

public class FindAddCommandParserTest {
    private FindAddCommandParser parser = new FindAddCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindAddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindAddCommand() {
        // no leading and trailing whitespaces
        FindAddCommand expectedFindAddCommand =
                new FindAddCommand(new AddressContainsKeywordsPredicate(Arrays.asList("geylang", "Tokyo")));
        assertParseSuccess(parser, "geylang Tokyo", expectedFindAddCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n geylang \n \t Tokyo  \t", expectedFindAddCommand);
    }
}
