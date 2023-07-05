package com.energy.api.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class GenericErrorReponse<T> {
    private HttpStatus status;
    @JsonProperty("dateOfOccurrence")
    private LocalDateTime localDateTime;
    private String message;
    @JsonProperty("description")
    private T data;

    public GenericErrorReponse(LocalDateTime localDateTime, HttpStatus status, String message, T data) {
        this.localDateTime = localDateTime;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
