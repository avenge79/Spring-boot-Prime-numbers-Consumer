package com.rado.consumer.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NumberMessage {

    @Schema(description = "List of numbers sent via WebSocket", example = "[7, 11, 13, 14, 18]")
    private List<Integer> numbers;
}