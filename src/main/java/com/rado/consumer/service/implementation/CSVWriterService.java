package com.rado.consumer.service.implementation;

import com.rado.consumer.configuration.CSVConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Slf4j
public class CSVWriterService implements com.rado.consumer.service.CSVWriterService {
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
            writeCsvFile(randomNumbers, csvConfiguration.getFolder(), csvConfiguration.getName());
        } catch (Exception e) {
            log.error(errorWriting, e.getMessage());
        }
    }

    public void writeCsvFile(List<Integer> randomNumbers, String folderPath, String fileName) throws IOException {
        Path filePath = Path.of(folderPath);
        String fullFilePath = Paths.get(String.valueOf(filePath), fileName).toString();

        // Create folder if it doesn't exist
        Files.createDirectories(filePath);
        boolean hasData = false;
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
