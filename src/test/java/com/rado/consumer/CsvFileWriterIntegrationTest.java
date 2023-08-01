package com.rado.consumer;

import com.rado.consumer.configuration.CSVConfiguration;
import com.rado.consumer.service.CSVWriterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CsvFileWriterIntegrationTest {

    @Autowired
    private CSVWriterService csvFileWriter;

    @Autowired
    private CSVConfiguration configureProperties;

    @Test
    void testSaveNumbersToCsvFile() throws IOException {
        // Test with custom list of generated random numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            numbers.add(new Random().nextInt(100) + 1);
        }

        String folderPath = configureProperties.getFolder();
        String fileName = "PrimeNumbersTest.csv";

        Path filePath = Paths.get(folderPath, fileName);

        // Delete file if exists
        Files.deleteIfExists(filePath);

        csvFileWriter.writeCsvFile(numbers, folderPath, fileName);

        // Test if file is created
        assertTrue(Files.exists(filePath), "CSV file should be created.");

        // Test if file has any data written
        assertTrue(Files.size(filePath) > 0, "File should not be empty.");

        List<String> lines = Files.readAllLines(filePath);

        // Convert line read from file to Integer list
        List<Integer> listOfInteger = Arrays.stream(lines.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Cleanup
        Files.deleteIfExists(filePath);

        // Test initial list against the list retrieved from the CSV file
        assertEquals(numbers.size(), listOfInteger.size(), "CSV file should have the same number of lines as prime numbers.");
        assertEquals(numbers, listOfInteger, "The array from CSV file should have the same content as prime numbers array.");
    }
}
