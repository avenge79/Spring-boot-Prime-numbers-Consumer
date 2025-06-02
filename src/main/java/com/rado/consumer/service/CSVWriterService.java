package com.rado.consumer.service;

import java.io.IOException;
import java.util.List;

public interface CSVWriterService {
    void writeRandomNumbersToCSV(List<Integer> randomNumbers);
}