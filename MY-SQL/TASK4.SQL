SELECT * FROM CUSTOMERS WHERE NO_OF_ORDERS=0

SELECT COUNT(*) AS NO_OF_PRODUCTS FROM PRODUCTS

SELECT SUM(TOTAL_AMOUNT) AS TOTALREVENUE FROM ORDERS

SELECT P.PRODUCT_NAME,AVG(OD.QUANTITY) FROM ORDERDETAILS OD 
JOIN PRODUCTS P USING (PRODUCT_ID)
WHERE exists (SELECT * FROM PRODUCTS WHERE P.CATEGORY='mobile') 
GROUP BY PRODUCT_ID  

SELECT C.CUSTOMER_ID,CONCAT(C.FIRST_NAME,' ',C.LAST_NAME),SUM(O.TOTAL_AMOUNT) FROM ORDERS O JOIN CUSTOMERS C 
WHERE O.CUSTOMER_ID=1 

SELECT CONCAT(FIRST_NAME,' ',LAST_NAME),NO_OF_ORDERS FROM CUSTOMERS
WHERE NO_OF_ORDERS=(SELECT MAX(NO_OF_ORDERS) FROM CUSTOMERS);

SELECT P.CATEGORY FROM PRODUCTS P JOIN ORDERDETAILS OD WHERE OD.QUANTITY=(SELECT MAX(QUANTITY) FROM ORDERDETAILS) GROUP BY QUANTITY

select concat(c.first_name,' ',c.last_name),sum(o.total_amount) as total_amount 
from customers c join orders o using(customer_id) group by customer_id
having total_amount=
(select max(total_amount) from (select sum(total_amount) as total_amount from orders group by customer_id) as map_spending)

select concat(c.first_name,' ',c.last_name),avg(o.total_amount)/no_of_orders as average_value
from customers c join orders o using (customer_id) group by customer_id

select concat(c.first_name,' ',c.last_name) as customer_name,no_of_orders from customers c