package com.rabbitmq.demo.service;

import com.rabbitmq.demo.dao.StudentDetailsDTO;
import com.rabbitmq.demo.entity.StudentEntity;
import com.rabbitmq.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {

    public final StudentRepository studentRepository;

    public  ResponseEntity<?> processStudentDetails(StudentDetailsDTO studentDetailsDTO)  {
        log.info("sanitized payload received before saving {}", studentDetailsDTO);

        try {
            StudentEntity studentEntity =
                    StudentEntity.builder()
                            .studentID(studentDetailsDTO.getStudentID())
                            .surName(studentDetailsDTO.getSurName())
                            .otherNames(studentDetailsDTO.getOtherNames())
                            .age(studentDetailsDTO.getAge())
                            .occupation(studentDetailsDTO.getOccupation())
                            .idNumber(studentDetailsDTO.getIdNumber())
                            .registeredDate(new Date())
                            .build();
            studentRepository.save(studentEntity);
            return new ResponseEntity<>(studentRepository, HttpStatus.ACCEPTED);
        }catch(Exception sq){
            log.error("Exception thrown is {}", sq.getMessage());
            return new ResponseEntity<>(sq.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
