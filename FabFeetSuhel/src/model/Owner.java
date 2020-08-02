package model;

import persistance.EmployeesDAO;
import persistance.InventoryDAO;
import persistance.OwnerDAO;

public class Owner {
	private int ownerId;
	private String password;

	public static void displayAllOrders() {
		Orders.displayAllOrders();
		return;
	}

	public static void displayBranchOrders(int bid) {
		Orders.displayBranchOrders(bid);
		return;
	}

	public static void displayAllStock() {
		Inventory.displayAllStock();
		return;
	}

	public static void displayBranchStock(int bid) {
		// call the dislayBrnachStockdao
		Inventory.displayBranchStock(bid);
		return;
	}

	public static void displayEmployee() {

		Employees.displayAllEmployee();
		return;
	}

	public static void displayAllByBranchID(int bid) {

	
		Employees.displayAllByBranchID(bid);
		return;
	}

	public static void displayRequest(int bid) {
		// call the displayRequestdao with bid;
		Requests.displayRequestByBranch(bid);
		return;

	}

	public static void displayAllReview() {
		// call the displayFeedbackdao
		Reviews.displpayAllReviews();
		return;
	}
	
	public static void checkReviewbyName() {
		Reviews.checkReviewbyName();
		return;
	}

//	public static void main(String[] args) {
//		Owner on = new Owner();
////		Employee test
////		on.displayEmployee();
////		on.displayAllByBranchID(4);
//		
////		on.displayAllStock();
//		on.displayBranchStock(3);
//
//	}
	
	public static boolean validate(int id,String pass){
		boolean flag = OwnerDAO.validate(id, pass);
		return flag;
	}

}
