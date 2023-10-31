package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateParserTest {

    @Test
    void convertDate_validArgs() {
        String date1 = "2023-12-12";
        String date2 = "12-12-2023";
        LocalDate localDate1 = DateParser.convertDate(date1);
        LocalDate localDate2 = DateParser.convertDate(date2);
        assertEquals(localDate1, localDate2);
    }

    @Test
    void invalidArgs_throwsParseException() {
        assertFalse(DateParser.isValidCurrentDate("1999-01-01"));
        assertFalse(DateParser.isValidFormat("hello this is not a date"));
    }
}
