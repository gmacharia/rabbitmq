DROP TABLE IF EXISTS students;

CREATE TABLE students
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    studentID          INT,
    sur_name            VARCHAR(250) NOT NULL,
    other_names         VARCHAR(250) NOT NULL,
    age                INT,
    id_number           INT,
    occupation        VARCHAR(250) DEFAULT NULL,
    registered_date     TIMESTAMP,
    UNIQUE (studentID)
);
