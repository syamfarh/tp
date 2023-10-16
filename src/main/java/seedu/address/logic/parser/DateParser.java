package seedu.address.logic.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


/**
 * Parses input arguments and convert to LocalDate object.
 */
public class DateParser {
    /**
     * Parses the given {@code String} of arguments
     * and returns a LocalDate object.
     */
    public static LocalDate convertDate(String date) {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("[MM/dd/yyyy]" + "[dd-MM-yyyy]" + "[yyyy-MM-dd]"));
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

        return LocalDate.parse(date, dateTimeFormatter);
    }
    /**
     * Returns true if a given string is a more than current date.
     */
    public static boolean isValidCurrentDate(String date) {
        LocalDate inputDate = convertDate(date);
        return inputDate.isAfter(LocalDate.now());
    }

}
