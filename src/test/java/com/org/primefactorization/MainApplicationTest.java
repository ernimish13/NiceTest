package com.org.primefactorization;

import com.org.primefactorization.processor.PrimeFactorizationProcessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

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

    @TempDir
    private Path tempDir;

    @BeforeEach
    void setUp() {
        mainApplication = new MainApplication();
    }

    /*
        * Test that the process method returns an error when no arguments are provided.
     */
    @Test
    void testMissingArguments_ReturnsError() {
        String[] args = {};
        int exitCode = mainApplication.process(args);
        assertEquals(1, exitCode, "Expected failure due to missing arguments (exit code 1)");
    }

    /*
        * Test that the process method returns when valid arguments are provided.
     */
    @Test
    void testValidArgument() throws IOException {
        File validFile = tempDir.resolve("test.txt").toFile();
        try (FileWriter writer = new FileWriter(validFile)) {
            writer.write("test data");
        }
        String[] args = {"test.txt"};
        int exitCode = mainApplication.process(args);
        assertEquals(0, exitCode);
    }
}
