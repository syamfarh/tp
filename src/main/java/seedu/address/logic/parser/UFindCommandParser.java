package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the input arguments and creates a new FindCommand or FindAddCommand depending on user input prefix
 */
public class UFindCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the UFindCommand
     * and returns a FindCommand or FindAddCommand object for execution.
     * @param args User input
     * @return FindCommand or FindAddCommand depending on prefix user specified
     * @throws ParseException if the user input does not conform with the expected format
     */
    public Command parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) && !arePrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UFindCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ADDRESS);

        if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            throw new ParseException(Messages.getErrorMessageForMultiplePrefixes());
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            return new FindCommandParser().parse(argMultimap.getValue(PREFIX_NAME).get());
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            return new FindAddCommandParser().parse(argMultimap.getValue(PREFIX_ADDRESS).get());
        }
        // should never reach this line
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UFindCommand.MESSAGE_USAGE));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
