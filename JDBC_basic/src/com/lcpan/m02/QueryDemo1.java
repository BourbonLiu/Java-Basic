package com.lcpan.m02;

import java.sql.*;

public class QueryDemo1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
//			因為用不到Class.forName的傳回值，所以沒有用一個變數去接
//			forName裡面的字串在connector/j.jar → META-INF → services → java.sql.Driver裡
			Class.forName("com.mysql.cj.jdbc.Driver");    // 會拋出ClassNotFoundException
//			url字串一定要有jdbc:DBname:hostname:portNB(3306代表mysql)
			String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";  
			conn = DriverManager.getConnection(url, "allen", "allen"); // 會拋出SQLException

			String sql = "SELECT ename, salary FROM employee";
			Statement stmt = conn.createStatement();      // 會拋出SQLException
			ResultSet rs = stmt.executeQuery(sql);        // 會拋出SQLException executeQuery用在select

			while (rs.next()) {
				System.out.print("name = " + rs.getString("ename")); 
				System.out.println(", salary = " + rs.getInt("salary"));
			}
			
//          getString的參數可以是parameter name也可以是parameter index
//			while (rs.next()) {
//				System.out.print("name = " + rs.getString(1)); 
//				System.out.println(", salary = " + rs.getString(2));
//			}
			
			rs.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
//			因為資料庫連線有限制，用完必須close釋放資源
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
