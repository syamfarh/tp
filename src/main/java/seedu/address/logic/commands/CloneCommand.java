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
            + "Parameters: INDEX (must be a positive integer).\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLONE_PERSON_SUCCESS = "Cloned Person: %1$s";

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

        while (true) {
            try {
                model.addPerson(clonedPerson);
                model.storePreviousUndoableCommand(COMMAND_WORD);

                model.resetRedoableStateList();
                model.resetUndoableStateList();
                model.removeRedoCommands();

                return new CommandResult(String.format(MESSAGE_CLONE_PERSON_SUCCESS, Messages.format(personToClone)));
            } catch (DuplicatePersonException e) {
                clonedPerson = clonePerson(clonedPerson);
            }
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
    public static Person clonePerson(Person personToClone) throws CommandException {
        String originalName = personToClone.getName().toString();
        String[] parts = splitStringAtLastSpace(originalName);
        int numericSuffix;
        String updatedName = null;

        if (parts[1].isEmpty()) {
            // Case 1: name has no spaces
            numericSuffix = 1; // Add a default numeric suffix of 1
            updatedName = parts[0] + " " + numericSuffix;
        } else if (!parts[1].matches("\\d+")) {
            // Case 2: name has spaces but suffix is not only integers
            numericSuffix = 1; // Add a default numeric suffix of 1
            updatedName = parts[0] + " " + parts[1] + " " + numericSuffix;
        } else if (parts[1].matches("\\d+")) {
            // Case 3: name has spaces and suffix is only an integer
            numericSuffix = Integer.parseInt(parts[1].trim());

            // Check if the numeric suffix is within a valid range
            if (0 < numericSuffix && numericSuffix < Integer.MAX_VALUE) {
                numericSuffix++;
                updatedName = parts[0] + " " + numericSuffix;
            } else {
                throw new CommandException(Messages.MESSAGE_PERSON_SUFFIX_OUT_OF_RANGE);
            }
        }

        Name clonedName = new Name(updatedName);
        Person clonedPerson = new Person(clonedName, personToClone.getPhone(), personToClone.getEmail(),
                personToClone.getOccupation(), personToClone.getAddress(), personToClone.getApptDate(),
                personToClone.getRiskProfile(), personToClone.getTags());
        return clonedPerson;
    }

    /**
     * Splits a given string into two substrings using the last space as the delimiter.
     *
     * @param input The input string to be split.
     * @return An array of two strings. The first element contains the characters before the last space,
     *         and the second element contains the characters after the last space. If there are no spaces
     *         in the input string, the first element contains the original string, and the second element
     *         is an empty string.
     */
    public static String[] splitStringAtLastSpace(String input) {
        int lastIndex = input.lastIndexOf(" ");
        if (lastIndex >= 0) {
            String part1 = input.substring(0, lastIndex);
            String part2 = input.substring(lastIndex + 1);
            return new String[]{part1, part2};
        } else {
            // Handle the case where there are no spaces in the string
            return new String[]{input, ""};
        }
    }
}
