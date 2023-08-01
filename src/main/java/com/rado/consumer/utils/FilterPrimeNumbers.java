package com.rado.consumer.utils;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FilterPrimeNumbers {

    private FilterPrimeNumbers() {
    }

    public static List<Integer> filterNumbers(List<Integer> numbers) {
        if (numbers != null && !numbers.isEmpty())
            return numbers.stream()
                    .filter(FilterPrimeNumbers::isPrime).collect(toList());

        return Collections.emptyList();
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
