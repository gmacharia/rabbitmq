package com.rabbitmq.demo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    private String surName;

    @NotNull
    private String otherNames;

    @NotNull
    private int age;

    @NotNull
    private int idNumber;

    @NotNull
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