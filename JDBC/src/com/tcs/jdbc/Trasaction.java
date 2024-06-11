package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Trasaction {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
						"Kishore@123");
				PreparedStatement ps = con.prepareStatement("select sal from emp where empno= ?");
				Statement st = con.createStatement()) {

			con.setAutoCommit(false);
			System.out.print("Please enter EMP no that you want to dept ");
			int dep = sc.nextInt();

			System.out.print("Please enter EMP no that you want to credit ");
			int cre = sc.nextInt();

			System.out.print("Please enter Amount ");
			int amo = sc.nextInt();

			st.addBatch("UPDATE EMP SET SAL= SAL - " + amo + " WHERE EMPNO= " + dep);
			st.addBatch("UPDATE EMP SET SAL= SAL + " + amo + " WHERE EMPNO= " + cre);

			ps.setInt(1, dep);

			java.sql.ResultSet rs = ps.executeQuery();
			rs.next();

			int balence = rs.getInt(1);

			if (amo > balence) {
				System.out.println("Inssueficient funds ");
				return;
			}

			int[] res = st.executeBatch();
			boolean tra = true;
			for (int i = 0; i < res.length; i++) {
				if (res[i] == 0) {
					tra = false;
					break;
				}
			}
			if (tra) {
				System.out.println("Transaction succesfull and commited ");
				con.commit();
			} else {
				System.out.println("Transaction failed");
				con.rollback();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
