package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DateTimeParserTest {

    @Test
    void convertDate_validValue() {
        assertEquals(LocalDateTime.parse("2020-11-20 23:00", DateTimeParser.STRINGTODATETIME.toFormatter()),
                DateTimeParser.convertDate("2020-11-20 23:00"));

        assertEquals(LocalDateTime.parse("2000-11-20 23:00", DateTimeParser.STRINGTODATETIME.toFormatter()),
                DateTimeParser.convertDate("11/20/2000 23:00"));

        assertEquals(LocalDateTime.parse("2000-11-20 23:00", DateTimeParser.STRINGTODATETIME.toFormatter()),
                DateTimeParser.convertDate("20-11-2000 23:00"));
    }

    @Test
    void isValidCurrentDate() {
        assertFalse(DateTimeParser.isValidCurrentDateTime("2023-10-23 14:00"));

        assertFalse(DateTimeParser.isValidCurrentDateTime("2021-11-20 23:00"));

        assertTrue(DateTimeParser.isValidCurrentDateTime("2023-11-20 23:00"));
    }
}
