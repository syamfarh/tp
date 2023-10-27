package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKPROFILE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.RiskProfile;

/**
 * Adds the risk profile of a person identified by the index number.
 * Existing risk profile will be overwritten by the input.
 */
public class RiskProfileCommand extends Command {
    public static final String COMMAND_WORD = "riskprofile";
    public static final String MESSAGE_ADD_RISKPROFILE_SUCCESS = "Added risk profile to Person: %1$s";
    public static final String MESSAGE_INVALID_RESULT = "Result must have 8 comma-separated characters from 'a' to 'e'."
        + "\n%1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the risk profile of the person identified "
        + "by the index number used in the last person listing. "
        + "Existing risk profile will be overwritten by the input.\n"
        + "Parameters: INDEX (must be a positive integer)"
        + PREFIX_RISKPROFILE + "[RESULT]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_RISKPROFILE + "a,e,b,d,c,a,d,e";

    private final Index index;
    private final RiskProfile result;

    /**
     * @param index of the person in the filtered person list to edit the risk profile
     * @param result of the person's quiz which will be generated into risk profile to be updated to
     */
    public RiskProfileCommand(Index index, RiskProfile result) {
        requireAllNonNull(index, result);

        this.index = index;
        this.result = result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!isValidResult(result.value)) {
            throw new CommandException(String.format(MESSAGE_INVALID_RESULT, MESSAGE_USAGE));
        }

        int totalScore = calculateTotalScore(result.value);
        String riskLevel = calculateRiskLevel(totalScore);

        RiskProfile riskProfile = new RiskProfile(riskLevel);

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
            personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(), personToEdit.getOccupation(),
            personToEdit.getAddress(), personToEdit.getApptDate(), riskProfile, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message when
     * the risk profile is added.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_RISKPROFILE_SUCCESS, personToEdit);
    }

    /**
     * Calculates the total score from the result.
     *
     * @param result The result of risk profile.
     * @return The total score calculated from the result.
     */
    public static int calculateTotalScore(String result) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countE = 0;

        for (char ch : result.toCharArray()) {
            switch (ch) {
            case 'a':
                countA++;
                break;
            case 'b':
                countB++;
                break;
            case 'c':
                countC++;
                break;
            case 'd':
                countD++;
                break;
            case 'e':
                countE++;
                break;
            default:
                break;
            }
        }

        return countA + countB * 2 + countC * 3 + countD * 4 + countE * 5;
    }

    /**
     * Calculates the risk level based on the total score.
     *
     * @param totalScore The total score calculated from the result.
     * @return The risk level determined from the total score.
     */
    public static String calculateRiskLevel(int totalScore) {
        String result;
        switch (totalScore) {
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
            result = "Low";
            break;
        case 9:
        case 10:
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
            result = "Moderately Low";
            break;
        case 17:
        case 18:
        case 19:
        case 20:
        case 21:
        case 22:
        case 23:
        case 24:
            result = "Moderate";
            break;
        case 25:
        case 26:
        case 27:
        case 28:
        case 29:
        case 30:
        case 31:
        case 32:
            result = "Moderately High";
            break;
        case 33:
        case 34:
        case 35:
        case 36:
        case 37:
        case 38:
        case 39:
        case 40:
            result = "High";
            break;
        default:
            result = "";
            break;
        }
        return result;
    }

    /**
     * Checks if the given input is a valid result.
     *
     * @param result Result to be validated.
     * @return {@code true} if the input is a valid result, {@code false} otherwise.
     */
    public static boolean isValidResult(String result) {
        String[] characters = result.split(",");
        if (characters.length != 8) {
            return false;
        }

        for (String character : characters) {
            if (!character.matches("[abcde]")) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RiskProfileCommand)) {
            return false;
        }

        RiskProfileCommand e = (RiskProfileCommand) other;
        return index.equals(e.index)
            && result.equals(e.result);
    }
}
