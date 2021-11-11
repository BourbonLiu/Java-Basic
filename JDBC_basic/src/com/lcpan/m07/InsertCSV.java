package com.lcpan.m07;

import java.io.*;
import java.sql.*;

public class InsertCSV {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";
	private static final String SQL = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL2 = "Select * from employee";
	
	public static void CSVToDB(String fileName,String fileName2) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			Statement tmt = conn.createStatement();
			
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName2));
			
			String row;
			String[] cols;
			while ((row = in.readLine()) != null) {
				cols = row.split(",");
				for (int i = 0; i < cols.length; i++) {
					pstmt.setString(i + 1, cols[i]);
				}
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			
			in.close();
			pstmt.close();	
			System.out.println("Insert finish!");
			
			ResultSet rs = tmt.executeQuery(SQL2);
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();
			
			while(rs.next()) {
				for(int i = 1; i<=col; i++) {
					out.write(rs.getString(i)+",");			
				}
				out.write("\n");
			}
			out.flush();
			out.close();
			rs.close();
			tmt.close();
			System.out.println("Write finish!");

			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	

	public static void main(String[] args) {
		String fileName = "res/empdata.csv";
		String fileName2 = "res/empdata2.csv";
		CSVToDB(fileName,fileName2);
	}
}
