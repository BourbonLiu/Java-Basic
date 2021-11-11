package com.lcpan.m02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DBtoFile {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc?serverTimeZone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";
	private static final String CMD = "select * from employee where deptno = ? and title = ?";

		
	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("res/output.txt"));
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement prst = conn.prepareStatement(CMD);
			int deptno = 100;
			String title = "engineer";
			prst.setInt(1, deptno);
			prst.setString(2, title);
			ResultSet rs = prst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int col = rsmd.getColumnCount();					
			
			while(rs.next()) {
				for(int i = 1; i <= col; i++) {
					out.write(rs.getString(i) + ",");
				}
				out.write("\n");
			}
			
			out.flush();
			out.close();
			rs.close();
			prst.close();
			System.out.println("write finish !");
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		

	}
}
