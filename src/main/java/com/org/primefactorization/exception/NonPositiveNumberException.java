package com.org.primefactorization.exception;

/**
 * Exception thrown when a non-positive number (zero or negative) is encountered.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
public class NonPositiveNumberException extends RuntimeException {
    public NonPositiveNumberException(String message) {
        super(message);
    }
}
