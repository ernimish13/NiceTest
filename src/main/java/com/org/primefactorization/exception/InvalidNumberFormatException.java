package com.org.primefactorization.exception;

/**
 * Exception thrown when an invalid number format is encountered in the input file.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
public class InvalidNumberFormatException extends RuntimeException {
    public InvalidNumberFormatException(String message) {
        super(message);
    }
}