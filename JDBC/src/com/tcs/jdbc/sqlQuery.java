package com.tcs.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class sqlQuery {

	public static void main(String[] args) {

//		create or replace procedure p_get_EMP_details(
//				num IN NUMBER,
//				Name OUT STRING,
//				SAL OUT FLOAT)
//				as
//				begin
//
//					select  ENAME, SAL INTO Name, SAL FROM EMP WHERE EMPNO=num;
//				end;
//				/

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
						"Kishore@123");
				CallableStatement cs = con.prepareCall(" {call p_get_EMP_details(?,?,?) }")) {
			if (sc != null && cs != null) {
				System.out.print("Please enter the employee number.. ");

				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.FLOAT);

				cs.setInt(1, sc.nextInt());

				cs.execute();

				System.out.println("Name is '" + cs.getString(2) + "' and the sal is " + cs.getFloat(3));
			}
		} catch (SQLException se) {
			if (se.getErrorCode() == 1403)
				System.out.println("No employee found ");
			else if (se.getErrorCode() == 933)
				System.out.println("Call statement syntax error ");
			else
				se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
