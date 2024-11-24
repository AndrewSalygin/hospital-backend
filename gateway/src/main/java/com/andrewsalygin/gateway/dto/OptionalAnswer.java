package com.andrewsalygin.gateway.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.andrewsalygin.gateway.dto.response.ApiErrorResponse;
import com.andrewsalygin.gateway.util.OptionalAnswerDeserializer;
import java.util.function.Consumer;

@JsonDeserialize(using = OptionalAnswerDeserializer.class)
public record OptionalAnswer<T>(T answer, ApiErrorResponse apiErrorResponse) {

    public static <T> OptionalAnswer<T> error(ApiErrorResponse apiErrorResponse) {
        return new OptionalAnswer<>(null, apiErrorResponse);
    }

    public static <T> OptionalAnswer<T> of(T t) {
        return new OptionalAnswer<>(t, null);
    }

    public boolean isError() {
        return apiErrorResponse != null;
    }

    public OptionalAnswer<T> ifExists(Consumer<T> consumer) {
        if (answer != null) {
            consumer.accept(answer);
        }
        return this;
    }
}
