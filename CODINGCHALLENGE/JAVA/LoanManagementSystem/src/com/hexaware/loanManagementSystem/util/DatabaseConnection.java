package com.hexaware.loanManagementSystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

static Connection connection=null;
	
	public static Connection getConnection(String fileName) throws Exception{
		
		try {
			String conn=DBPropertiesFile.getConnectionString(fileName);
			String[] pair = conn.split("\\|");
			String url = pair[0];
			String user = pair[1];
			String pass = pair[2];
			String driver = pair[3];
		
			System.out.println("Connection Established");
			 connection = DriverManager.getConnection(url,user,pass);
			
		}catch(SQLException e)
		{
			
			System.err.println("Database connection Failed " + e.getMessage());
		}
		
		return connection;
	}
}
