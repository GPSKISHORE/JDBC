package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// JDBC application to retrive the STUDENT dats based one 3 parameters*/

public class classA {

	public static void main(String[] args) {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		Statement stt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			String city1 = null, city2 = null, city3 = null;
			sc = new Scanner(System.in);
			System.out.print("Please enter 1st city name.. ");
			city1 = sc.next();
			sc.nextLine();
			System.out.print("Please enter 2nd city name.. ");
			city2 = sc.next();
			sc.nextLine();
			System.out.print("Please enter 3rd city name.. ");
			city3 = sc.next();

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Kishore@123");
			if (con != null) {
				st = con.createStatement();
				stt = con.createStatement();
			}
			if (st != null) {
				rs = st.executeQuery(
						"SELECT * FROM STUDENT WHERE SADD IN ('" + city1 + "','" + city2 + "','" + city3 + "')");
				count = stt.executeUpdate(
						"SELECT * FROM STUDENT WHERE SADD IN ('" + city1 + "','" + city2 + "','" + city3 + "')");
			}
			if (count != 0) {
				System.out.println("Total number of records we get ,,, " + count);
			} else {
				System.out.println("No records found..!");
			}

			if (rs != null) {
				while (rs.next()) {
					System.out.println(rs.getString(1) + " " + rs.getString(2));
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ee) {
				ee.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException ea) {
				ea.printStackTrace();
			}
			try {
				if (stt != null)
					stt.close();
			} catch (SQLException ea) {
				ea.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException see) {
				see.printStackTrace();
			}
			try {
				if (sc != null)
					sc.close();
			} catch (Exception p) {
				p.printStackTrace();
			}
		}
		System.out.println("*****************END*******************");
	}
}
