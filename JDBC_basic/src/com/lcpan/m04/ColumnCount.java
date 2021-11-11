package com.lcpan.m04;

import java.sql.*;

public class ColumnCount {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";

	private static final String SQL1 = "SELECT ename, salary FROM employee";
	private static final String SQL2 = "SELECT * FROM employee";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL1);  // resultset回傳的資料除了表格內容還有表格的架構
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			System.out.println("column count = " + count);

			rs = stmt.executeQuery(SQL2);
			rsmd = rs.getMetaData();
			count = rsmd.getColumnCount();
			System.out.println("table column count = " + count);

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
