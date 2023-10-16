package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.DateParser;

/**
 * Represents a Person's appointment Date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFormat(String)}}
 */
public class AppointmentDate implements Comparable<AppointmentDate> {

    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Appointment Date should follow the format of [mm/dd/yyyy] or [dd-mm-yyyy] or [yyyy-mm-dd]";

    public static final String MESSAGE_CONSTRAINTS_CURRENTDATE =
            "Appointment Date should be after the current date";



    public final String value;

    public final LocalDate valueInLocalDate;

    /**
     * Constructs an {@code AppointmentDate}.
     *
     * @param date A valid appointment Date in String type.
     */
    public AppointmentDate(String date) {
        requireNonNull(date);
        value = date;
        if (date.equals("")) {
            valueInLocalDate = LocalDate.now();
        } else {
            valueInLocalDate = DateParser.convertDate(date);
        }
    }

    /**
     * Constructs an {@code AppointmentDate}.
     *
     * @param date A valid appointment Date in LocalDate type.
     */
    public AppointmentDate(LocalDate date) {
        requireNonNull(date);
        valueInLocalDate = date;
        value = date.toString();
    }

    /**
     * Returns true if a given string is a valid format date.
     */
    public static boolean isValidFormat(String test) {
        try {
            DateParser.convertDate(test);
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
            return DateParser.isValidCurrentDate(test);
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

        if (valueInLocalDate.isEqual(o.valueInLocalDate)) {
            return 0;
        } else if (valueInLocalDate.isAfter(o.valueInLocalDate)) {
            return 1;
        } else if (valueInLocalDate.isBefore(o.valueInLocalDate)) {
            return -1;
        }

        return 0;
    }
}
