package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import persistance.CustomerDAO;

public class Customers {

	private int customerId;
	private String customerName;
	private String phoneNo;
	private String address;
	static BufferedReader br;
	static {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void display() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "Customers [customerId=" + customerId + ", customerName="
				+ customerName + ", phoneNo=" + phoneNo + ", address="
				+ address + "]";
	}

	public static void addCustomer() {
		try {
			Customers cust = new Customers();
			// System.out.println("Enter Customer ID");
			cust.setCustomerId(0);

			System.out.println("Enter Customer Name");
			cust.setCustomerName(br.readLine());

			System.out.println("Enter Customer Phone Number");
			cust.setPhoneNo(br.readLine());

			System.out.println("Enter Customer Address");
			cust.setAddress(br.readLine());

			// cust.display();
			CustomerDAO.insertIntoCustomersTable(cust);

		} catch (Exception e) {
			System.out.println("Buffer Read error");
			e.printStackTrace();
		}

	}

	public static void productSearch() {
		
		Inventory.productSearch();
		

		return;
	}

	public static void checkReviewbyName() {
		Reviews.checkReviewbyName();
		return;
	}
	//
	public static void writeReview() {
		Reviews.writeReview();
	}

	// public static void main(String[] args) {
	// Customers cust = new Customers();
	//
	//
	// }

}