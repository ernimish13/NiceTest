package com.org.primefactorization.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link FileValidator}.
 * Tests valid files, non-existent files, directories, and edge cases.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
class FileValidatorTest {

    private FileValidator fileValidator;

    @TempDir
    private  Path tempDir;

    @BeforeEach
    void setUp() {
        fileValidator = new FileValidator();
    }

    /**
     * Verifies that {@code isValidFile} correctly identifies valid text and CSV files.
     *
     * @param input the name of the test file (e.g., "test.txt", "test.csv")
     * @throws IOException if an I/O error occurs
     */
    @ParameterizedTest
    @ValueSource(strings = {"test.txt", "test.csv"})
    void testIsValidFile(String input) throws IOException {
        File validFile = tempDir.resolve(input).toFile();
        try (FileWriter writer = new FileWriter(validFile)) {
            writer.write("test data");
        }
        assertTrue(fileValidator.isValidFile(validFile.getAbsolutePath()));
    }

    /**
     * Tests if file is empty
     */
    @Test
    void testEmptyFile() throws Exception {
        File validFile = tempDir.resolve("test.txt").toFile();
        assertTrue(validFile.createNewFile()); // Create the file
        assertFalse(fileValidator.isValidFile(validFile.getAbsolutePath()));
    }


    /**
     * Tests if a jpeg file is invalid file.
     */
    @Test
    void testJPEGFile() throws Exception {
        File validFile = tempDir.resolve("test.jpeg").toFile();
        assertTrue(validFile.createNewFile()); // Create the file
        assertFalse(fileValidator.isValidFile(validFile.getAbsolutePath()));
    }

    /**
     * Tests if a non-existent file is correctly detected.
     */
    @Test
    void testNonExistentFile() {
        String invalidPath = "non_existent_file.txt";
        assertFalse(fileValidator.isValidFile(invalidPath));
    }

    /**
     * Tests if a directory is correctly identified as invalid.
     */
    @Test
    void testIsValidDirectory() {
        assertFalse(fileValidator.isValidFile(tempDir.toString()));
    }

    /**
     * Tests handling of an empty file path.
     */
    @Test
    void testIsEmptyPath() {
        assertFalse(fileValidator.isValidFile(""));
    }

    /**
     * Tests handling of a null file path.
     */
    @Test
    void testIsNullPath() {
        assertThrows(NullPointerException.class, () -> fileValidator.isValidFile(null));
    }
}
