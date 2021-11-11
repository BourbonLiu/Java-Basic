package com.lcpan.m02;

import java.sql.*;

public class QueryDemo3 {
//	���F�����@�ҥH��@�Ǳ`�ƩԥX�ӡA�]���O�`�ƩҥH�[�Wfinal
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";

	private static final String SQL = "SELECT ename, salary FROM employee";

	public static void main(String[] args) {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		��connection�g�btry���Ajava�|�۰ʧ⥦close
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
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
		}

	}
}
