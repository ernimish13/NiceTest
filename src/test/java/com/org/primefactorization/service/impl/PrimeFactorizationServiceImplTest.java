package com.org.primefactorization.service.impl;

import com.org.primefactorization.exception.FileProcessingException;
import com.org.primefactorization.utils.FileValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyInt;

/**
 * Unit tests for {@link PrimeFactorizationServiceImpl}.
 * Ensures correct behavior for processing files and computing prime factors.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
class PrimeFactorizationServiceImplTest {

    private FileValidator fileValidator;
    private PrimeFactorizationServiceImpl primeFactorizationService;

    @BeforeEach
    void setUp() {
        fileValidator = mock(FileValidator.class);
        primeFactorizationService = new PrimeFactorizationServiceImpl(fileValidator);
    }

    /**
     * Tests prime factorization for valid numbers.
     */
    @Test
    void testPrimeFactors() {
        assertEquals(Arrays.asList(2, 2, 3), primeFactorizationService.primeFactors(12));
        assertEquals(List.of(5), primeFactorizationService.primeFactors(5));
        assertEquals(Arrays.asList(2, 2, 2, 2), primeFactorizationService.primeFactors(16));
        assertEquals(Arrays.asList(7, 7, 7), primeFactorizationService.primeFactors(343));
    }

    /**
     * Tests prime factorization for numbers that are already prime.
     */
    @Test
    void testPrimeFactors_PrimeNumbers() {
        assertEquals(List.of(13), primeFactorizationService.primeFactors(13));
        assertEquals(List.of(97), primeFactorizationService.primeFactors(97));
    }

    /**
     * Tests prime factorization for 1 (should return an empty list).
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 1, -5})
    void testPrimeFactors_InvalidNumbers(int input) {
        assertTrue(primeFactorizationService.primeFactors(input).isEmpty());
    }

    /**
     * Tests handling of a valid file by reading numbers and processing them.
     *
     * @param tempDir Temporary directory for testing.
     */
    @Test
    void testProcessFile_ValidFile(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("test.txt").toFile();
        String lineSeparator = System.lineSeparator();
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.append("35").append(lineSeparator).append("40").append(lineSeparator)
                    .append("a").append(lineSeparator).append(lineSeparator).append("@100").append(lineSeparator).append("2000")
                    .append(lineSeparator).append("12.3");
        }

        when(fileValidator.isValidFile(testFile.getAbsolutePath())).thenReturn(true);

        assertDoesNotThrow(() -> primeFactorizationService.processFile(testFile.getAbsolutePath()));
        verify(fileValidator, times(1)).isValidFile(testFile.getAbsolutePath());
    }

    /**
     * Tests processing of a file when an IOException occurs.
     */
    @Test
    void testProcessFile_FileProcessingException() {
        when(fileValidator.isValidFile("invalid/path")).thenReturn(true);

        assertThrows(FileProcessingException.class, () -> primeFactorizationService.processFile("invalid/path"));
    }

    /**
     * Tests handling when the file is invalid.
     */
    @Test
    void testProcessFile_InvalidFile() {
        when(fileValidator.isValidFile("invalid.txt")).thenReturn(false);

        assertDoesNotThrow(() -> primeFactorizationService.processFile("invalid.txt"));
        verify(fileValidator, times(1)).isValidFile("invalid.txt");
    }

    /**
     * Test case: processNumber() should correctly process a valid number.
     * It should call primeFactors() to compute factors.
     */
    @Test
    void testProcessNumber_ValidNumber() {
        // Spy on the service to mock primeFactors() behavior
        PrimeFactorizationServiceImpl spyService = spy(primeFactorizationService);
        doReturn(Arrays.asList(2, 3)).when(spyService).primeFactors(6);

        spyService.processNumber("6");

        verify(spyService).primeFactors(6);
    }

    /**
     * Parameterized test case: processNumber() should skip processing for invalid inputs.
     * It should not call primeFactors() for empty String, string, "0", "1", or any negative number.
     */
    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "-5", "-10", "abc", ""})
    void testProcessNumber_InvalidInputs(String input) {
        PrimeFactorizationServiceImpl spyService = spy(primeFactorizationService);
        spyService.processNumber(input);

        verify(spyService, never()).primeFactors(anyInt());
    }
}
