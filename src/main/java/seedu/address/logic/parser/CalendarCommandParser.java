package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.DateParser.isValidCurrentDate;
import static seedu.address.logic.parser.DateParser.isValidFormat;

import java.util.Arrays;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CalendarCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CalendarContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new CalendarCommand object
 */
public class CalendarCommandParser implements Parser<CalendarCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CalendarCommand
     * and returns a CalendarCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CalendarCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalendarCommand.MESSAGE_USAGE));
        }
        if (!isValidFormat(trimmedArgs)) {
            throw new ParseException(Messages.MESSAGE_WRONG_DATE_FORMAT);
        }
        if (!isValidCurrentDate(trimmedArgs)) {
            throw new ParseException(Messages.MESSAGE_INVALID_INPUT_DATE);
        }

        String[] calendarKeywords = trimmedArgs.split("\\s+");

        return new CalendarCommand(new CalendarContainsKeywordsPredicate(Arrays.asList(calendarKeywords)));
    }
}
