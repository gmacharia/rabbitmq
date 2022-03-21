package com.rabbitmq.demo.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class StudentDetailsDTO {

    @JsonProperty
    @NotNull(message = "student id  cannot be null")
    @NotEmpty(message = "student id cannot be empty")
    @Email
    private int studentID;
    private String surName;
    private String otherNames;
    private int age;
    @NotEmpty
    @Size(min = 2, message = "id number should have at least 5 digits")
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