package com.org.primefactorization.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link InvalidNumberFormatException}.
 * Ensures the exception correctly stores and retrieves the provided message.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
class InvalidNumberFormatExceptionTest {

    /**
     * Tests that the exception correctly stores and returns the provided message.
     */
    @Test
    void testExceptionMessage() {
        InvalidNumberFormatException exception = new InvalidNumberFormatException("Invalid number format");

        assertEquals("Invalid number format", exception.getMessage());
    }

    /**
     * Tests that the exception allows null as a message and returns null.
     */
    @Test
    void testExceptionWithNullMessage() {
        InvalidNumberFormatException exception = new InvalidNumberFormatException(null);

        assertNull(exception.getMessage());
    }
}
