CREATE DATABASE TECHSHOP;


USE TECHSHOP;


create table customers(
customer_id int primary key auto_increment,
first_name varchar(100) not null,
last_name varchar(100),
email varchar(100) not null, 
phone varchar(50),
address varchar(100)
);


create table products(
product_id int primary key auto_increment,
product_name varchar(100) not null,
description varchar(100) ,
price int
);

create table orders(
order_id int primary key auto_increment,
customer_id int,
order_date timestamp default current_timestamp,
total_amount varchar(100),
foreign key (customer_id) references customers(customer_id)
);

create table orderdetails(
orderdetailid int primary key auto_increment,
order_id int,
product_id int,
quantity int not null,
foreign key (order_id) references orders(order_id),
foreign key (product_id) references products(product_id)
);

create table inventory(
inventory_id int primary key auto_increment,
product_id int,
quantityinstock int,
laststockupdate date,
foreign key (product_id) references products(product_id)
);


INSERT INTO customers 
VALUES (1, 'Harrikisan', 'M', 'harrikisan5577@gmail.com', '9345520672', 'abc street');

INSERT INTO customers 
VALUES (2, 'Jane Smith', 'F', 'janesmith@example.com', '9123456789', '456 Oak St');

INSERT INTO customers 
VALUES (3, 'Alice Johnson', 'F', 'alicej@example.com', '9765432109', '789 Pine St');

INSERT INTO customers 
VALUES (4, 'Bob Brown', 'M', 'bobbrown@example.com', '9345678901', '101 Birch St');

INSERT INTO customers 
VALUES (5, 'Charlie Davis', 'M', 'charliedavis@example.com', '9212345678', '202 Cedar St');

INSERT INTO customers 
VALUES (6, 'Eva Williams', 'F', 'evawilliams@example.com', '9456781234', '303 Elm St');

INSERT INTO customers 
VALUES (7, 'George Harris', 'M', 'georgeharris@example.com', '9108765432', '404 Willow St');

INSERT INTO customers 
VALUES (8, 'Mia Clark', 'F', 'miaclark@example.com', '9348123567', '505 Redwood St');

INSERT INTO customers 
VALUES (9, 'Liam Lee', 'M', 'liamlee@example.com', '9123478901', '606 Sequoia St');


INSERT INTO PRODUCTS VALUES(1,'vivo t3','5g mobile,128 gb storage,6 gb ram','20000');

INSERT INTO PRODUCTS VALUES (2, 'Samsung Galaxy A54', '5G mobile, 128GB storage, 8GB RAM', '32000');  

INSERT INTO PRODUCTS VALUES (3, 'iPhone 15', '5G mobile, 256GB storage, 6GB RAM', '80000');  

INSERT INTO PRODUCTS VALUES (4, 'OnePlus Nord 3', '5G mobile, 256GB storage, 12GB RAM', '34000');  

INSERT INTO PRODUCTS VALUES (5, 'Realme Narzo 60', '5G mobile, 128GB storage, 6GB RAM', '18000');  

INSERT INTO PRODUCTS VALUES (6, 'Redmi Note 12 Pro', '5G mobile, 128GB storage, 8GB RAM', '24000');  


INSERT INTO ORDERS VALUES(1,1,now(),'21000');
INSERT INTO ORDERS VALUES (2, 2, NOW(), 32000);  
INSERT INTO ORDERS VALUES (3, 3, NOW(), 80000);  
INSERT INTO ORDERS VALUES (4, 4, NOW(), 34000);  
INSERT INTO ORDERS VALUES (5, 5, NOW(), 18000);  
INSERT INTO ORDERS VALUES (6, 6, NOW(), 24000);  
INSERT INTO ORDERS VALUES (7, 7, NOW(), 20000);  
INSERT INTO ORDERS VALUES (8, 8, NOW(), 32000);  
INSERT INTO ORDERS VALUES (9, 9, NOW(), 34000);  
INSERT INTO ORDERS VALUES (10, 1, NOW(), 80000);  


INSERT INTO ORDERDETAILS VALUES (1, 1, 1, 1);
INSERT INTO ORDERDETAILS VALUES (2, 2, 2, 1);  
INSERT INTO ORDERDETAILS VALUES (3, 3, 3, 1);  
INSERT INTO ORDERDETAILS VALUES (4, 4, 4, 1);  
INSERT INTO ORDERDETAILS VALUES (5, 5, 5, 2);  
INSERT INTO ORDERDETAILS VALUES (6, 6, 6, 1);  
INSERT INTO ORDERDETAILS VALUES (7, 7, 1, 2);  
INSERT INTO ORDERDETAILS VALUES (8, 8, 2, 1);  
INSERT INTO ORDERDETAILS VALUES (9, 9, 4, 2);  
INSERT INTO ORDERDETAILS VALUES (10, 10, 3, 1);  


INSERT INTO inventory VALUES (1, 1, 1, '2025-03-21');
INSERT INTO inventory VALUES (2, 2, 5, '2025-03-21');  
INSERT INTO inventory VALUES (3, 3, 2, '2025-03-22');  
INSERT INTO inventory VALUES (4, 4, 10, '2025-03-23');  
INSERT INTO inventory VALUES (5, 5, 7, '2025-03-24');  
INSERT INTO inventory VALUES (6, 6, 3, '2025-03-25');  
INSERT INTO inventory VALUES (7, 1, 6, '2025-03-26');  
INSERT INTO inventory VALUES (8, 2, 8, '2025-03-27');  
INSERT INTO inventory VALUES (9, 3, 4, '2025-03-28');  
INSERT INTO inventory VALUES (10, 4, 9, '2025-03-29');  


