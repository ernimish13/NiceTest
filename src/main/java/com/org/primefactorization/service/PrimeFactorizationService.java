package com.org.primefactorization.service;

import java.util.List;

/**
 * Handles the processing of a file to compute prime factorizations.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
public interface PrimeFactorizationService {

    /**
     * Reads integers from a file, computes their prime factors, and logs the results.
     *
     * @param filePath Path to the input file.
     */
    void processFile(String filePath);

    /**
     * Computes the prime factors of a given number.
     *
     * @param number The input integer.
     * @return A list of prime factors.
     */
    List<Integer> primeFactors(int number);

    void processNumber(String numberStr);
}
