package com.lcpan.m08;

import java.sql.*;
import javax.sql.DataSource;

public class ConnectionPoolingDemo {
	private static final String SQL = "SELECT ename, salary FROM employee";

	public static void main(String[] args) {
		Connection conn = null;
		
		// 使用connection pool可以減少反覆對資料庫進行連線，可以大幅增加效率，也可以限制連線數
		try {
			DataSource dataSource = DataSourceFactory.getDataSource();
			conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				System.out.print("name = " + rs.getString("ename"));
				System.out.println(", salary = " + rs.getInt("salary"));
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
