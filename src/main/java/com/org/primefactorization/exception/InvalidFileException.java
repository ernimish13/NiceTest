package com.org.primefactorization.exception;

/**
 * Exception thrown when the specified file is invalid (missing, directory, unreadable).
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String message) {
        super(message);
    }
}
