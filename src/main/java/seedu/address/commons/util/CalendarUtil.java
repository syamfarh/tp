package seedu.address.commons.util;

import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;

import seedu.address.model.person.Person;

/**
 * Helper function for handling calendar entry.
 */
public class CalendarUtil {

    /**
     * Return {@code Entry} with {@code Person} appointment date.
     * @param person
     */
    public static Entry createNewEntry(Person person) {
        Interval interval =
                new Interval(person.getApptDate().valueInLocalDateTime,
                        person.getApptDate().valueInLocalDateTime.plusHours(1));
        Entry entry = new Entry(person.getName().toString(), interval);
        return entry;
    }
}
