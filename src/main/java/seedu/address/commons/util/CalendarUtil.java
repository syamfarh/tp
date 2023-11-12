package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;

import seedu.address.model.person.Person;

/**
 * Helper function for converting Person's AppointmentDate to calendar Entry.
 */
public class CalendarUtil {

    /**
     * Return {@code Entry} with {@code Person} appointment date.
     * @param person
     */
    public static Entry createNewEntry(Person person) {
        requireNonNull(person);
        Interval interval =
                new Interval(person.getApptDate().valueInLocalDateTime,
                        person.getApptDate().valueInLocalDateTime.plusHours(1));
        Entry entry = new Entry(person.getName().toString(), interval, person.getName().toString());
        return entry;
    }
}
