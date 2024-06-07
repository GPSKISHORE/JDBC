package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class nthlargest {
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
						"Kishore@123");
				Statement st = con.createStatement();
				PreparedStatement ps = con.prepareStatement(
						"SELECT * FROM EMP WHERE SAL= (SELECT DISTINCT SAL FROM EMP A WHERE (SELECT COUNT(DISTINCT SAL) FROM EMP B WHERE A.SAL<=B.SAL)=?)")) {

			int count = st.executeUpdate("SELECT DISTINCT SAL FROM EMP");

			System.out.println(
					"IN emp table total \"" + count + "\" unique sal found, please enter number between those");

			System.out.print("please enter number to find highest sal ");
			int num = sc.nextInt();

			if (num <= count && num > 0) {
				if (ps != null) {
					ps.setInt(1, num);
					ResultSet rs = ps.executeQuery();
					System.out.println();
					System.out.println(" -----------------------------------");

					while (rs.next()) {

						System.out.println(" | " + rs.getInt(1) + " * " + rs.getString(2) + " * " + rs.getInt(3) + " * "
								+ rs.getString(4) + " | ");
						System.out.println(" -----------------------------------");
					}
				}
			} else {
				System.out.println("your entered out of range number..!");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
