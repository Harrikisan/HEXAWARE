package com.hexaware.electronicgadgets.main;

import com.hexaware.electronicgadgets.dao.implementations.*;
import com.hexaware.electronicgadgets.entities.*;
import com.hexaware.electronicgadgets.exceptions.DatabaseOperationException;
import com.hexaware.electronicgadgets.exceptions.InputValidationException;
import com.hexaware.electronicgadgets.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;
public class App {
	
	static CustomerdaoImpl Customer=new CustomerdaoImpl();
	static OrdersdaoImpl Orders=new OrdersdaoImpl();
	static OrderdetailsdaoImpl Orderdetails=new OrderdetailsdaoImpl();
	static ProductsdaoImpl Products=new ProductsdaoImpl();
	static InventorydaoImpl Inventory=new InventorydaoImpl();
	
	public static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException, InputValidationException {
		DatabaseConnection.getConnection();
		
		
		
		
		
		while(true) {
			System.out.println("TECHSHOP");
			System.out.println("--------");
			System.out.println("Enter your choise");
			System.out.println("1.Manage Customer \n"
					+ "2.Manage Products \n"
					+ "3.Manage Orders \n"
					+ "4.Manage Order Information \n"
					+ "5.Manage Inventory");
			int n=sc.nextInt();
			
			switch(n) {
			case 1->manageCustomer();
			case 2->manageProduct();
			case 3->manageOrder();
			case 4->manageOrderDetails();
			case 5->manageInventory();
			default->System.out.println("Invalid choise");
			}
		}
		
		
		
		
	}
	
