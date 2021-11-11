package com.lcpan.m07;

import java.sql.*;

public class BatchUpdatePreStatement {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";

	private static final String SQL = "UPDATE employee SET salary = ? WHERE empno = ?";

	public static void main(String[] args) {
		Connection conn = null;
		
		// 透過batch可以減少對資料庫的反覆連線，不過在中途出現問題後面的動作會不會繼續執行要看driver的設定
		// 使用batch可以下達的指令為insert,update不可以是delete,select
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, 70000);
			pstmt.setString(2, "1004");
			pstmt.addBatch();
			pstmt.setInt(1, 40000);
			pstmt.setString(2, "1005");
			pstmt.addBatch();
			pstmt.setInt(1, 47000);
			pstmt.setString(2, "1006");
			pstmt.addBatch();
			pstmt.executeBatch();

			pstmt.close();
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
