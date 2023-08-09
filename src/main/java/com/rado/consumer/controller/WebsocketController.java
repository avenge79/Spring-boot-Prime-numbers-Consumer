package com.rado.consumer.controller;

import com.rado.consumer.service.CSVWriterService;
import com.rado.consumer.utils.FilterPrimeNumbers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.rado.consumer.controller.MappingConstants.MESSAGE_MAPPING;

@Slf4j
@Controller
public class WebsocketController {
    private final CSVWriterService csvWriterService;

    public WebsocketController(CSVWriterService csvWriterService) {
        this.csvWriterService = csvWriterService;
    }

    @MessageMapping(MESSAGE_MAPPING)
    public void receiveMessage(@Payload(required = false) List<Integer> numbers) {
        log.info("Received WS message: {}", numbers);
        List<Integer> primeNumbers = FilterPrimeNumbers.filterNumbers(numbers);

        if (primeNumbers != null && primeNumbers.isEmpty()) {
            log.info("No prime numbers in the list");
        }

        log.info("Received numbers: {}", numbers);
        log.info("Prime numbers: {}", primeNumbers);

        if (numbers != null && !numbers.isEmpty())
            csvWriterService.writeRandomNumbersToCSV(primeNumbers);
    }
}