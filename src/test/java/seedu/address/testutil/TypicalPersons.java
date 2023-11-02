package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_JOHN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENTDATE_JOHN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_JOHN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_JOHN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_JOHN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKPROFILE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RISKPROFILE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withOccupation("Barber").withAppointmentDate("2023-12-13 08:30")
            .withRiskProfile("a,a,b,b,b,d,e,c").withTags("friends").build();
    public static final Person EDITEDALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("notalice@example.com")
            .withPhone("94351253").withOccupation("Clerk").withAppointmentDate("")
            .withRiskProfile("a,a,b,b,b,d,e,c").withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withOccupation("Musician")
            .withAppointmentDate("2023-12-14 08:30").withRiskProfile("d,c,a,e,b,b,c,a")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withAppointmentDate("2023-12-15 08:30")
            .withEmail("heinz@example.com").withOccupation("Sheep Farmer").withAddress("wall street")
            .withRiskProfile("").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withOccupation("Lecturer").withAddress("10th street")
            .withAppointmentDate("2023-12-16 08:30").withRiskProfile("").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withAppointmentDate("2023-12-17 08:30").withEmail("werner@example.com").withOccupation("Seamstress")
            .withAddress("michegan ave").withRiskProfile("").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withAppointmentDate("2023-12-18 08:30").withEmail("lydia@example.com").withOccupation("Princess")
            .withAddress("little tokyo").withRiskProfile("").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withAppointmentDate("2023-12-19 08:30").withEmail("george@example.com").withOccupation("Zoologist")
            .withAddress("4th street").withRiskProfile("").build();
    public static final Person KHALID = new PersonBuilder().withName("Khalid")
            .withAddress("5th Street").withEmail("khalid@example.com")
            .withPhone("94352433").withOccupation("Golfer").withAppointmentDate("2023-12-20 08:30")
            .withRiskProfile("").withTags("friends").build();
    public static final Person LENNY = new PersonBuilder().withName("Lenny David")
            .withAddress("6th Street").withEmail("lenny@example.com")
            .withPhone("94932143").withOccupation("Fisherman").withAppointmentDate("2023-12-21 08:30")
            .withRiskProfile("").withTags("friends").build();
    public static final Person LENNY1 = new PersonBuilder().withName("Lenny David 1")
            .withAddress("6th Street").withEmail("lenny@example.com")
            .withPhone("94932143").withOccupation("Fisherman").withAppointmentDate("2023-12-22 08:30")
            .withRiskProfile("").withTags("friends").build();
    public static final Person OWEN = new PersonBuilder().withName("Owen Davis 2147483647")
            .withAddress("7th Street").withEmail("owen@example.com")
            .withPhone("94932143").withOccupation("Fisherman").withAppointmentDate("2023-12-23 08:30")
            .withRiskProfile("").withTags("friends").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withAppointmentDate("").withEmail("stefan@example.com").withOccupation("Unemployed")
            .withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withAppointmentDate("").withEmail("hans@example.com").withOccupation("Chef")
            .withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withOccupation("Barber").withAddress(VALID_ADDRESS_AMY)
            .withAppointmentDate("").withRiskProfile(VALID_RISKPROFILE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withOccupation("Software Engineer").withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withAppointmentDate("")
            .withRiskProfile(VALID_RISKPROFILE_BOB).build();

    public static final Person JOHN = new PersonBuilder().withName(VALID_NAME_JOHN).withPhone(VALID_PHONE_JOHN)
            .withEmail(VALID_EMAIL_JOHN).withOccupation("Chef").withAddress(VALID_ADDRESS_JOHN)
            .withAppointmentDate(VALID_APPOINTMENTDATE_JOHN).withRiskProfile("")
            .build();



    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(OWEN, LENNY, LENNY1, KHALID, ALICE, BENSON, CARL, DANIEL, ELLE, FIONA,
                GEORGE, JOHN));
    }
}
