package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    public static final String MESSAGE_FAILURE = "Address book is empty. There is nothing to clear.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (model.getAddressBookSize() == 0) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        while (model.getAddressBookSize() > 0) {
            int targetIndex = model.getAddressBookSize() - 1;
            Person personToDelete = lastShownList.get(targetIndex);
            model.deletePerson(personToDelete);
        }
        //model.setAddressBook(new AddressBook());

        if (model.getDeletedPersonsSize() <= model.getNumberOfPreviousDeleteCommands()) {
            throw new AssertionError("Assertion Error: Previous clear command "
                    + "did not store deleted persons.");
        }

        model.storePreviousUndoableCommand(COMMAND_WORD);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
