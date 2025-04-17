DATABASE
--------


create database loanManagement

USE LOANMANAGEMENT

CREATE TABLE customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email_address VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    address VARCHAR(255),
    credit_score INT
);

CREATE TABLE loan (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    principal_amount DOUBLE NOT NULL,
    interest_rate DOUBLE NOT NULL,
    loan_term INT NOT NULL, -- tenure in months
    loan_type VARCHAR(20) NOT NULL,
    loan_status VARCHAR(20) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

create table homeloan(
homeloan_id int primary key auto_increment,
loan_id int,
property_name varchar(50),
property_value varchar(50),
foreign key(loan_id) references loan(loan_id)
);

create table carloan(
car_id int primary key auto_increment,
loan_id int,
car_name varchar(50),
car_value varchar(50),
foreign key(loan_id) references loan(loan_id)
)