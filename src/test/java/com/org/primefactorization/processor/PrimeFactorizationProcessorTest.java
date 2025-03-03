package com.org.primefactorization.processor;

import com.org.primefactorization.exception.FileProcessingException;
import com.org.primefactorization.exception.InvalidFileException;
import com.org.primefactorization.exception.InvalidNumberFormatException;
import com.org.primefactorization.exception.NonPositiveNumberException;
import com.org.primefactorization.service.PrimeFactorizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

/**
 * Unit test for PrimeFactorizationProcessor.
 * Ensures correct handling of different exceptions and return codes.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
@ExtendWith(MockitoExtension.class)
class PrimeFactorizationProcessorTest {

    @Mock
    private PrimeFactorizationService primeFactorizationService;

    @InjectMocks
    private PrimeFactorizationProcessor processor;

    private static final String FILE_PATH = "testfile.txt";

    @BeforeEach
    void setUp() {
        processor = new PrimeFactorizationProcessor(primeFactorizationService);
    }

    /**
     * Tests successful execution of the prime factorization process.
     */
    @Test
    void testExecute_Success() {
        int exitCode = processor.execute(FILE_PATH);
        assertEquals(0, exitCode, "Expected exit code 0 for successful execution.");
    }

    /**
     * Tests handling of an invalid file exception.
     */
    @Test
    void testExecute_InvalidFileException() {
        doThrow(new InvalidFileException("Invalid file"))
                .when(primeFactorizationService).processFile(FILE_PATH);

        int exitCode = processor.execute(FILE_PATH);
        assertEquals(1, exitCode, "Expected exit code 1 for invalid file.");
    }

    /**
     * Tests handling of a file processing exception.
     */
    @Test
    void testExecute_FileProcessingException() {
        doThrow(new FileProcessingException("Error processing file", new RuntimeException()))
                .when(primeFactorizationService).processFile(FILE_PATH);

        int exitCode = processor.execute(FILE_PATH);
        assertEquals(1, exitCode, "Expected exit code 1 for file processing error.");
    }

    /**
     * Tests handling of an invalid number format exception.
     */
    @Test
    void testExecute_InvalidNumberFormatException() {
        doThrow(new InvalidNumberFormatException("Invalid number format"))
                .when(primeFactorizationService).processFile(FILE_PATH);

        int exitCode = processor.execute(FILE_PATH);
        assertEquals(1, exitCode, "Expected exit code 1 for invalid number format.");
    }

    /**
     * Tests handling of a non-positive number exception.
     */
    @Test
    void testExecute_NonPositiveNumberException() {
        doThrow(new NonPositiveNumberException("Non-positive number"))
                .when(primeFactorizationService).processFile(FILE_PATH);

        int exitCode = processor.execute(FILE_PATH);
        assertEquals(1, exitCode, "Expected exit code 1 for non-positive number.");
    }
}
