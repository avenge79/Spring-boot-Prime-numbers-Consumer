package com.rado.consumer.service.implementation;

import com.rado.consumer.service.CSVFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@Slf4j
public class CSVFileServiceImpl implements CSVFileService {
    public void writeCsvFile(List<Integer> randomNumbers, String folderPath, String fileName) throws IOException, IOException {
        Path filePath = Paths.get(folderPath);
        Files.createDirectories(filePath);

        Path fullFilePath = filePath.resolve(fileName);
        boolean hasData = Files.exists(fullFilePath) && Files.size(fullFilePath) > 0;

        try (BufferedWriter writer = Files.newBufferedWriter(fullFilePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            String appendString = String.join(",", randomNumbers.stream().map(String::valueOf).toList());
            if (hasData) {
                writer.write(",");
            }
            writer.write(appendString);
            writer.flush();

            log.info("Random numbers written to CSV file successfully: {}", fullFilePath);
        } catch (IOException e) {
            log.error("Error writing to CSV file: {}", e.getMessage());
        }
    }
}
