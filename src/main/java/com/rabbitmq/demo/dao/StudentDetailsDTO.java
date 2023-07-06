package com.rabbitmq.demo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class StudentDetailsDTO {

    @NotNull
    @Min(
            value = 2,
            message = "There must be at least {value} test{value > 1 ? 's' : ''} in the test case"
    )
    private int studentID;

    @NotNull(message = "surname cant be null")
    @NotEmpty
    private String surName;

    @NotNull(message = "otherNames cant be null")
    @NotEmpty
    private String otherNames;

    private int age;

    @NotNull
    @Range(min = 1)
    private int idNumber;

    private String occupation;
}

/*
http://localhost:10010/student/v1/create
{
        "studentID": 1,
        "surName": "",
        "otherNames": "",
        "age": 18,
        "idNumber": 12,
        "occupation": ""
        }
*/
