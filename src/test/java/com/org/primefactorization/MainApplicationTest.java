package com.org.primefactorization;

import com.org.primefactorization.processor.PrimeFactorizationProcessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for MainApplication.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
@ExtendWith(MockitoExtension.class)
class MainApplicationTest {

    @Mock
    private PrimeFactorizationProcessor processor;

    @InjectMocks
    private MainApplication mainApplication;

    @BeforeEach
    void setUp() {
        mainApplication = new MainApplication();
    }

    @Test
    void testProcess_MissingArguments_ReturnsError() {
        String[] args = {};
        int exitCode = mainApplication.process(args);
        assertEquals(1, exitCode, "Expected failure due to missing arguments (exit code 1)");
    }
}
