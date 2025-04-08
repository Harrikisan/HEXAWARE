package com.hexaware.electronicgadgets.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	
	private static final String URL="jdbc:mysql://localhost:3306/techshop";
	private static final String user="root";
	private static final String password="";
	private static Connection connection;
	private DatabaseConnection() {}
	
	public static Connection getConnection() {
		
		if(connection==null) {
			try {
				connection=DriverManager.getConnection(URL,user,password);
				System.out.println("Connection established");
			}catch(SQLException e){
				System.err.println("Connection failed:"+e.getMessage());
			}
			
		}
		
		return connection;
		
	}
	
}
