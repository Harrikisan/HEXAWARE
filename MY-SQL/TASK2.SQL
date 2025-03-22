SELECT concat(first_name,' ',last_name) as Names,email from customers

select 
customers.customer_id,
concat(customers.first_name,' ',customers.last_name),
orders.order_id,
orders.order_date from customers
inner join orders using (customer_id)


insert INTO CUSTOMERS values(10,'Praveenkumar','M','praveenkumar8844@gmail.com','8610481045','abc street');

UPDATE products  
SET price = price + (price * (10/100))  
WHERE price > 0;


delete from orders where order_id=1;
delete from orderdetails where order_id=1;


insert into orders values(1,1,now(),21000)

update  customers
set email='alicejohnson@gmail.com' where customer_id=3;


update  orders
join orderdetails on orders.order_id=orderdetails.order_id
join products on orderdetails.product_id=products.product_id
set orders.total_amount=products.price*orderdetails.quantity


DELETE orderdetails,orders FROM orders  
JOIN orderdetails ON orders.order_id = orderdetails.order_id  
WHERE orders.customer_id = 3;


insert into products values(7,'redmi note 8 pro','4g mobile,64 gb storage,8 gb ram',15000);

update orders
set status='shipped' where order_id=1


update customers
set no_of_orders=(select count(order_id) from orders where customers.customer_id=orders.customer_id );


