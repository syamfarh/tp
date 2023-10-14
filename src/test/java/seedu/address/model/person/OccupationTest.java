package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OccupationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Occupation(null));
    }

    @Test
    public void constructor_invalidOccupation_throwsIllegalArgumentException() {
        String invalidOccupation = "";
        assertThrows(IllegalArgumentException.class, () -> new Occupation(invalidOccupation));
    }

    @Test
    public void isValidOccupation() {
        // null occupation
        assertThrows(NullPointerException.class, () -> Occupation.isValidOccupation(null));

        // invalid occupation
        assertFalse(Occupation.isValidOccupation("")); // empty string
        assertFalse(Occupation.isValidOccupation(" ")); // spaces only
        assertFalse(Occupation.isValidOccupation("^")); // only non-alphanumeric characters

        // valid occupation
        assertTrue(Occupation.isValidOccupation("Software Engineer")); // alphabets and spaces
        assertTrue(Occupation.isValidOccupation("Doctor 2nd")); // alphanumeric characters and spaces
        assertTrue(Occupation.isValidOccupation("CEO")); // short, all capital letters
    }

    @Test
    public void equals() {
        Occupation occupation = new Occupation("Software Engineer");

        // same values -> returns true
        assertTrue(occupation.equals(new Occupation("Software Engineer")));

        // same object -> returns true
        assertTrue(occupation.equals(occupation));

        // null -> returns false
        assertFalse(occupation.equals(null));

        // different types -> returns false
        assertFalse(occupation.equals(5.0f));

        // different values -> returns false
        assertFalse(occupation.equals(new Occupation("Doctor")));
    }
}
