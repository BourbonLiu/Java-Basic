package com.lcpan.m08;

import java.sql.*;
import javax.sql.DataSource;

public class ConnectionPoolingDemo {
	private static final String SQL = "SELECT ename, salary FROM employee";

	public static void main(String[] args) {
		Connection conn = null;
		
		// �ϥ�connection pool�i�H��֤��й��Ʈw�i��s�u�A�i�H�j�T�W�[�Ĳv�A�]�i�H����s�u��
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
