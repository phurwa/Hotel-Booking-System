package library1;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyJDBC {
	public MyLibs login(MyLibs user) {
		PreparedStatement pstat; // declare preparedStatement
		boolean result = false;
		String sql = "SELECT * FROM customer where emailID =? AND pass =?";
		// exception handling
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // driver load
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel2", "root", "Phurwa@807"); // connection
																														// establish
			pstat = conn.prepareStatement(sql); // create statement
			pstat.setString(1, user.getEmailID());
			pstat.setString(2, user.getPassword());
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				user.setCustomerID(rs.getInt("CustomerID"));
				user.setFullName(rs.getString("fullName"));
				user.setCustomerType(rs.getString("CustomerType"));
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return user;
	}

	public boolean save(MyLibs user) {
		PreparedStatement pstat; // declare preparedStatement
		boolean result = false;
		String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)"; // query to insert values
		// exception handling
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // driver load
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel2", "root", "Phurwa@807"); // connection
																														// establish
			pstat = conn.prepareStatement(sql); // create statement

			pstat.setInt(1, user.getCustomerID());
			pstat.setString(2, user.getFullName());
			pstat.setString(3, user.getAddress());
			pstat.setString(4, user.getEmailID());
			pstat.setString(5, user.getPhoneNo());
			pstat.setString(6, user.getCustomerType());
			pstat.setString(7, user.getCardNo());
			pstat.setString(8, user.getPassword());
			pstat.setString(9, user.getCompanyName());
			pstat.executeUpdate();

			pstat.close();
			conn.close();

			result = true;
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
		}
		return result;
	}

}
