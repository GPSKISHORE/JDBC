package com.tcs.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsertion {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Kishore@123");
				PreparedStatement ps=con.prepareStatement("INSERT INTO public.\"Employee\" ( \"EMPNO\", \"ENAME\", \"DOB\", \"DOJ\", \"EXIT_TIME\") VALUES (?,?,?,?,?)");
				Scanner sc=new Scanner(System.in)){
			
			int num=0;
			String ename=null, dob=null,doj=null, exit=null;
			if(sc!=null) {
				System.out.print("Please enter EMP no.. ");
				num=sc.nextInt();
				System.out.print("Please enter ENAME.. ");
				ename=sc.next();
				sc.nextLine();
				System.out.print("Please enter DOB in MM/DD/YYYY .. ");
				dob=sc.next();
				sc.nextLine();
				System.out.print("Please enter DOJ yyyy/MM/dd HH:mm:ss.. ");
				doj=sc.next();
				sc.nextLine();
				System.out.print("Please enter DOB mm:HH:ss .. ");
				exit=sc.next();
			}
			int count=0;
			if(ps!=null) {
				
			ps.setInt(1, num);
			ps.setString(2, ename);
			

			SimpleDateFormat sd=new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date dd=sd.parse(dob);
			java.sql.Date d=new Date(dd.getDate());
			ps.setDate(3, d);
			
			java.sql.Timestamp ts=java.sql.Timestamp.valueOf(doj);
			ps.setTimestamp(4, ts);
			
			SimpleDateFormat a=new SimpleDateFormat("mm:HH:ss");
			java.util.Date ad=a.parse(exit);
			java.sql.Time tt=new Time(ad.getTime());
			ps.setTime(5, tt);
			
			count=ps.executeUpdate();
									}			
			if(count==1) {
				System.out.println("Record inserted ");
			}else
				System.out.println("no record is inserted");
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
