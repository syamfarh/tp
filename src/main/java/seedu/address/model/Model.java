package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the comparator of the filtered person list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateSortComparator(Comparator<Person> comparator);

    /**
     * Updates and stores the most recently deleted person in the deletedPersons ArrayList.
     * @param deletedPerson
     */
    void storeDeletedPerson(Person deletedPerson);

    /**
     * Returns the most recently deleted person in the deletedPersons ArrayList.
     */
    Person getDeletedPerson();

    /**
     * Removes the most recently deleted person from the deletedPersons ArrayList.
     */
    void removeDeletedPerson();

    /**
     * Returns the size of the deletedPersons ArrayList.
     */
    int getDeletedPersonsSize();

    /**
     * Returns a list of persons that have been previously deleted.
     *
     * @return A list of Person objects representing previously deleted persons.
     */
    ArrayList<Person> getDeletedPersons();

    /**
     * Returns the number of undoable commands in the previousUndoableCommands ArrayList.
     */
    int getPreviousUndoableCommandsSize();

    /**
     * Returns the number of delete commands in the previousUndoableCommands ArrayList.
     */
    int getNumberOfPreviousDeleteCommands();

    /**
     * Undoes the most recent delete command.
     * This method should only be invoked when the previous command is a delete command.
     */
    void undoDelete();

    /**
     * Undoes the most recent add command.
     * This method should only be invoked when the previous command is an add command.
     */
    void undoAdd();

    /**
     * Stores the command as a String into the previousUndoableCommands ArrayList.
     * @param s the command as a String
     */
    void storePreviousUndoableCommand(String s);

    /**
     * Returns the most recent undoable command as a String from the previousUndoableCommands ArrayList.
     */
    String getPreviousUndoableCommand();

    /**
     * Removes the most recent command from the previousUndoableCommands ArrayList.
     */
    void removePreviousUndoableCommand();

    /**
     * Returns the size of the address book, which is the number of contacts in the address book.
     */
    int getAddressBookSize();

    /**
     * Stores a pair of persons, editedPerson and originalPerson, in the editedPersons ArrayList.
     * @param editedPerson The already edited person.
     * @param originalPerson The original person before edit.
     */
    void storeEditedPersonsPair(Person editedPerson, Person originalPerson);

    /**
     * Returns the most recently added pair of persons, editedPerson and originalPerson,
     * from the editedPersons ArrayList.
     */
    Pair<Person, Person> getEditedPersonsPair();

    /**
     * Removes the most recently added pair of persons, editedPerson and originalPerson,
     * from the editedPersons ArrayList.
     */
    void removeEditedPersonsPair();

    /**
     * Stores the number of deletes associated with a delete command in a queue.
     */
    void storeDeletedNumberList(int deletedNumber);

    /**
     * Returns the number associated with the last delete command.
     */
    Integer getLastDeletedNumber();

    /**
     * Returns the deleted number list.
     */
    ArrayList<Integer> getDeletedNumberList();

    /**
     * Removes the last number from the number list.
     */
    void removeLastNumber();

    /**
     * Updates and stores the most recently added person in the addedPersons ArrayList.
     * Note that this method is invoked for a clone command too, since a clone will still add a person.
     * @param addedPerson the person that was most recently added.
     */
    void storeAddedPerson(Person addedPerson);

    /**
     * Returns the most recently added person in the addedPersons ArrayList.
     */
    Person getAddedPerson();

    /**
     * Removes the most recently added person from the addedPersons ArrayList.
     */
    void removeAddedPerson();
}
