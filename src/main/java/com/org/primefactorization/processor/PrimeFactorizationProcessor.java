package com.org.primefactorization.processor;

import com.org.primefactorization.exception.FileProcessingException;
import com.org.primefactorization.exception.InvalidFileException;
import com.org.primefactorization.exception.InvalidNumberFormatException;
import com.org.primefactorization.exception.NonPositiveNumberException;
import com.org.primefactorization.service.PrimeFactorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the processing logic for the prime factorization application.
 * Separates business logic from the MainApplication class for better testability.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
public class PrimeFactorizationProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrimeFactorizationProcessor.class);
    private final PrimeFactorizationService primeFactorizationService;

    public PrimeFactorizationProcessor(PrimeFactorizationService primeFactorizationService) {
        this.primeFactorizationService = primeFactorizationService;
    }

    /**
     * Executes the prime factorization process and handles exceptions.
     *
     * @param filePath Path to the input file.
     * @return Exit code (0 for success, 1 for failure)
     */
    public int execute(String filePath) {
        try {
            primeFactorizationService.processFile(filePath);
            return 0;
        } catch (InvalidFileException e) {
            LOGGER.warn("Invalid file '{}': {}", filePath, e.getMessage());
        } catch (FileProcessingException e) {
            LOGGER.error("Failed to process file '{}': {}", filePath, e.getMessage());
        } catch (InvalidNumberFormatException e) {
            LOGGER.warn("Invalid number format in file '{}': {}", filePath, e.getMessage());
        } catch (NonPositiveNumberException e) {
            LOGGER.info("Skipping non-positive number in file '{}': {}", filePath, e.getMessage());
        }
        return 1;
    }
}
