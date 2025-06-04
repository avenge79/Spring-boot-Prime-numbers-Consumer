package com.rado.consumer;

import com.rado.consumer.service.CSVFileService;
import com.rado.consumer.service.implementation.CSVWriterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CSVWriterServiceImplTest {

    @InjectMocks
    private CSVWriterServiceImpl csvWriterService;

    @Mock
    private CSVFileService csvFileService;

    private Path tempFolder;
    private final String testCsvFileName = "test.csv";

    @BeforeEach
    void setUp() throws IOException {
        tempFolder = Files.createTempDirectory(testCsvFileName);
    }

    @Test
    void writeRandomNumbersToCSV_createsFileAndWritesData() throws IOException {
        List<Integer> numbers = List.of(10, 20, 30);
        Path csvFilePath = tempFolder.resolve(testCsvFileName);
        Mockito.doAnswer(invocation -> {
            Files.writeString(csvFilePath, String.join(",", numbers.stream().map(String::valueOf).toList()));
            return null;
        }).when(csvFileService).writeCsvFile(numbers);

        csvWriterService.writeRandomNumbersToCSV(numbers);

        assertTrue(Files.exists(csvFilePath));
        String content = Files.readString(csvFilePath);
        assertEquals("10,20,30", content);
    }

    @Test
    void writeRandomNumbersToCSV_handlesEmptyList() throws IOException {
        csvWriterService.writeRandomNumbersToCSV(List.of());

        Path csvFilePath = tempFolder.resolve(testCsvFileName);
        assertFalse(Files.exists(csvFilePath)); // Файлът не трябва да се създава
    }
}
