package com.rado.consumer.controller;

import com.rado.consumer.model.NumberMessage;
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
    public ResponseEntity<NumberMessage> receiveRandomNumbers(@RequestBody NumberMessage message) {
        List<Integer> primeNumbers = filterPrimeNumbersService.filterNumbers(message.getNumbers());

        NumberMessage responseMessage = new NumberMessage();
        responseMessage.setNumbers(primeNumbers);

        if (CollectionUtils.isEmpty(primeNumbers)) {
            log.info(NO_PRIME_NUMBERS_FOUND);
        } else {
            log.info("Received numbers: {}", message.getNumbers());
            log.info("Prime numbers: {}", primeNumbers);
            csvWriterService.writeRandomNumbersToCSV(primeNumbers);
        }
        return ResponseEntity.ok(responseMessage);
    }
}