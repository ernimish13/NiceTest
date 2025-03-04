package com.org.primefactorization;

import com.org.primefactorization.processor.PrimeFactorizationProcessor;
import com.org.primefactorization.service.PrimeFactorizationService;
import com.org.primefactorization.service.impl.PrimeFactorizationServiceImpl;
import com.org.primefactorization.utils.FileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main entry point of the prime factorization application.
 * This class delegates processing to PrimeFactorizationProcessor.
 *
 * @author Nimish Kasaudhan
 * @version 1.0
 * @since 2025-03-03
 */
public class MainApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        MainApplication mainApplication = new MainApplication();
        int exitCode = mainApplication.process(args);
        System.exit(exitCode);
    }

    public int process(String[] args) {
        if (args.length != 1) {
            LOGGER.error("Invalid number of arguments provided. Expected: 1, Received: {}.", args.length);
            LOGGER.info("Usage: java -jar PrimeFactorization.jar <file_path>");
            return 1;
        }

        String filePath = args[0];
        FileValidator fileValidator = new FileValidator();
        PrimeFactorizationService primeFactorizationService = new PrimeFactorizationServiceImpl(fileValidator);
        PrimeFactorizationProcessor processor = new PrimeFactorizationProcessor(primeFactorizationService);

        return processor.execute(filePath);
    }
}
