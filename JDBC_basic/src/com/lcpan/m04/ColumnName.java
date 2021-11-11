package com.lcpan.m04;

import java.sql.*;

public class ColumnName {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";

	private static final String SQL = "SELECT * FROM employee";
	private static final String SQL2 = "SELECT empno as number, ename as name FROM employee";
	
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String colName = rsmd.getColumnName(i);
				System.out.println("column name" + i + " = " + colName);
			}
			
			System.out.println();
			
			rs = stmt.executeQuery(SQL2);
			rsmd = rs.getMetaData();
			count = rsmd.getColumnCount();
			for(int i=1; i<=count; i++) {
				String colLabel = rsmd.getColumnLabel(i);  // getColumnLabel�|�^�ǧO�W
				System.out.println("column name" + i + "=" + colLabel);
			}
					
			rs.close();
			stmt.close();
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
}
