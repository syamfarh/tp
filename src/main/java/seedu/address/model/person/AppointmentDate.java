package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.DateParser;

/**
 * Represents a Person's appointment Date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFormat(String)}}
 */
public class AppointmentDate {

    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Appointment Date should follow the format of [mm/dd/yyyy] or [dd-mm-yyyy] or [yyyy-mm-dd]";

    public static final String MESSAGE_CONSTRAINTS_CURRENTDATE =
            "Appointment Date should be after the current date";


    /**
     * Constructs a {@code AppointmentDate}.
     *
     * @param date A valid date.
     */
    public final String value;

    /**
     * Constructs an {@code AppointmentDate}.
     *
     * @param date A valid appointment Date.
     */
    public AppointmentDate(String date) {
        requireNonNull(date);
        value = date;
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
}
