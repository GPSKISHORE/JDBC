package com.tcs.jdbc;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class ClobBlob {
	public static void main(String[] args) {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
				"Kishore@123");
				PreparedStatement ps = con.prepareStatement("SELECT * FROM PERSON WHERE NUM= ?");
				Scanner sc = new Scanner(System.in)) {
			int num = 0;

			if (sc != null) {
				System.out.print("Please enter person number.. ");
				num = sc.nextInt();

				if (ps != null)
					ps.setInt(1, num);

				try (ResultSet rs = ps.executeQuery()) {
					rs.next();

					try (FileOutputStream fos = new FileOutputStream(rs.getString(2)+" pic.png");
							FileWriter fr = new FileWriter("profile.txt");) {
						IOUtils.copy(rs.getBinaryStream(3), fos);
						IOUtils.copy(rs.getCharacterStream(4), fr);

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
