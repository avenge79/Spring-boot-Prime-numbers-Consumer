package com.rado.consumer;

import com.rado.consumer.controller.ConsumerController;
import com.rado.consumer.model.NumberMessage;
import com.rado.consumer.service.CSVWriterService;
import com.rado.consumer.service.FilterPrimeNumbersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.rado.consumer.utils.MappingConstants.CONTROLLER_POST_MAPPING;
import static com.rado.consumer.utils.MappingConstants.REQUEST_MAPPING;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ConsumerControllerTest {

    @InjectMocks
    private ConsumerController consumerController;

    @Mock
    private CSVWriterService csvWriterService;

    @Mock
    private FilterPrimeNumbersService filterPrimeNumbersService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void receiveRandomNumbers_returnsPrimeNumbers() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(consumerController).build();

        NumberMessage requestMessage = new NumberMessage();
        requestMessage.setNumbers(List.of(7, 11, 13, 14, 18));

        NumberMessage responseMessage = new NumberMessage();
        responseMessage.setNumbers(List.of(7, 11, 13)); // Само простите числа

        // Мокираме поведението на `filterPrimeNumbersService`
        when(filterPrimeNumbersService.filterNumbers(requestMessage.getNumbers())).thenReturn(responseMessage.getNumbers());

        // Изпращаме POST заявка
        mockMvc.perform(post(REQUEST_MAPPING + CONTROLLER_POST_MAPPING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestMessage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numbers").isArray())
                .andExpect(jsonPath("$.numbers[0]").value(7))
                .andExpect(jsonPath("$.numbers[1]").value(11))
                .andExpect(jsonPath("$.numbers[2]").value(13));

        // Уверяваме се, че методът `writeRandomNumbersToCSV` е извикан
        verify(csvWriterService, times(1)).writeRandomNumbersToCSV(responseMessage.getNumbers());
    }

    @Test
    void receiveRandomNumbers_returnsEmptyListIfNoPrimes() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(consumerController).build();

        NumberMessage requestMessage = new NumberMessage();
        requestMessage.setNumbers(List.of(4, 6, 8, 10));

        NumberMessage responseMessage = new NumberMessage();
        responseMessage.setNumbers(List.of()); // Няма прости числа

        when(filterPrimeNumbersService.filterNumbers(requestMessage.getNumbers())).thenReturn(responseMessage.getNumbers());

        mockMvc.perform(post(REQUEST_MAPPING + CONTROLLER_POST_MAPPING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestMessage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numbers").isEmpty());

        verify(csvWriterService, never()).writeRandomNumbersToCSV(any());
    }
}
