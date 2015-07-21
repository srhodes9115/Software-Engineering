#create database Employee_Information;

USE Employee_Information;

DROP TABLE IF EXISTS Employees;

CREATE TABLE Employees
(cdid varchar(255) Primary Key, #unique id
firstName varchar(255) NOT NULL,
lastName varchar(255) not null,
title varchar(255) NOT null,
company int not null
);

select * from Employees;
