package com.org.primefactorization.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link FileProcessingException}.
 * Ensures that the exception correctly stores the message and cause.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
class FileProcessingExceptionTest {

    /**
     * Tests that the exception correctly stores the provided message and cause.
     */
    @Test
    void testExceptionMessageAndCause() {
        Exception cause = new Exception("Root cause");
        FileProcessingException exception = new FileProcessingException("File processing failed", cause);

        assertEquals("File processing failed", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    /**
     * Tests that the exception can be created with a message and a null cause.
     */
    @Test
    void testExceptionWithoutCause() {
        FileProcessingException exception = new FileProcessingException("File error", null);

        assertEquals("File error", exception.getMessage());
        assertNull(exception.getCause());
    }
}
