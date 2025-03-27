package com.quiz.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomObjectMapper {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static <D> D mapper(Object source, Class<D> destination) {
        return mapper.convertValue(source, destination);
    }
}
