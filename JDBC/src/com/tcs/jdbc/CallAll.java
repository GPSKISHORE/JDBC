package com.tcs.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

public class CallAll {

	public static void main(String[] args) {

//		 create or replace PROCEDURE p_get_EMP_ALL_details(
//		    Details OUT sys_refcursor)
//		    as
//		    begin
//		       open details for
//		       select * FROM EMP;
//		    end;

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
						"Kishore@123");
				CallableStatement cs = con.prepareCall(" {call p_get_EMP_ALL_details(?) }")) {
			if (sc != null && cs != null) {

				cs.registerOutParameter(1, OracleTypes.CURSOR);

				cs.execute();

				ResultSet rs = (ResultSet) cs.getObject(1);
				if (rs != null) {
					while (rs.next()) {
						System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getFloat(3) + " | "
								+ rs.getString(4));
					}
				} else {
					System.out.println("No records found ");
				}

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
