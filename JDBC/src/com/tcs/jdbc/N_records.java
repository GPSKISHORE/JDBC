package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class N_records {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kishore@123");
			PreparedStatement st=con.prepareStatement("SELECT * FROM STUDENT",java.sql.ResultSet.TYPE_SCROLL_SENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
			Scanner sc=new Scanner(System.in)){
			
			java.sql.ResultSet rs=st.executeQuery();
			
			if(sc!=null) {
				System.out.println("Please enter number of records that you want to get, max limit is "+7);
				int a=sc.nextInt(), b=0;
				
				while(rs.next() && b<a) {
					b++;
					System.out.println(rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+rs.getFloat(4));
				}
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
