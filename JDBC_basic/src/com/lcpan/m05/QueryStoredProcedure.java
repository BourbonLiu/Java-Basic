package com.lcpan.m05;

import java.sql.*;

public class QueryStoredProcedure {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";

	private static final String SQL = "{call qry_emp(?,?,?)}"; // �Ĥ@�Ӱݸ��Oin ��G�Ӱݸ��Oout

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			CallableStatement cstmt = conn.prepareCall(SQL);
			cstmt.setString(1, "1001");

			// rigisterOutParameter�O�@���ˬd����A�^�Ǫ��ȥ����ŦX�ҳ]�w����ƫ��A�~�౵��
			// �o�Ǹ�ƫ��A�bAPI��java.sql.Types
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
