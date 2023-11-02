package seedu.address.logic.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * Parses input arguments and convert to LocalDate object.
 */
public class DateParser {

    public static final DateTimeFormatterBuilder STRING_TO_DATE = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("[MM/dd/yyyy]" + "[dd-MM-yyyy]" + "[yyyy-MM-dd]"));

    /**
     * Parses the given {@code String} of arguments
     * and returns a LocalDate object.
     */
    public static LocalDate convertDate(String date) {
        DateTimeFormatter dateFormatter = STRING_TO_DATE.toFormatter();

        return LocalDate.parse(date, dateFormatter);
    }

    /**
     * Returns true if input date is after current date.
     */
    public static boolean isValidCurrentDate(String input) {
        LocalDate date = convertDate(input);
        return date.isAfter(LocalDate.now());
    }

    /**
     * Returns true if input date is of acceptable format.
     */
    public static boolean isValidFormat(String input) {
        try {
            convertDate(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
