package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's occupation in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidOccupation(String)}
 */
public class Occupation {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the occupation must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullOccupation;

    /**
     * Constructs a {@code Occupation}.
     *
     * @param occupation A valid occupation.
     */
    public Occupation(String occupation) {
        requireNonNull(occupation);
        checkArgument(isValidOccupation(occupation), MESSAGE_CONSTRAINTS);
        fullOccupation = occupation;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidOccupation(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullOccupation;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Occupation)) {
            return false;
        }

        Occupation otherOccupation = (Occupation) other;
        return fullOccupation.equals(otherOccupation.fullOccupation);
    }

    @Override
    public int hashCode() {
        return fullOccupation.hashCode();
    }

}
