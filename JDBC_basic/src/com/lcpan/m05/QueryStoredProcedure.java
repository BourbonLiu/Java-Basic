package com.lcpan.m05;

import java.sql.*;

public class QueryStoredProcedure {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";

	private static final String SQL = "{call qry_emp(?,?,?)}"; // 第一個問號是in 後二個問號是out

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			CallableStatement cstmt = conn.prepareCall(SQL);
			cstmt.setString(1, "1001");

			// rigisterOutParameter是一個檢查機制，回傳的值必須符合所設定的資料型態才能接收
			// 這些資料型態在API的java.sql.Types
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.execute();
			System.out.print("name = " + cstmt.getString(2));
			System.out.println(", salary = " + cstmt.getInt(3));

			cstmt.close();
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
	}// end of main()
}// end of class StoredProcedureDemo
