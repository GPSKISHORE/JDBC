package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class classB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		Statement stt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			int eno = 0;
			sc = new Scanner(System.in);
			System.out.print("Please enter employee number.. ");
			eno = sc.nextInt();

			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Kishore@123");
			if (con != null) {
				st = con.createStatement();
				stt = con.createStatement();
			}
			if (st != null) {
				rs = st.executeQuery("SELECT * FROM EMP");
				count = stt.executeUpdate("SELECT * FROM EMP");
			}
			if (count != 0) {
				System.out.println("Total number of records we get ,,, " + count);
			} else {
				System.out.println("No records found..!");
			}

			if (rs != null) {
				if (rs.next())
					System.out.println(rs.getString(1) + " " + rs.getString(2));

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
