package com.rabbitmq.demo.publisher;

import com.rabbitmq.demo.config.PropertiesConfig;
import com.rabbitmq.demo.dao.StudentDetailsDTO;
import com.rabbitmq.demo.dao.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/v1")
@Slf4j
public class StudentControllerPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final PropertiesConfig propertiesConfig;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createStudentDetails(@Valid @RequestBody StudentDetailsDTO
                                                                   studentDetailsDTO) {
        // rabbitTemplate.convertAndSend(rabbitMQConfig.createStudentQueue().getName(), studentDetailsDTO);
        rabbitTemplate.convertAndSend(propertiesConfig.getExchangeName(),
                propertiesConfig.getRoutingKey(),
                studentDetailsDTO);

        List<StudentDetailsDTO> studentList = new ArrayList<>();
        studentList.add(studentDetailsDTO);

        StudentResponseDTO studentResponseDTO =
                StudentResponseDTO.builder()
                        .studentDetailDTO(studentList)
                        .statusCode(propertiesConfig.getSucessStatusCode())
                        .statusMessage(propertiesConfig.getSucessStatusMessage())
                        .build();
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.OK);
    }
}
