package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        while (model.getAddressBookSize() > 0) {
            int targetIndex = model.getAddressBookSize() - 1;
            Person personToDelete = lastShownList.get(targetIndex);
            model.deletePerson(personToDelete);
        }
        //model.setAddressBook(new AddressBook());
        model.storePreviousUndoableCommand(COMMAND_WORD);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
