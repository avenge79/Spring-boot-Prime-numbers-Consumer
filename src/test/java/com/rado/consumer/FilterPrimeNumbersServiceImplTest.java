package com.rado.consumer;

import com.rado.consumer.service.implementation.FilterPrimeNumbersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FilterPrimeNumbersServiceImplTest {

    @InjectMocks
    private FilterPrimeNumbersServiceImpl filterPrimeNumbersService;

    @Test
    void filterNumbers_returnsOnlyPrimeNumbers() {
        List<Integer> input = List.of(3, 4, 5, 8, 11, 13, 16);
        List<Integer> expectedOutput = List.of(3, 5, 11, 13);

        List<Integer> result = filterPrimeNumbersService.filterNumbers(input);

        assertEquals(expectedOutput, result);
    }

    @Test
    void filterNumbers_returnsEmptyListIfNoPrimes() {
        List<Integer> input = List.of(4, 6, 8, 10);

        List<Integer> result = filterPrimeNumbersService.filterNumbers(input);

        assertTrue(result.isEmpty());
    }

    @Test
    void filterNumbers_returnsEmptyListForNullInput() {
        List<Integer> result = filterPrimeNumbersService.filterNumbers(null);

        assertTrue(result.isEmpty());
    }

    @Test
    void isPrime_correctlyIdentifiesPrimeNumbers() {
        assertTrue(filterPrimeNumbersService.isPrime(7));
        assertTrue(filterPrimeNumbersService.isPrime(11));
        assertFalse(filterPrimeNumbersService.isPrime(9));
        assertFalse(filterPrimeNumbersService.isPrime(1));
        assertFalse(filterPrimeNumbersService.isPrime(0));
    }
}