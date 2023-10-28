package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.DateTimeParser.DATETIMETOSTRING;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.DateTimeParser;

/**
 * Represents a Person's appointment Date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFormat(String)}}
 */
public class AppointmentDate implements Comparable<AppointmentDate> {

    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Appointment Date should follow the format of "
                    + "[mm/dd/yyyy HH:mm] or [dd-mm-yyyy HH:mm] or [yyyy-mm-dd HH:mm]";

    public static final String MESSAGE_CONSTRAINTS_CURRENTDATE =
            "Appointment Date should be after the current date";



    public final String value;

    public final LocalDateTime valueInLocalDateTime;

    /**
     * Constructs an {@code AppointmentDate}.
     *
     * @param date A valid appointment Date in String type.
     */
    public AppointmentDate(String date) {
        requireNonNull(date);
        value = date;
        if (date.equals("")) {
            valueInLocalDateTime = LocalDateTime.now().plusYears(50);
        } else {
            valueInLocalDateTime = DateTimeParser.convertDate(date);
        }
    }

    /**
     * Constructs an {@code AppointmentDate}.
     *
     * @param date A valid appointment Date in LocalDate type.
     */
    public AppointmentDate(LocalDateTime date) {
        requireNonNull(date);
        valueInLocalDateTime = date;
        value = date.format(DATETIMETOSTRING.toFormatter());
    }

    /**
     * Returns true if a given string is a valid format date.
     */
    public static boolean isValidFormat(String test) {
        try {
            DateTimeParser.convertDate(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid date compare to current date.
     */
    public static boolean isValidCurrentDate(String test) {
        try {
            return DateTimeParser.isValidCurrentDateTime(test);
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentDate // instanceof handles nulls
                && value.equals(((AppointmentDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
    @Override
    public int compareTo(AppointmentDate o) {
        if (this == o) {
            return 0;
        }

        if (valueInLocalDateTime.isEqual(o.valueInLocalDateTime)) {
            return 0;
        } else if (valueInLocalDateTime.isAfter(o.valueInLocalDateTime)) {
            return 1;
        } else if (valueInLocalDateTime.isBefore(o.valueInLocalDateTime)) {
            return -1;
        }

        return 0;
    }
}
