package model;

import persistance.EmployeesDAO;

public class Employees {
	 
	@Override
	public String toString() {
		return "Employees [employeeId=" + employeeId + ", employeeName=" + employeeName + ", designation=" + designation
				+ ", branchId=" + branchId + "]";
	}

	private int employeeId;
	private String employeeName;
	private String designation;
	private int branchId;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void display(){
		System.out.println(this.toString());
	}

	public static void displayAllEmployee() {

		EmployeesDAO.displayAll();
		return;
	}
	
	public static void displayAllByBranchID(int bid) {
		
		EmployeesDAO.displayAllByBranchID(bid);
		return;
	}
	



	 
	
	
}
