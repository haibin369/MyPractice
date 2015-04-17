CREATE DATABASE J2EE;
USE J2EE;

CREATE TABLE JTA (
  test VARCHAR(10)
);

CREATE TABLE EJB (
  test VARCHAR(20)
);

CREATE TABLE teacher (
  id           INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  teacher_name VARCHAR(50)
);

CREATE TABLE teacher_address (
  teacher_id INT(10),
  address    VARCHAR(100),
  CONSTRAINT pk_teacher_add PRIMARY KEY (teacher_id),
  CONSTRAINT fk_teacher_add_teacher_id FOREIGN KEY (teacher_id) REFERENCES teacher (id)
);

CREATE TABLE student (
  id           INT(10) NULL AUTO_INCREMENT,
  student_name VARCHAR(50),
  teacher_id INT(10),
  CONSTRAINT pk_student PRIMARY KEY (id),
  CONSTRAINT fk_teacher_id FOREIGN KEY (id) REFERENCES teacher (id)
);