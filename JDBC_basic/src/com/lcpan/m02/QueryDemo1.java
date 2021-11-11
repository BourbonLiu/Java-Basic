package com.lcpan.m02;

import java.sql.*;

public class QueryDemo1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
//			�]���Τ���Class.forName���Ǧ^�ȡA�ҥH�S���Τ@���ܼƥh��
//			forName�̭����r��bconnector/j.jar �� META-INF �� services �� java.sql.Driver��
			Class.forName("com.mysql.cj.jdbc.Driver");    // �|�ߥXClassNotFoundException
//			url�r��@�w�n��jdbc:DBname:hostname:portNB(3306�N��mysql)
			String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";  
			conn = DriverManager.getConnection(url, "allen", "allen"); // �|�ߥXSQLException

			String sql = "SELECT ename, salary FROM employee";
			Statement stmt = conn.createStatement();      // �|�ߥXSQLException
			ResultSet rs = stmt.executeQuery(sql);        // �|�ߥXSQLException executeQuery�Φbselect

			while (rs.next()) {
				System.out.print("name = " + rs.getString("ename")); 
				System.out.println(", salary = " + rs.getInt("salary"));
			}
			
//          getString���Ѽƥi�H�Oparameter name�]�i�H�Oparameter index
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
//			�]����Ʈw�s�u������A�Χ�����close����귽
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
