package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.CalendarContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose appointment date contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindApptCommand extends Command {

    public static final String COMMAND_WORD = "find appt/";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose appointment date matches "
            + "the specified input date and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " 2023-12-12";

    private final CalendarContainsKeywordsPredicate predicate;

    public FindApptCommand(CalendarContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindApptCommand)) {
            return false;
        }

        FindApptCommand otherCalCommand = (FindApptCommand) other;
        return predicate.equals(otherCalCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
