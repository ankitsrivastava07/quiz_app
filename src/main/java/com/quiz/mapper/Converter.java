package com.quiz.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convert(Object source, Class<T> destination) {
        return objectMapper.convertValue(source, destination);
    }
}
