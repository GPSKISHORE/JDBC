package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Batch {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kishore@123");
			Statement st=con.createStatement()){
			
			st.addBatch("INSERT INTO STUDENT VALUES(1521,'Krish','India',15.25)");
			st.addBatch("UPDATE STUDENT SET AVG=85.45 WHERE AVG=15.25");
			st.addBatch("DELETE FROM STUDENT WHERE SNAME='naresh'");
			
			int result[]=st.executeBatch();
			
			int sum=0;
			for(int i=0;i<result.length;i++) {
				sum=sum+result[i];						
			}
			System.out.println("Total records that are effected .. "+sum);
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
