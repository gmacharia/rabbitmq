package com.rabbitmq.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ApiError {
    private int status;
    private String message;
    private String path;
    private long timestamp;
}
