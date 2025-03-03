package com.org.primefactorization.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for validating file paths.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
public class FileValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileValidator.class);

    /**
     * Checks if the given file path points to an existing and valid file.
     * Logs an error if the file does not exist or is not a regular file.
     *
     * @param filePath the path of the file to validate.
     * @return {@code true} if the file exists and is valid, otherwise {@code false}.
     */
    public boolean isValidFile(String filePath) {
        Path path = Paths.get(filePath);
        File file = path.toFile();
        boolean isValidFile = true;
        if (!file.exists()) {
            LOGGER.error("Error: File '{}' does not exist.", filePath);
            isValidFile =  false;
        }
        else if (!file.isFile()) {
            LOGGER.error("Error: '{}' is not a valid file.", filePath);
            isValidFile =  false;
        }

        return isValidFile;
    }
}
