package seedu.address.logic.parser;

import static seedu.address.commons.util.ComparatorUtil.APPTCOMPARATOR;
import static seedu.address.commons.util.ComparatorUtil.NAMECOMPARATOR;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCUPATION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validPrefix_returnsSortCommand() {
        SortCommand expectedSortCommandAppt = new SortCommand(APPTCOMPARATOR);
        SortCommand expectedSortCommandName = new SortCommand(NAMECOMPARATOR);

        // valid prefix
        assertParseSuccess(parser, PREFIX_APPOINTMENTDATE.getPrefix(), expectedSortCommandAppt);
        assertParseSuccess(parser, PREFIX_NAME.getPrefix(), expectedSortCommandName);

    }

    @Test
    public void parse_invalidPrefix_returnsSortCommand() {
        SortCommand expectedSortCommandAppt = new SortCommand(APPTCOMPARATOR);
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

        // invalid prefix
        assertParseFailure(parser, PREFIX_OCCUPATION.getPrefix(), expectedMessage);
        assertParseFailure(parser, "", expectedMessage);
    }
}
