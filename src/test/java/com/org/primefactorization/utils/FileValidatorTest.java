package com.org.primefactorization.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
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

    @BeforeEach
    void setUp() {
        fileValidator = new FileValidator();
    }

    /**
     * Tests if a valid file is correctly recognized.
     */
    @Test
    void testIsValidFile(@TempDir Path tempDir) throws Exception {
        File validFile = tempDir.resolve("test.txt").toFile();
        assertTrue(validFile.createNewFile()); // Create the file
        assertTrue(fileValidator.isValidFile(validFile.getAbsolutePath()));
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
    void testIsValidDirectory(@TempDir Path tempDir) {
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
