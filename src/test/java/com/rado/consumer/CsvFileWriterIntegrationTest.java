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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CsvFileWriterIntegrationTest {

    @Autowired
    private CSVWriterService csvFileWriter;

    @Autowired
    private CSVConfiguration configureProperties; // Replace this with your actual configuration bean

    @Test
    void testSaveNumbersToCsvFile() throws IOException {
        List<Integer> primeNumbers = Arrays.asList(2, 3, 5, 7, 11, 4, 25, 77, 41, 6, 75);

        String folderPath = configureProperties.getFolder();
        String fileName = "PrimeNumbersTest.csv";

        csvFileWriter.writeCsvFile(primeNumbers, folderPath, fileName);

        Path filePath = Paths.get(folderPath, fileName);
        assertTrue(Files.exists(filePath), "CSV file should be created.");

        List<String> lines = Files.readAllLines(filePath);

        List<Integer> listOfInteger = Arrays.stream(lines.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Files.deleteIfExists(filePath);
        assertEquals(primeNumbers.size(), listOfInteger.size(), "CSV file should have the same number of lines as prime numbers.");
        assertEquals(primeNumbers, listOfInteger, "The array from CSV file should have the same content as prime numbers array.");
    }
}
