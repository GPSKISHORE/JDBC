package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Prepared {

	public static void main(String[] args) {
		try(Scanner sc=new Scanner(System.in);
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kishore@123");
			PreparedStatement ps=con.prepareStatement("INSERT INTO STUDENT VALUES (?,?,?,?)");){
			
			int count=1;
			int i=0;
			while(count==1) {
				i++;
				System.out.print("Please enter student number.. ");
				int no=sc.nextInt();
				System.out.print("Please enter Student name.. ");
				String name=sc.next();
				System.out.print("Please enter Student adderes.. ");
				String add=sc.next();
				System.out.print("Please enter average.. ");
				float f=sc.nextFloat();
				
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setString(3, add);
				ps.setFloat(4, f);
				ps.executeUpdate();
				
				System.out.print("If you want to insert 1 more record please enter 1, else please enter 0.. ");
					count=sc.nextInt();
				}
			System.out.println(i+" number of records inserted.!");
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
