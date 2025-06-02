package com.rado.consumer.utils;

public class MappingConstants {
    public static final String MESSAGE_MAPPING = "/message";

    public static final String REQUEST_MAPPING = "/api";
    public static final String CONTROLLER_POST_MAPPING = "/random-numbers";


    public static final String NO_PRIME_NUMBERS_FOUND = "No prime numbers found";


    public static final String CONSUMER_CONTROLLER_NAME = "Prime Numbers Controller";
    public static final String CONSUMER_CONTROLLER_DESCRIPTION = "Operations related to filtering prime numbers";

    public static final String RANDOM_NUMBERS_MAPPING_SUMMARY = "Filter prime numbers from list";
    public static final String RANDOM_NUMBERS_MAPPING_DESCRIPTION = "Receives a list of numbers and returns only the prime ones.";

    public static final String WEB_SOCKET_MESSAGE_SUMMARY = "Receive WebSocket message";
    public static final String WEB_SOCKET_MESSAGE_DESCRIPTION = "Receives a list of numbers via WebSocket and filters prime numbers.";
}
