package com.org.primefactorization.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link InvalidFileException}.
 * Ensures that the exception correctly stores the provided message.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
class InvalidFileExceptionTest {

    /**
     * Tests that the exception correctly stores the provided message.
     */
    @Test
    void testExceptionMessage() {
        InvalidFileException exception = new InvalidFileException("Invalid file path");

        assertEquals("Invalid file path", exception.getMessage());
    }

    /**
     * Tests that the exception can be created with a null message.
     */
    @Test
    void testExceptionWithNullMessage() {
        InvalidFileException exception = new InvalidFileException(null);

        assertNull(exception.getMessage());
    }
}
