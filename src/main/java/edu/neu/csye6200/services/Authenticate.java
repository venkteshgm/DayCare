package edu.neu.csye6200.services;

import java.sql.*;

public class Authenticate {

//	public static void main(String args[]) {
////		try {
////			Class.forName("com.mysql.cj.jdbc.Driver");
////			Connection con = DriverManager.getConnection(
////					"jdbc:mysql://database-1.cog3jk7pua93.us-east-2.rds.amazonaws.com:3306/daycaredb", "admin",
////					"Namrataisbad");
////			Statement stmt = con.createStatement();
////			ResultSet rs = stmt.executeQuery("select * from test");
////			while (rs.next())
////				System.out.println(rs.toString());
////			con.close();
////		} catch (Exception e) {
////			System.out.println(e);
////		}
//		Authenticate at = new Authenticate();
//		System.out.println(at.authenticate("Ebby", "awesome"));
//	}

	public boolean authenticate(String userName, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://database-1.cog3jk7pua93.us-east-2.rds.amazonaws.com:3306/daycaredb", "admin",
					"Namrataisbad");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from admin_users where username='"+ userName+ "' and pword = '" + password +"'" );
			if(rs.next() == false) {
				con.close();
				return false;
			}else {
				con.close();
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;

	}
}
