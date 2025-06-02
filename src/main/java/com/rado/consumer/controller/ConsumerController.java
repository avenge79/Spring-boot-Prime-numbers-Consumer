package com.rado.consumer.controller;

import com.rado.consumer.service.CSVWriterService;
import com.rado.consumer.service.FilterPrimeNumbersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static com.rado.consumer.utils.MappingConstants.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(REQUEST_MAPPING)
@Tag(name = CONSUMER_CONTROLLER_NAME,
        description = CONSUMER_CONTROLLER_DESCRIPTION)
public class ConsumerController {
    private final CSVWriterService csvWriterService;
    private final FilterPrimeNumbersService filterPrimeNumbersService;

    @PostMapping(CONTROLLER_POST_MAPPING)
    @Operation(summary = RANDOM_NUMBERS_MAPPING_SUMMARY,
            description = RANDOM_NUMBERS_MAPPING_DESCRIPTION)
    public ResponseEntity<List<Integer>> receiveRandomNumbers(@RequestBody List<Integer> numbers) {
        List<Integer> primeNumbers = filterPrimeNumbersService.filterNumbers(numbers);

        if (CollectionUtils.isEmpty(primeNumbers)) {
            log.info(NO_PRIME_NUMBERS_FOUND);
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            log.info("Received numbers: {}", numbers);
            log.info("Prime numbers: {}", primeNumbers);
            csvWriterService.writeRandomNumbersToCSV(primeNumbers);

            return ResponseEntity.ok(primeNumbers);
        }
    }
}