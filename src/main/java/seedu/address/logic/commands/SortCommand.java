package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sort all persons in address book by the parameter.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort all persons by name or appointment date.\n"
            + "Parameters: KEYWORD [PREFIX n/ or appt/]\n"
            + "Example: " + COMMAND_WORD + " n/";


    private final Comparator<Person> sortComparator;

    public SortCommand(Comparator<Person> sortComparator) {
        this.sortComparator = sortComparator;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortComparator(sortComparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherFindCommand = (SortCommand) other;
        return sortComparator.equals(otherFindCommand.sortComparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparator", sortComparator)
                .toString();
    }
}
