package com.org.primefactorization.service.impl;

import com.org.primefactorization.exception.FileProcessingException;
import com.org.primefactorization.service.PrimeFactorizationService;
import com.org.primefactorization.utils.FileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the processing of a file to compute prime factorizations.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
public class PrimeFactorizationServiceImpl implements PrimeFactorizationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrimeFactorizationServiceImpl.class);
    private final FileValidator fileValidator;

    public PrimeFactorizationServiceImpl(FileValidator fileValidator) {
        this.fileValidator = fileValidator;
    }

    /**
     * Reads integers from a file, computes their prime factors, and logs the results.
     *
     * @param filePath Path to the input file.
     */
    @Override
    public void processFile(String filePath) {
        if (!fileValidator.isValidFile(filePath)) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //int blankLineCounts = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if(!line.isEmpty()) {
                    processNumber(line.trim());
                } /*else {
                    blankLineCounts++;
                }*/
                //"Currently, there is no break condition mentioned in requirements to stop processing the file after encountering X consecutive blank lines.
                // We can add a check like the following to handle this scenario:"
/*                if(blankLineCounts>X) {
                    break;
                }*/
            }
        } catch (IOException e) {
            throw new FileProcessingException("Error reading file: " + filePath, e);
        }
    }

    /**
     * Processes a single number, computing and logging its prime factors.
     *
     * @param numberStr The number as a string.
     */
    @Override
    public void processNumber(String numberStr) {
        try {
            int number = Integer.parseInt(numberStr);
            if (number == 0) {
                LOGGER.debug("Skipping 0 as it has infinitely many factors.");
                return;
            }
            else if(number == 1) {
                LOGGER.debug("Number 1 does not have any prime factors");
                return;
            }
            else if (number < 1) {
                LOGGER.debug("Skipping non-positive integer: {}", number);
                return;
            }
            List<Integer> factors = primeFactors(number);
            String output = String.join(",", factors.stream().map(String::valueOf).toArray(String[]::new));
            System.out.println(output);
            LOGGER.debug("Prime factor for number {}: {}", number, output);
        } catch (NumberFormatException e) {
            LOGGER.debug("Skipping invalid integer: {}", numberStr);
        }
    }

    /**
     * Computes the prime factors of a given number.
     *
     * @param number The input integer.
     * @return A list of prime factors.
     */
    @Override
    public List<Integer> primeFactors(int number) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i * i <= number; i++) {
            while (number % i == 0) {
                factors.add(i);
                number /= i;
            }
        }
        if (number > 1) {
            factors.add(number);
        }
        return factors;
    }
}
