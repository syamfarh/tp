package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AppointmentDateTest {
    @Test
    public void equals() {
        AppointmentDate apptDate = new AppointmentDate("2022-12-04");

        // same object -> returns true
        assertTrue(apptDate.equals(apptDate));

        // same values -> returns true
        AppointmentDate apptDateCopy = new AppointmentDate(apptDate.value);
        assertTrue(apptDate.equals(apptDateCopy));

        // different types -> returns false
        assertFalse(apptDate.equals(1));

        // null -> returns false
        assertFalse(apptDate.equals(null));

        // different apptDate -> returns false
        AppointmentDate differentApptDate = new AppointmentDate("2020-12-04");
        assertFalse(apptDate.equals(differentApptDate));
    }
}
