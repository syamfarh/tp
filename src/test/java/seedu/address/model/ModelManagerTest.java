package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.EDITEDALICE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    private final Person testPersonAlice = ALICE;
    private final Person testPersonAliceEdited = EDITEDALICE;
    private final Person testPersonBenson = BENSON;

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }


    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    // Unit testing methods
    @Test
    public void storeAddedPerson() {
        modelManager.storeAddedPerson(testPersonAlice);
        assertEquals(testPersonAlice, modelManager.getAddedPerson());
    }

    //Should I remove this test for a getter?
    @Test
    public void getAddedPerson() {
        modelManager.storeAddedPerson(testPersonAlice);
        modelManager.storeAddedPerson(testPersonBenson);
        assertEquals(modelManager.getAddedPerson(), testPersonBenson);
    }

    @Test
    public void removeAddedPerson() {
        modelManager.storeAddedPerson(testPersonAlice);
        modelManager.storeAddedPerson(testPersonBenson);
        modelManager.removeAddedPerson();
        assertEquals(modelManager.getAddedPerson(), testPersonAlice);
    }

    @Test
    public void removeAddedPerson_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.removeAddedPerson());
    }


    @Test
    public void storeDeletedPerson() {
        modelManager.storeDeletedPerson(testPersonAlice);
        assertEquals(testPersonAlice, modelManager.getDeletedPerson());
    }

    @Test
    public void removeDeletedPerson() {
        modelManager.storeDeletedPerson(testPersonAlice);
        modelManager.storeDeletedPerson(testPersonBenson);
        modelManager.removeDeletedPerson();
        assertEquals(modelManager.getDeletedPerson(), testPersonAlice);
    }

    @Test
    public void removeDeletedPerson_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.removeDeletedPerson());
    }

    @Test
    public void storePreviousUndoableCommand() {
        modelManager.storePreviousUndoableCommand("TEST");
        assertEquals(modelManager.getPreviousUndoableCommand(), "TEST");
    }

    @Test
    public void removePreviousUndoableCommand() {
        modelManager.storePreviousUndoableCommand("TEST1");
        modelManager.storePreviousUndoableCommand("TEST2");
        modelManager.removePreviousUndoableCommand();
        assertEquals(modelManager.getPreviousUndoableCommand(), "TEST1");
    }

    @Test
    public void removePreviousUndoableCommand_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.removePreviousUndoableCommand());
    }

    @Test
    public void storeEditedPersonsPair() {
        Pair<Person, Person> testPersons = new Pair<>(testPersonAlice, testPersonBenson);
        modelManager.storeEditedPersonsPair(testPersonAlice, testPersonBenson);
        assertEquals(modelManager.getEditedPersonsPair(), testPersons);
    }

    @Test
    public void removeEditedPersonsPair() {
        Pair<Person, Person> testPersons = new Pair<>(testPersonAlice, testPersonBenson);
        modelManager.storeEditedPersonsPair(testPersonAlice, testPersonBenson);
        modelManager.storeEditedPersonsPair(testPersonBenson, testPersonAlice);
        modelManager.removeEditedPersonsPair();
        assertEquals(modelManager.getEditedPersonsPair(), testPersons);
    }

    @Test
    public void removeEditedPersonsPair_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.removeEditedPersonsPair());
    }

    @Test
    public void storeDeletedNumberList() {
        modelManager.storeDeletedNumberList(1);
        assertEquals(modelManager.getLastDeletedNumber(), 1);
    }

    @Test
    public void removeLastDeletedNumber() {
        modelManager.storeDeletedNumberList(1);
        modelManager.storeDeletedNumberList(2);
        modelManager.removeLastDeletedNumber();
        assertEquals(modelManager.getLastDeletedNumber(), 1);
    }

    @Test
    public void removeLastDeletedNumber_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> modelManager.removeLastDeletedNumber());
    }

    @Test
    public void addPerson_storesAddedPersonInArrayList() {
        modelManager.addPerson(testPersonAlice);
        assertEquals(modelManager.getAddedPerson(), testPersonAlice);
    }

    @Test
    public void undoDelete() {
        modelManager.addPerson(testPersonAlice);

        //Make sure person is added into addedPersons array list.
        assertEquals(modelManager.getAddedPersonsSize(), 1);

        modelManager.deletePerson(testPersonAlice);

        //Make sure person is added into deletedPersons array list.
        assertEquals(modelManager.getDeletedPersonsSize(), 1);

        modelManager.undoDelete();

        //Make sure the person is added back to the address book.
        assertTrue(modelManager.hasPerson(testPersonAlice));
        //Make sure the deleted person is removed from the deletedPersons array list.
        assertEquals(modelManager.getDeletedPersonsSize(), 0);
        //Make sure the addedPersons array list is unchanged.
        assertEquals(modelManager.getAddedPersonsSize(), 1);
    }

    @Test
    public void undoAdd() {
        modelManager.addPerson(testPersonAlice);

        //Make sure person is added into addedPersons array list.
        assertEquals(modelManager.getAddedPersonsSize(), 1);

        modelManager.undoAdd();

        //Make sure person is removed from addedPersons array list.
        assertEquals(modelManager.getAddedPersonsSize(), 0);
        //Make sure person is deleted from address book.
        assertFalse(modelManager.hasPerson(testPersonAlice));
    }

    @Test
    public void undoEdit() {
        modelManager.addPerson(testPersonAlice);
        modelManager.setPerson(testPersonAlice, testPersonAliceEdited);

        modelManager.storeEditedPersonsPair(testPersonAliceEdited, testPersonAlice);

        //Make sure pair is added into editedPersons array list.
        assertEquals(modelManager.getEditedPersonsSize(), 1);

        modelManager.undoEdit();

        //Make sure pair is removed from editedPersons array list.
        assertEquals(modelManager.getEditedPersonsSize(), 0);

    }


}
