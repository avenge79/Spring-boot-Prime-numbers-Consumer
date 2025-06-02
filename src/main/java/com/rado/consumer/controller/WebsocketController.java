package com.rado.consumer.controller;

import com.rado.consumer.model.NumberMessage;
import com.rado.consumer.service.CSVWriterService;
import com.rado.consumer.service.FilterPrimeNumbersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.rado.consumer.utils.MappingConstants.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "WebSocket Controller", description = "Processes WebSocket messages about numbers")
public class WebsocketController {
    private final CSVWriterService csvWriterService;
    private final FilterPrimeNumbersService filterPrimeNumbersService;

    @MessageMapping(MESSAGE_MAPPING)
    @Operation(summary = WEB_SOCKET_MESSAGE_SUMMARY,
            description = WEB_SOCKET_MESSAGE_DESCRIPTION)
    public void receiveMessage(@Payload NumberMessage message) {
        if (message == null || CollectionUtils.isEmpty(message.getNumbers())) {
            log.info("No numbers received");
            return;
        }

        log.info("Received WS message: {}", message.getNumbers());
        List<Integer> primeNumbers = filterPrimeNumbersService.filterNumbers(message.getNumbers());

        if (CollectionUtils.isEmpty(primeNumbers)) {
            log.info(NO_PRIME_NUMBERS_FOUND);
            return;
        }

        log.info("Prime numbers: {}", primeNumbers);
        csvWriterService.writeRandomNumbersToCSV(primeNumbers);
    }
}