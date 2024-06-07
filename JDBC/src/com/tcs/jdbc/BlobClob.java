package com.tcs.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BlobClob {

	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
				"Kishore@123");
				PreparedStatement ps = con.prepareStatement("INSERT INTO PERSON VALUES(?,?,?,?)");
				Scanner sc = new Scanner(System.in)) {
			int num = 0;
			String name = null, pic = null, profile = null;

			if (sc != null) {
				System.out.print("Please enter person number.. ");
				num = sc.nextInt();
				System.out.print("Please enter person name.. ");
				name = sc.next();

				sc.nextLine();
				System.out.print("Please enter pic path.. ");
				pic = sc.next().trim();
				System.out.println(pic);

				sc.nextLine();
				System.out.print("Please enter profile pathe.. ");
				profile = sc.next().trim();
				System.out.println(profile);

				try (FileInputStream fis = new FileInputStream(pic); FileReader fr = new FileReader(profile);) {
					if (ps != null) {
						int count = 0;
						ps.setInt(1, num);
						ps.setString(2, name);
						ps.setBinaryStream(3, fis);
						ps.setCharacterStream(4, fr);
						count = ps.executeUpdate();
						if (count == 0) {
							System.out.println("No records inserted");
						} else
							System.out.println(count + " record inserteds");
					}
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
