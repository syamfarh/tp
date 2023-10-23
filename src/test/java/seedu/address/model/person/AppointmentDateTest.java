package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

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

    @Test
    public void isValidCurrentDate() {
        assertTrue(AppointmentDate.isValidCurrentDate("2050-11-20"));
        assertFalse(AppointmentDate.isValidCurrentDate("2000-11-20"));
    }

    @Test
    void compareTo() {
        AppointmentDate earlierDate = new AppointmentDate("2000-11-20");
        AppointmentDate laterDate = new AppointmentDate("2030-11-20");
        AppointmentDate currentDate = new AppointmentDate(LocalDate.now());

        assertEquals(0, currentDate.compareTo(currentDate));
        assertEquals(1, currentDate.compareTo(earlierDate));
        assertEquals(-1, currentDate.compareTo(laterDate));
    }
}
