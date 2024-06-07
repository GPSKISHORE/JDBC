package com.tcs.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class FunctionCall {

	public static void main(String[] args) {

//		create or replace function fx_student_details( no in number, name out varchar2)return float
//				as
//				average float;
//				begin
//					select sname,avg into name,average from student where sno=no;
//					return average;
//				end;
				
		try(Scanner sc=new Scanner(System.in);
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kishore@123");
			CallableStatement cs=con.prepareCall("{? = call fx_student_details(?,?)}")){
		
			if(sc!=null && cs!=null) {
				System.out.print("Please enter student number..");
				
				cs.registerOutParameter(1, Types.FLOAT);
				cs.registerOutParameter(3,Types.VARCHAR);
				
				cs.setInt(2, sc.nextInt());
				
				cs.execute();
				
				System.out.println("Name of the student is '"+cs.getString(3)+"' and got the average is "+cs.getFloat(1));
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
