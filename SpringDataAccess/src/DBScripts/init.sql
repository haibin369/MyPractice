CREATE DATABASE spring;
USE spring;

CREATE TABLE user(
  userId   INT(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(30),
  password VARCHAR(20)
);

ALTER TABLE user ADD CONSTRAINT UNIQUE_USERNAME UNIQUE (username);