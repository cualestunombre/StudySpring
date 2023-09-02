package com.example.demo.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class SimpleJsonResponse {
    private final Integer code;
    private final String message;
}
