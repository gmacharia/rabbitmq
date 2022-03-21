package com.rabbitmq.demo.dao;

import lombok.*;

import java.util.*;

@Data
@Builder
public class StudentResponseDTO {
    private List<StudentDetailsDTO> studentDetailDTO;
    private int statusCode;
    private String statusMessage;

}
