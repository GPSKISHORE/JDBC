package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class preparedLog {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
					"Kishore@123");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM user_creddentials WHERE UNAME= ? AND PWD =? ");
			int count = 0;
			try (con; ps) {
				if (ps != null) {
					System.out.print("Please enter user name.. ");
					ps.setString(1, sc.next());
					sc.nextLine();
					System.out.print("Please enter password.. ");
					ps.setString(2, sc.next());

					System.out.println(ps);
					count = ps.executeUpdate();

					if (count == 1) {
						System.out.println("Login successfully....!");
					} else {
						System.out.println("Login failed....!");
					}
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
