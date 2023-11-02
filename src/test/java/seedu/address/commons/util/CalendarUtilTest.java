package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;

class CalendarUtilTest {

    @Test
    public void createNewEntry_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CalendarUtil.createNewEntry(null));
    }

    @Test
    public void createNewEntry_success() {

        Interval intervalAlice = new Interval(ALICE.getApptDate().valueInLocalDateTime,
                ALICE.getApptDate().valueInLocalDateTime.plusHours(1));

        Entry expectedEntryAlice = new Entry(ALICE.getName().toString(), intervalAlice, ALICE.getName().toString());

        assertEquals(expectedEntryAlice, CalendarUtil.createNewEntry(ALICE));
    }

    @Test
    public void createNewEntry_difference() {

        Interval intervalAlice = new Interval(ALICE.getApptDate().valueInLocalDateTime,
                ALICE.getApptDate().valueInLocalDateTime.plusHours(1));

        Entry expectedEntryAlice = new Entry(ALICE.getName().toString(), intervalAlice, ALICE.getName().toString());

        assertFalse(expectedEntryAlice.equals(CalendarUtil.createNewEntry(BENSON)));
    }

}
