package com.org.primefactorization.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link NonPositiveNumberException}.
 * Ensures the exception correctly stores and retrieves the provided message.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
class NonPositiveNumberExceptionTest {

    /**
     * Tests that the exception correctly stores and returns the provided message.
     */
    @Test
    void testExceptionMessage() {
        NonPositiveNumberException exception = new NonPositiveNumberException("Non-positive number encountered");

        assertEquals("Non-positive number encountered", exception.getMessage());
    }

    /**
     * Tests that the exception allows null as a message and returns null.
     */
    @Test
    void testExceptionWithNullMessage() {
        NonPositiveNumberException exception = new NonPositiveNumberException(null);

        assertNull(exception.getMessage());
    }
}
