package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class postgresCode {
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Kishore@123");
			PreparedStatement st=con.prepareStatement("insert into public.\"Billing\" values(nextval('increment_1'),?,?)")){
			
			Scanner sc=new Scanner(System.in);
			System.out.print("Please enter item name .. ");
			st.setString(1, sc.next());
			sc.nextLine();
			System.out.print("Please enter cost.. ");
			st.setFloat(2, sc.nextFloat());
			
			int count = st.executeUpdate();
			if(count==1)
				System.out.println("Record is inserted");
			else
				System.out.println("No record is inserted");
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
