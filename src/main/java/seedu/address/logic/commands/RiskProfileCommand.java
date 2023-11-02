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
    public static final String MESSAGE_ADD_RISKPROFILE_SUCCESS = "Added risk profile to Person: %1$s; Phone: %2$s; "
        + "Email: %3$s; Occupation: %4$s; Address: %5$s; "
        + "Appointment Date: %6$s; Risk Profile: %7$s; Tags: %8$s";
    public static final String MESSAGE_INVALID_RESULT = "Result must have 8 comma-separated characters from 'a' to 'e'!"
        + "\n%1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the risk profile of the person identified "
        + "by the index number used in the last person listing. "
        + "Existing risk profile will be overwritten by the input.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + PREFIX_RISKPROFILE + "[RESULT]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_RISKPROFILE + "a,e,b,d,c,a,d,e";

    private final Index personToEdit;
    private final RiskProfile result;

    /**
     * @param index of the person in the filtered person list to edit the risk profile
     * @param result of the person's quiz which will be generated into risk profile to be updated to
     */
    public RiskProfileCommand(Index index, RiskProfile result) {
        requireAllNonNull(index, result);

        this.personToEdit = index;
        this.result = result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personToEdit.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!isValidResult(result.value)) {
            throw new CommandException(String.format(MESSAGE_INVALID_RESULT, MESSAGE_USAGE));
        }

        int totalScore = calculateTotalScore(result.value);
        String riskLevel = calculateRiskLevel(totalScore);

        RiskProfile riskProfile = new RiskProfile(riskLevel);

        Person personToEdit = lastShownList.get(this.personToEdit.getZeroBased());
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
        return String.format(MESSAGE_ADD_RISKPROFILE_SUCCESS,
            personToEdit.getName(),
            personToEdit.getPhone(),
            personToEdit.getEmail(),
            personToEdit.getOccupation(),
            personToEdit.getAddress(),
            personToEdit.getApptDate(),
            personToEdit.getRiskProfile(),
            personToEdit.getTags());
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
        if (totalScore >= 1 && totalScore <= 8) {
            result = "Low";
        } else if (totalScore > 8 && totalScore <= 16) {
            result = "Moderately Low";
        } else if (totalScore > 16 && totalScore <= 24) {
            result = "Moderate";
        } else if (totalScore > 24 && totalScore <= 32) {
            result = "Moderately High";
        } else if (totalScore > 32 && totalScore <= 40) {
            result = "High";
        } else {
            result = "";
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
        return personToEdit.equals(e.personToEdit)
            && result.equals(e.result);
    }
}
