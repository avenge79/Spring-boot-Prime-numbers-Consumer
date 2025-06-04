package com.rado.consumer.service.implementation;

import com.rado.consumer.configuration.CSVConfiguration;
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
            csvFileService.writeCsvFile(randomNumbers);
        } catch (Exception e) {
            log.error("Error writing random numbers to CSV file: {}", e.getMessage());
        }
    }
}
