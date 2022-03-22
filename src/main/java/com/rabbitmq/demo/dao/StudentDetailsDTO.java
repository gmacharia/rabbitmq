package com.rabbitmq.demo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class StudentDetailsDTO {

    private int studentID;

    private String surName;

    private String otherNames;

    private int age;

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