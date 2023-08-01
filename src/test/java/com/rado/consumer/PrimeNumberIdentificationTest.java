package com.rado.consumer;

import org.junit.jupiter.api.Test;

import static com.rado.consumer.utils.FilterPrimeNumbers.isPrime;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimeNumberIdentificationTest {

    @Test
    void testIsPrimeWithPrimeNumber() {
        // Test a positive prime number
        int primeNumber = 17;
        boolean result = isPrime(primeNumber);
        assertTrue(result, primeNumber + " should be prime.");
    }

    @Test
    void testIsPrimeWithNonPrimeNumber() {
        // Test a non-prime number
        int nonPrimeNumber = 15;
        boolean result = isPrime(nonPrimeNumber);
        assertFalse(result, nonPrimeNumber + " should not be prime.");
    }

    @Test
    void testIsPrimeWithNegativeNumber() {
        // Test a negative number
        int negativeNumber = -7;
        boolean result = isPrime(negativeNumber);
        assertFalse(result, negativeNumber + " should not be prime.");
    }

    @Test
    void testIsPrimeWithZeroAndOne() {
        // Test 0 and 1 (not prime)
        assertFalse(isPrime(0), "0 should not be prime.");
        assertFalse(isPrime(1), "1 should not be prime.");
    }
}
