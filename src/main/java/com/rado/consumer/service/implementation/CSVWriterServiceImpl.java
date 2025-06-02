package com.rado.consumer.service.implementation;

import com.rado.consumer.service.CSVFileService;
import com.rado.consumer.service.CSVWriterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CSVWriterServiceImpl implements CSVWriterService {
    private final CSVFileService csvFileService;

    @Override
    public void writeRandomNumbersToCSV(List<Integer> randomNumbers) {
        if (randomNumbers == null || randomNumbers.isEmpty()) {
            return;
        }

        try {
            csvFileService.writeCsvFile(randomNumbers, "some/folder", "data.csv");
        } catch (Exception e) {
            log.error("Error writing random numbers to CSV file: {}", e.getMessage());
        }
    }

//    public void writeCsvFile(List<Integer> randomNumbers, String folderPath, String fileName) throws IOException {
//        Path filePath = Path.of(folderPath);
//        String fullFilePath = Paths.get(String.valueOf(filePath), fileName).toString();
//
//        // Create folder if it doesn't exist
//        Files.createDirectories(filePath);
//        boolean hasData = false;
//        try (FileWriter writer = new FileWriter(fullFilePath, true)) {
//            File file = new File(fullFilePath);
//            if (file.exists() && file.isFile() && file.length() > 0) {
//                hasData = true;
//            }
//
//            String appendString = StringUtils.collectionToCommaDelimitedString(randomNumbers);
//            if (hasData) {
//                writer.append(",");
//            }
//            writer.append(appendString);
//            writer.flush();
//
//            log.info("Random numbers written to CSV file successfully to: {}", fullFilePath);
//        } catch (Exception e) {
//            log.error(ERROR_WRITING, e.getMessage());
//        }
//    }
}
