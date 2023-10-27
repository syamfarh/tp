package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class RiskProfileTest {
    @Test
    public void equals() {
        RiskProfile result = new RiskProfile("a,a,b,b,b,d,e,c");

        // same object -> returns true
        assertTrue(result.equals(result));

        // same values -> returns true
        RiskProfile resultCopy = new RiskProfile(result.value);
        assertTrue(result.equals(resultCopy));

        // different types -> returns false
        assertFalse(result.equals(1));

        // null -> returns false
        assertFalse(result.equals(null));

        // different result -> returns false
        RiskProfile differentResult = new RiskProfile("d,e,c,c,c,e,b,a");
        assertFalse(result.equals(differentResult));

        // Test hashCode()
        assertEquals(result.hashCode(), resultCopy.hashCode());
        assertNotEquals(result.hashCode(), differentResult.hashCode());
    }
}
