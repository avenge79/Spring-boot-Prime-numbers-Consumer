package com.rado.consumer.service;

import com.rado.consumer.configuration.CSVConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class CSVWriterService {
    private final CSVConfiguration csvConfiguration;
    String errorWriting = "Error writing random numbers to CSV file: {}";
    @Autowired
    public CSVWriterService(CSVConfiguration csvConfiguration) {
        this.csvConfiguration = csvConfiguration;
    }

    public void writeRandomNumbersToCSV(List<Integer> randomNumbers) {
        if (randomNumbers == null || randomNumbers.isEmpty()) {
            return;
        }

        try {
            Path fileName = Path.of(csvConfiguration.getFolder());
            String fullFilePath = Paths.get(String.valueOf(fileName), csvConfiguration.getName()).toString();

            // Create folder if it doesn't exist
            Files.createDirectories(fileName);
            boolean hasData = false;

            writeCsvFile(randomNumbers, fullFilePath, hasData);
        } catch (Exception e) {
            log.error(errorWriting, e.getMessage());
        }
    }

    private void writeCsvFile(List<Integer> randomNumbers, String fullFilePath, boolean hasData) {
        try (FileWriter writer = new FileWriter(fullFilePath, true)) {
            File file = new File(fullFilePath);
            if (file.exists() && file.isFile() && file.length() > 0) {
                hasData = true;
            }

            String appendString = StringUtils.collectionToCommaDelimitedString(randomNumbers);
            if (hasData) {
                writer.append(",");
            }
            writer.append(appendString);
            writer.flush();

            log.info("Random numbers written to CSV file successfully.");
        } catch (Exception e) {
            log.error(errorWriting, e.getMessage());
        }
    }
}