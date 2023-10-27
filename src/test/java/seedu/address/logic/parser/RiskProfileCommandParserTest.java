package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKPROFILE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RiskProfileCommand;
import seedu.address.model.person.RiskProfile;

public class RiskProfileCommandParserTest {
    private RiskProfileCommandParser parser = new RiskProfileCommandParser();
    private final String nonEmptyResult = "a,a,b,c,c,d,d,e";

    @Test
    public void parse_indexSpecified_success() {
        // have result
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_RISKPROFILE + nonEmptyResult;
        RiskProfileCommand expectedCommand =
            new RiskProfileCommand(INDEX_FIRST_PERSON, new RiskProfile(nonEmptyResult));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no result
        userInput = targetIndex.getOneBased() + " " + PREFIX_RISKPROFILE;
        assertParseFailure(parser, userInput,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RiskProfileCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RiskProfileCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RiskProfileCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RiskProfileCommand.COMMAND_WORD + " " + nonEmptyResult, expectedMessage);
    }
}
