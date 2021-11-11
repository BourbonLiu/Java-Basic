package com.lcpan.m06;

import java.sql.*;

public class Transaction {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";
	private static final String SQL = "INSERT INTO department VALUES (?, ?)";

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			// Java�u�䴩Implicit Transaction���䴩Explicit Transaction
			// mySQL�w�]AutoCommit�O���}�A�ҥH�n����Implicit Transaction�e�n����L����
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, 401);
			pstmt.setString(2, "Sales");
			pstmt.executeUpdate(); // executeUpdate�Φbinsert,update,delete
			pstmt.setInt(1, 402);
			pstmt.setString(2, "Service");
			pstmt.executeUpdate();
			pstmt.setInt(1, 403);
			pstmt.setString(2, "Production");
			pstmt.executeUpdate();
			conn.commit();
			
			System.out.println("Transaction complete1 !");
			
			pstmt.setInt(1, 404);
			pstmt.setString(2, "Sales1");
			pstmt.executeUpdate();
			pstmt.setInt(1, 402);  // �]��primary key���ơA�ҥH404,402,406�o������|�Q����
			pstmt.setString(2, "Service2");
			pstmt.executeUpdate();
			pstmt.setInt(1, 406);
			pstmt.setString(2, "Production2");
			pstmt.executeUpdate();
			conn.commit();

			System.out.println("Transaction complete2 !");
			
			// ����Implicit Transaction��n����AutoCommit����
			conn.setAutoCommit(true);

			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				System.err.println("Transaction is being rolled back");
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
