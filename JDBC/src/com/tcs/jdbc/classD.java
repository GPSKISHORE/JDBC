package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* Update SAL COLUMN BY INCREING THE SAL 10% FOR ENTER PEOPLE 
 * in EMP table inside xe db using sql query*/

public class classD {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int count=0;
		try {
			int empno=0;
			sc=new Scanner(System.in);
			System.out.print("Please enter EMP number, that you want to increse 10%..");
			empno=sc.nextInt();
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kishore@123");
			if(con!=null)
				st=con.createStatement();
			if(st!=null)
				count=st.executeUpdate("UPDATE EMP SET SAL= (SAL+(SAL/10)) where EMPNO="+empno);
			System.out.println("Number of records that are effected "+count);
		}catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}try {
				if(sc!=null)
					sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("*****************END*******************");
	}

}
