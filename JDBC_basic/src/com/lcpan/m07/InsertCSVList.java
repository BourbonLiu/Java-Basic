package com.lcpan.m07;

import java.io.*;
import java.sql.*;
import java.util.*;

public class InsertCSVList {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";
	private static final String SQL = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?)";

	public static List<EmpBean> readFromCSV(String fileName) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		List<EmpBean> emps = new ArrayList<>();
		String row;
		String[] cols;
		EmpBean bean;
		while ((row = in.readLine()) != null) {
			cols = row.split(",");
			bean = new EmpBean();
			bean.setEmpno(Integer.parseInt(cols[0]));
			bean.setEname(cols[1]);
			bean.setHiredate(cols[2]);
			bean.setSalary(Integer.parseInt(cols[3]));
			bean.setDeptno(Integer.parseInt(cols[4]));
			bean.setTitle(cols[5]);
			emps.add(bean);
		}
		in.close();
		return emps;
	}

	public static void writeToDB(List<EmpBean> emps) throws SQLException {
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		for (EmpBean emp : emps) {
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getHiredate());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getDeptno());
			pstmt.setString(6, emp.getTitle());
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		pstmt.close();
		conn.close();
	}

	public static void main(String[] args) throws Exception {
		String fileName = "res\\empdata.csv";
		writeToDB(readFromCSV(fileName));
	}
}
