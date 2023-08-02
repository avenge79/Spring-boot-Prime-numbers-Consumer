package com.rado.consumer.controller;

import com.rado.consumer.service.CSVWriterService;
import com.rado.consumer.utils.FilterPrimeNumbers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rado.consumer.controller.MappingConstants.CONTROLLER_POST_MAPPING;
import static com.rado.consumer.controller.MappingConstants.REQUEST_MAPPING;

@RestController
@Slf4j
@RequestMapping(REQUEST_MAPPING)
public class ConsumerController {
    private final CSVWriterService csvWriterService;

    @Autowired
    public ConsumerController(CSVWriterService csvWriterService) {
        this.csvWriterService = csvWriterService;
    }


    @PostMapping(CONTROLLER_POST_MAPPING)
    public ResponseEntity<List<Integer>> receiveRandomNumbers(@RequestBody List<Integer> numbers) {
        List<Integer> primeNumbers = FilterPrimeNumbers.filterNumbers(numbers);

        if (primeNumbers == null || primeNumbers.isEmpty()) {
            log.info("No prime numbers in the list");
        }

        log.info("Received numbers: {}", numbers);
        log.info("Prime numbers: {}", primeNumbers);
        csvWriterService.writeRandomNumbersToCSV(primeNumbers);

        return ResponseEntity.ok(primeNumbers);
    }
}