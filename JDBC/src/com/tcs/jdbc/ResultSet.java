package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSet {
	
	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kishore@123");
			Statement st=con.createStatement(java.sql.ResultSet.TYPE_SCROLL_SENSITIVE,java.sql.ResultSet.CONCUR_UPDATABLE)){
			
			if(st!=null) {
				
				java.sql.ResultSet rs=st.executeQuery("SELECT * FROM EMP");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" | "+rs.getString(2));
				}
				System.out.println("***************************************************");
				while(rs.previous()) {
					System.out.println(rs.getInt(1)+" | "+rs.getString(2));
				}
				System.out.println("***************************************************");
				
				rs.first();
				System.out.println(rs.getInt(1)+" | "+rs.getString(2));
				
				rs.last();
				System.out.println(rs.getRow()+rs.getInt(1)+" | "+rs.getString(2));
				System.out.println("***************************************************");
				
				rs.relative(-1);
				System.out.println(rs.getRow()+rs.getInt(1)+" | "+rs.getString(2));
				
				rs.relative(-5);
				System.out.println(rs.getRow()+"--->"+rs.getInt(1)+" | "+rs.getString(2));
				
				System.out.println("***************************************************");
				
				rs.absolute(1);
				System.out.println(rs.getInt(1)+" | "+rs.getString(2));
				rs.absolute(3);
				System.out.println(rs.getInt(1)+" | "+rs.getString(2));
				rs.absolute(-3);
				System.out.println(rs.getRow()+rs.getInt(1)+" | "+rs.getString(2));
				
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
