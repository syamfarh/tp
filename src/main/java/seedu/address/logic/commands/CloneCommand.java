package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * Clones a person identified using it's displayed index from the address book.
 */
public class CloneCommand extends Command {

    public static final String COMMAND_WORD = "clone";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clones the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLONE_PERSON_SUCCESS = "Cloned Person: %1$s";

    public static final String MESSAGE_CLONE_PERSON_DUPLICATE_FAILURE = "A clone of this person already exists. To "
        + "clone again, please edit the previous clone first or alternatively, clone the previous clone.";

    private final Index targetIndex;

    public CloneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Assertion Error: model is null.";
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToClone = lastShownList.get(targetIndex.getZeroBased());

        Person clonedPerson = clonePerson(personToClone);
        try {
            model.addPerson(clonedPerson);
            return new CommandResult(String.format(MESSAGE_CLONE_PERSON_SUCCESS, Messages.format(personToClone)));
        } catch (DuplicatePersonException e) {
            throw new CommandException(String.format(MESSAGE_CLONE_PERSON_DUPLICATE_FAILURE));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CloneCommand)) {
            return false;
        }

        CloneCommand otherCloneCommand = (CloneCommand) other;
        return targetIndex.equals(otherCloneCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }

    /**
     * Creates a cloned Person object with an incremented numeric suffix in the name.
     *
     * @param personToClone The original Person object to be cloned.
     * @return A new Person object with the name having an incremented numeric suffix.
     */
    private Person clonePerson(Person personToClone) {
        int numericSuffix;
        String numericSuffixStr = personToClone.getName().toString().replaceAll("[^0-9]", "");
        String nameWithoutNumbers = personToClone.getName().toString().replaceAll("[0-9]", "");
        if (numericSuffixStr.isEmpty()) {
            numericSuffix = 0;
        } else {
            numericSuffix = Integer.parseInt(numericSuffixStr);
        }
        numericSuffix++;
        String updatedNumericSuffixStr = String.valueOf(numericSuffix);
        Name clonedName = new Name(nameWithoutNumbers + " " + updatedNumericSuffixStr);
        Person clonedPerson = new Person(clonedName, personToClone.getPhone(), personToClone.getEmail(),
                personToClone.getOccupation(), personToClone.getAddress(), personToClone.getApptDate(),
                personToClone.getTags());
        return clonedPerson;
    }
}
