package seedu.address.commons.util;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * A class that store comparator for sorting Person class.
 */
public class ComparatorUtil {
    public static final Comparator<Person> APPTCOMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getApptDate().compareTo(o2.getApptDate());
        }
    };

    public static final Comparator<Person> NAMECOMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
}
