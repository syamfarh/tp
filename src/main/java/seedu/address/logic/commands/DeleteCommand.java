package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person(s) identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEXES (must be positive integers, separated by spaces, no duplicates)\n"
            + "Example: " + COMMAND_WORD + " 1 3 5";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person(s): %1$s";

    private final List<Index> targetIndexes;

    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> deletedPersons = new ArrayList<>();
        int currentIndex = 0; // Initialize the current index

        for (Index targetIndex : targetIndexes) {
            int zeroBasedIndex = targetIndex.getZeroBased() - currentIndex; // Decrement the index
            if (zeroBasedIndex >= lastShownList.size() || zeroBasedIndex < 0) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        for (Index targetIndex : targetIndexes) {
            int zeroBasedIndex = targetIndex.getZeroBased() - currentIndex;
            Person personToDelete = lastShownList.get(zeroBasedIndex);
            model.deletePerson(personToDelete);
            model.storePreviousUndoableCommand(COMMAND_WORD);
            deletedPersons.add(personToDelete);

            currentIndex++;
        }

        model.storeDeletedNumberList(targetIndexes.size());

        model.resetRedoableStateList();
        model.resetUndoableStateList();
        model.removeRedoCommands();

        String resultMessage = String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.formatPersons(deletedPersons));

        return new CommandResult(resultMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndexes.equals(otherDeleteCommand.targetIndexes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndexes)
                .toString();
    }
}
