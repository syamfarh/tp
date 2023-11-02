package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKPROFILE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEXES;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CalendarCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindAddCommand;
import seedu.address.logic.commands.FindApptCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindNameCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.QuestionnaireCommand;
import seedu.address.logic.commands.RiskProfileCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.CalendarContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.RiskProfile;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                     + INDEX_SECOND_PERSON.getOneBased() + " " + INDEX_THIRD_PERSON.getOneBased());
        assertEquals(new DeleteCommand(FIRST_INDEXES), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(
                SortCommand.COMMAND_WORD + " " + PREFIX_APPOINTMENTDATE.getPrefix()) instanceof SortCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_questionnaire() throws Exception {
        assertTrue(parser.parseCommand(QuestionnaireCommand.COMMAND_WORD) instanceof QuestionnaireCommand);
        assertTrue(parser.parseCommand(QuestionnaireCommand.COMMAND_WORD + " 3") instanceof QuestionnaireCommand);
    }

    @Test
    public void parseCommand_calendar() throws Exception {
        assertTrue(parser.parseCommand(CalendarCommand.COMMAND_WORD) instanceof CalendarCommand);
        assertTrue(parser.parseCommand(CalendarCommand.COMMAND_WORD + " 3") instanceof CalendarCommand);
    }

    @Test
    public void parseCommand_riskprofile() throws Exception {
        final RiskProfile result = new RiskProfile("a,a,b,c,c,d,d,e");
        RiskProfileCommand command = (RiskProfileCommand) parser.parseCommand(RiskProfileCommand.COMMAND_WORD + " "
            + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_RISKPROFILE + result.value);
        assertEquals(new RiskProfileCommand(INDEX_FIRST_PERSON, result), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_find_returnFindName() throws Exception {
        List<String> findKeywords = Arrays.asList("n/", "alice", "bobby");
        List<String> findNameKeywords = Arrays.asList("alice", "bobby");
        FindNameCommand command = (FindNameCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + findKeywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindNameCommand(new NameContainsKeywordsPredicate(findNameKeywords)), command);
    }

    @Test
    public void parseCommand_find_returnFindAdd() throws Exception {
        List<String> findKeywords = Arrays.asList("a/", "tokyo", "geylang");
        List<String> findAddKeywords = Arrays.asList("tokyo", "geylang");
        FindAddCommand command = (FindAddCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + findKeywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindAddCommand(new AddressContainsKeywordsPredicate(findAddKeywords)), command);
    }

    @Test
    public void parseCommand_find_returnsCalendar() throws Exception {
        List<String> findKeywords = Arrays.asList("appt/", "2023-12-12");
        List<String> calendarKeywords = List.of("2023-12-12");
        FindApptCommand command = (FindApptCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + findKeywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindApptCommand(new CalendarContainsKeywordsPredicate(calendarKeywords)), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
