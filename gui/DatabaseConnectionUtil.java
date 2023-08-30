package gui;
//import useful library
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnectionUtil {

	public static Connection conn = null;
	
	public static Connection ConnectDB() {	
		//try catch block
		try {
			String URL =  "jdbc:mysql://localhost:3306/hotel2";
			String uname = "root";
			String pass = "Phurwa@807";
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			conn = DriverManager.getConnection(URL, uname, pass);

		}
		catch(Exception ex) {
			System.out.println("Error:"+ex.getMessage());
		}
		return conn;
		
	}
	

	public static int getCustomerID(String email) {
		//query to retrieve customerID from customer table
		String sql = "SELECT customerID FROM customer where emailID='"+email+"'";
		int id=-1;
		try {
			Connection con = ConnectDB();	
			PreparedStatement pstat = conn.prepareStatement(sql);
			ResultSet results = pstat.executeQuery(sql);
			while(results.next()) {
				id=results .getInt(1);
			}			
		}
		catch(Exception ex) {
			id=-1;
		}		
		return id;
	}
	

}
