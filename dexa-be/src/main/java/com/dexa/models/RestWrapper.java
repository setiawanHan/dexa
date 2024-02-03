package com.dexa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestWrapper<T> {
    private LocalDateTime respDate = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
    private int responseCode;
    private Object responseMessage;
    private T data;

    public ResponseEntity<RestWrapper<T>> responseWrapper(int responseCode,
                                                          HttpStatus httpStatus,
                                                          String responseMessage,
                                                          T data) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
        return new ResponseEntity<>(this, httpStatus);
    }
}
