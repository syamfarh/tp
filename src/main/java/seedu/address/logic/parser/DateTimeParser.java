package seedu.address.logic.parser;


import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


/**
 * Parses input arguments and convert to LocalDate object.
 */
public class DateTimeParser {

    /** formatter to convert String to DateTime */
    public static final DateTimeFormatterBuilder STRINGTODATETIME = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("[MM/dd/yyyy HH:mm]" + "[dd-MM-yyyy HH:mm]" + "[yyyy-MM-dd HH:mm]"));

    /** formatter to convert DateTime to String */
    public static final DateTimeFormatterBuilder DATETIMETOSTRING = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    /**
     * Parses the given {@code String} of arguments
     * and returns a LocalDate object.
     * @throws DateTimeException if {@code String} does not follow format
     */
    public static LocalDateTime convertDate(String date) {
        DateTimeFormatter dateTimeFormatter = STRINGTODATETIME.toFormatter();
        LocalDateTime.parse(date, dateTimeFormatter);
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
    /**
     * Returns true if a given string is a more than current date.
     */
    public static boolean isValidCurrentDateTime(String date) {
        LocalDateTime inputDate = convertDate(date);
        return inputDate.isAfter(LocalDateTime.now());
    }

}
