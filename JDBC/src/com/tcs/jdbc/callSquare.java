package com.tcs.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class callSquare {

//	create or replace procedure p_squre(x IN number, z OUT number)
//	  2  as
//	  3  begin
//	  4  z:=x*x;
//	  5* end;
	
	public static void main(String[] args) {
		
		try(Scanner sc=new Scanner(System.in);
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kishore@123");
			CallableStatement cs=con.prepareCall("{ call p_squre(?,?)}")){
			
			int a=0;
			if(sc!=null && cs!=null) {
				System.out.print("Please enter a number.. ");
				a=sc.nextInt();
				
				cs.registerOutParameter(2, Types.INTEGER);
				
				cs.setInt(1, a);
				cs.execute();
				
				int result=cs.getInt(2);
				System.out.println("Square of given number is.. "+result);
				
			}
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
