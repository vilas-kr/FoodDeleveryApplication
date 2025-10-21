package com.vilas.hungerHub.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {

    private int status;
    private String message;
    private LocalDateTime time;

}
