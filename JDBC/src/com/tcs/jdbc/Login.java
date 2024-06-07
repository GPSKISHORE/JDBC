package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
						"Kishore@123");
				Statement st = con.createStatement();) {

			String user_name = null, password = null;
			int count = 0;
			if (sc != null) {
				System.out.print("Please enter user name.. ");
				user_name = sc.next();
				sc.nextLine();
				System.out.print("Please enter password.. ");
				password = sc.next();
			}

			if (st != null) {
				count = st.executeUpdate("SELECT * FROM USER_CREDDENTIALS WHERE UNAME=" + "'" + user_name + "'"
						+ " AND PWD=" + "'" + password + "'");
				System.out.println("Command --> " + "SELECT * FROM USER_CREDDENTIALS WHERE UNAME=" + "'" + user_name
						+ "'" + " AND PWD=" + "'" + password + "'");
				System.out.println("Count of records--> " + count);
			}
			if (count == 1)
				System.out.println("Login Sussfull.!");
			else
				System.out.println("Login Failed.!");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
