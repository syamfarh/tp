package seedu.address.logic;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_NO_MULTIPLE_PREFIXES = "You are not allowed to search up multiple prefixes!\n"
            + "Please search for either name or address or appointment date at a time.";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_PERSON_SUFFIX_OUT_OF_RANGE =
        "The integer suffix of the person being cloned is out of range. Please note that the smallest possible suffix"
        + " that a person can have is 1 and the largest possible suffix that a person can have is 2147483647. As such,"
        + " if your suffix is 0 or 2147483647, please consider editing the names of your contacts first.";

    public static final String MESSAGE_WRONG_DATE_FORMAT =
            "Appointment Date should follow the format of "
                    + "[mm/dd/yyyy] or [dd-mm-yyyy] or [yyyy-mm-dd].";

    public static final String MESSAGE_INVALID_INPUT_DATE =
            "Appointment Date should be after the current date.";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    public static String getErrorMessageForMultiplePrefixes() {
        return MESSAGE_NO_MULTIPLE_PREFIXES;
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Occupation: ")
                .append(person.getOccupation())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; AppointmentDate: ")
                .append(person.getApptDate())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the list of persons for display to the user.
     */
    public static String formatPersons(List<Person> personList) {
        final StringBuilder builder = new StringBuilder();

        for (Person person : personList) {
            builder.append("\n")
                    .append(person.getName())
                    .append("; Phone: ").append(person.getPhone())
                    .append("; Email: ").append(person.getEmail())
                    .append("; Occupation: ").append(person.getOccupation())
                    .append("; Address: ").append(person.getAddress())
                    .append("; AppointmentDate: ").append(person.getApptDate())
                    .append("; Tags: ");
            person.getTags().forEach(builder::append);
        }

        return builder.toString();
    }


}
