package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DateParserTest {

    @Test
    void convertDate_validValue() {
        assertEquals(LocalDate.parse("2020-11-20"), DateParser.convertDate("2020-11-20"));

        assertEquals(LocalDate.parse("2000-11-20"), DateParser.convertDate("11/20/2000"));

        assertEquals(LocalDate.parse("2000-11-20"), DateParser.convertDate("20-11-2000"));
    }

    @Test
    void isValidCurrentDate() {
        assertFalse(DateParser.isValidCurrentDate("2020-11-20"));

        assertFalse(DateParser.isValidCurrentDate("2021-11-20"));

        assertTrue(DateParser.isValidCurrentDate("2023-11-20"));
    }
}