	private static void  manageCustomer() throws InputValidationException {
		// TODO Auto-generated method stub
		System.out.println("Enter action choise");
		System.out.println("1.Register User \n"
				+ "2.ListAllUsers \n"
				+ "3.Update FirstName \n"
				+ "4.Update LastName \n"
				+ "5.Updaete email \n"
				+ "6.Update phone number \n"
				+ "7.Update address \n"
				+ "8.Calculate total orders \n"
				+ "9.Delete User");
		int n=sc.nextInt();
		
		
		switch(n) {
		case 1:
			
			System.out.println("Enter ID");
			int id=sc.nextInt();
			System.out.println("Enter FirstName");
			String fName=sc.next();
			System.out.println("Enter LastName");
			String lName=sc.next();
			System.out.println("Enter email");
			String email=sc.next();
			System.out.println("Enter phone number");
			String phone=sc.next();
			System.out.println("Enter address");
			String address=sc.next();
			
			Customer cust=new Customer(id, fName,lName,email,phone,address);
			try {
				Customer.addCustomer(cust);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				Customer.listAllCustomers();
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new FirstName:");
			String name=sc.next();
			try {
				Customer.updateFirstName(name, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new LastName:");
			String temp=sc.next();
			try {
				Customer.updateLastName(temp, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new email:");
			temp=sc.next();
			try {
				Customer.updateEmail(temp, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new phone number:");
			temp=sc.next();
			try {
				Customer.updatePhoneNumber(temp, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 7:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new address:");
			temp=sc.next();
			try {
				Customer.updateAddress(temp, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 8:
			System.out.println("Enter id");
			id=sc.nextInt();
			try {
				Customer.CalculateTotalOrders(id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 9:
			System.out.println("Enter id");
			id=sc.nextInt();
			try {
				Customer.deleteCustomer(id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default :
			System.out.println("Invalid choise");
		}
		
		
	}
	
	private static void manageProduct() {
		// TODO Auto-generated method stub
		System.out.println("Enter action choise");
		System.out.println("1.Add product \n"
				+ "2.List all products \n"
				+ "3.Update product name \n"
				+ "4.Update description \n"
				+ "5.Update price \n"
				+ "6.Update category \n"
				+ "7.Delete product");
		
		int n=sc.nextInt();
		
		switch(n) {
		case 1:
			System.out.println("Enter ID");
			int id=sc.nextInt();
			System.out.println("Enter product name");
			String name=sc.next();
			System.out.println("Enter Product description");
			String desc=sc.next();
			System.out.println("Enter price ");
			int price=sc.nextInt();
			System.out.println("Enter category");
			String category=sc.next();
			
			Products product=new Products(id, name, desc, price,category);
			try {
				Products.addProduct(product);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				Products.listAllProducts();
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Enter product id");
			id=sc.nextInt();
			System.out.println("Enter new product name");
			String temp=sc.next();
			try {
				Products.updateProductName(temp, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			System.out.println("Enter product id");
			id=sc.nextInt();
			System.out.println("Enter new description");
			temp=sc.next();
			try {
				Products.updateDescription(temp, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			System.out.println("Enter product id");
			id=sc.nextInt();
			System.out.println("Enter new price");
			price=sc.nextInt();
			try {
				Products.updateprice(price, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			System.out.println("Enter product id");
			id=sc.nextInt();
			System.out.println("Enter new category");
			temp=sc.next();
			try {
				Products.updateCategory(temp, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		
	}
	
	private static void manageOrder() {
		// TODO Auto-generated method stub
		System.out.println("Enter action choise");
		System.out.println("1.Place order \n"
				+ "2.view all orders \n"
				+ "3.update customer id \n"
				+ "4.update order date \n"
				+ "5.update total amount \n"
				+ "6.update status \n"
				+ "7.Calculate total amount of order \n"
				+ "8.Cancel Order");
		
		int n=sc.nextInt();
		
		switch(n) {
		case 1:
			System.out.println("Enter Order ID");
			int id=sc.nextInt();
			System.out.println("Enter Customer ID");
			int custId=sc.nextInt();
			Customer cust=new Customer(custId);
			System.out.println("Enter order date");
			String date=sc.next();
			System.out.println("Enter total amount");
			int amount=sc.nextInt();
			System.out.println("Enter status");
			String status=sc.next();
			
			Orders order=new Orders(id, cust, date, amount,status);
			try {
				Orders.addOrder(order);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				Orders.viewAllOrders();
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new customer id");
			int cID=sc.nextInt();
			try {
				Orders.updateCustomerId(cID, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new order date");
			String temp=sc.next();
			try {
				Orders.updateOrderDate(temp, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new total amount");
			amount=sc.nextInt();
			try {
				Orders.updateTotalAmount(amount, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new status:pending,shipped");
			temp=sc.next();
			try {
				Orders.updateStatus(temp, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 7:
			System.out.println("Enter ID");
			id=sc.nextInt();
			try {
				Orders.cancelOrder(id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			break;
		case 8:
			System.out.println("Enter ID");
			id=sc.nextInt();
			try {
				Orders.calculateTotalAmount(id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
			
		}
		
	}
	
	private static void manageOrderDetails() {
		// TODO Auto-generated method stub
		System.out.println("Enter action choise");
		System.out.println("1.Add Order Information \n"
				+ "2.view all order information \n"
				+ "3.update order id \n"
				+ "4.update product id \n"
				+ "5.update quantity \n"
				+ "6.delete order information");
		
		int n=sc.nextInt();
		switch(n) {
		case 1:
			System.out.println("Enter OrderdetailsID");
			int id=sc.nextInt();
			System.out.println("Enter order id");
			int ordid=sc.nextInt();
			Orders order=new Orders(ordid);
			System.out.println("Enter product id");
			int pid=sc.nextInt();
			Products product=new Products(pid);
			System.out.println("Enter quantity");
			int quantity=sc.nextInt();
			
			Orderdetails orderdetail=new Orderdetails(id, order, product, quantity);
			try {
				Orderdetails.addOrderInfo(orderdetail);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				Orderdetails.viewAllInfo();
			}catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new order id");
			ordid=sc.nextInt();
			try {
				Orderdetails.updateOrderId(ordid, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new product id");
			pid=sc.nextInt();
			try {
				Orderdetails.upadteProductId(pid, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			System.out.println("Enter ID");
			id=sc.nextInt();
			System.out.println("Enter new quantity");
			quantity=sc.nextInt();
			try {
				Orderdetails.updateQuantity(quantity, id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case 6:
			System.out.println("Enter ID");
			id=sc.nextInt();
			try {
				Orderdetails.deleteInfo(id);
			} catch (DatabaseOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
			
		
	}


	private static void manageInventory() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter action choise");
		System.out.println("1.Add to Inventory \n"
				+ "2.view all products in inventory \n"
				+ "3.update product id \n"
				+ "4.update quantity in stock \n"
				+ "5.update last stock update \n"
				+ "6.delete from inventory");
		int n=sc.nextInt();
		switch(n) {
		case 1:
			System.out.println("Enter inventory id");
			int id=sc.nextInt();
			System.out.println("Enter product_id");
			int pid=sc.nextInt();
			Products product=new Products(pid);
			System.out.println("Enter quantity in stock");
			int stock=sc.nextInt();
			System.out.println("Last stock update");
			String update=sc.next();
			
			Inventory i=new Inventory(id, product, stock, update);
			Inventory.AddToInventory(i);
			break;
		case 2:
			Inventory.viewAllProducts();
			break;
		case 3:
			System.out.println("Enter Inventory id");
			id=sc.nextInt();
			System.out.println("Enter new product id");
			pid=sc.nextInt();
			Inventory.updateProductId(pid, id);
			break;
		case 4:
			System.out.println("Enter Inventory id");
			id=sc.nextInt();
			System.out.println("Enter new quantity in stock");
			stock=sc.nextInt();
			Inventory.updateQuantityStock(stock, id);
			break;
		case 5:
			System.out.println("Enter Inventory id");
			id=sc.nextInt();
			System.out.println("Enter new last stock update");
			update=sc.next();
			Inventory.updatelastStockUpdate(update, id);
			break;
		case 6:
			System.out.println("Enter Inventory id");
			id=sc.nextInt();
			Inventory.deleteFromInventory(id);
			break;
			
		}
		
		
	}

	

	
	
	
}
