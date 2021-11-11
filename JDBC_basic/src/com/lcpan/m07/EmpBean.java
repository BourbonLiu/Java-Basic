package com.lcpan.m07;

public class EmpBean {
	private int empno;
	private String ename;
	private String hiredate;
	private int salary;
	private int deptno;
	private String title;

	public int getEmpno() {
		return empno;
	}

	public String getEname() {
		return ename;
	}

	public String getHiredate() {
		return hiredate;
	}

	public int getSalary() {
		return salary;
	}

	public int getDeptno() {
		return deptno;
	}

	public String getTitle() {
		return title;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public void setTitle(String title) {
		this.title = title;
	}
} // end of class EmpBean
