package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindApptCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CalendarContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindApptCommand object
 */
public class CalendarCommandParser implements Parser<FindApptCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindApptCommand
     * and returns a FindApptCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindApptCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindApptCommand.MESSAGE_USAGE));
        }

        String[] calendarKeywords = trimmedArgs.split("\\s+");

        return new FindApptCommand(new CalendarContainsKeywordsPredicate(Arrays.asList(calendarKeywords)));
    }
}
