package com.rabbitmq.demo.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
@ToString
@Builder
public class StudentEntity implements Serializable {
    @Id
    @NotEmpty
    private int studentID;

    @Column(name="sur_name")
    private String surName;

    @Column(name="other_names")
    private String otherNames;

    private int age;
    @Column(name="id_number")
    private int idNumber;

    private String occupation;

    @Column(name="registered_date")
    private Date registeredDate;
}
