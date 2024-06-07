package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Clone {
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
						"Kishore@123");
				
				Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
						"Kishore@123");
				
				Statement st = con.createStatement();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO public.\"EMP\" VALUES (?,?,?,?)")){
	
			int count=0;
			if(st!=null) {
				ResultSet rs =st.executeQuery("SELECT * FROM EMP");				
				while(rs.next()) {
					ps.setInt(1, rs.getInt(1));
					ps.setString(2, rs.getString(2));
					ps.setInt(3, rs.getInt(3));
					ps.setString(4, rs.getString(4));
					ps.executeUpdate();
					count++;
				}
			}
			System.out.println("No of lines inserted.. "+count);
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
