package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import persistance.ProductsDAO;
import persistance.ReviewsDAO;

public class Reviews {
	
	private int reviewId;
	private String customerName;
	private String productName;
	private String review;
	static BufferedReader br ;
	static {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
			System.out.println("Error in Reviews, Buffer initialization");
		}
	}

	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "Reviews [reviewId=" + reviewId + ", customerName=" + customerName + ", productName=" + productName
				+ ", review=" + review + "]";
	}

	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public void display(){
		System.out.println(this.toString());
	}

	public static void writeReview(){
		int reviewId=0;
		String customerName="Anonymous";
		String productName="";
		String review="";
		try {
			Reviews rev = new Reviews();

			System.out.println("Enter Your Name");
			customerName = Reviews.br.readLine();
			
			ProductsDAO.showProducts();
			System.out.println();
			System.out.println("Enter Product Name from the above list of Products");
			productName = Reviews.br.readLine();

			System.out.println("Enter Review");
			review = Reviews.br.readLine();
			


			rev.setReviewId(0);
			rev.setCustomerName(customerName);
			rev.setProductName(productName);
			rev.setReview(review);

			ReviewsDAO.insertToReviews(rev);
		} catch (Exception e) {
			System.out.println("Error while adding review");
		}
		

	}

	
	public static void checkReviewbyName()
	{
		ProductsDAO.showProducts();
		System.out.println();
		System.out.println("Enter the product name from the above list to check the reviews");
		try {
			String Pname=br.readLine();
			ReviewsDAO.displayAllByProductName(Pname);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	
	public static void displpayAllReviews()
	{
		
		ReviewsDAO.displayAll();
		
		
		return;
	}

	

}
