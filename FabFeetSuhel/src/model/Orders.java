package model;

import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import persistance.CustomerDAO;
import persistance.InventoryDAO;
import persistance.OrdersDAO;

public class Orders {

	private int orderId;
	private int customerId;
	private int productId;
	private int branchId;
	private int size;
	private int quantity;
	private double amount;
	private String orderDate;
	static BufferedReader br;
	static {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getbranchId() {
		return branchId;
	}

	public void setbranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void display() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", customerId=" + customerId
				+ ", productId=" + productId + ", branchId=" + branchId
				+ ", size=" + size + ", quantity=" + quantity + ", amount="
				+ amount + ", orderDate=" + orderDate + "]";
	}



	public static void placeOrder(int bid){
		
		int orderId = 0;
		int customerId;
		int productId;
		int branchId;
		int size;
		int quantity;
		double amount;
		String orderDate;
		
		try {
			Orders order = new Orders();

			System.out.println("Enter Customer Id");
			customerId = Integer.parseInt(br.readLine());
			if(!CustomerDAO.customerExists(customerId))
			{
				System.out.println("customer does not exist");
				return;
			}
			System.out.println("Enter Product Id");
			productId =  Integer.parseInt(br.readLine());
			
			// System.out.println("Enter Branch Id");
			//branchId =  Integer.parseInt(scan.readLine());
			branchId = bid;
			System.out.println("showing the available sizes quantity and the poduct");
			if(!InventoryDAO.getProductbyIdAndBranch(productId, bid))
				return;
			
			size= checksize(productId,bid,0);
			if(size==0) {
				System.out.println("Order cancelled sicnce size is not available");
				return;
			}
//			System.out.println("Enter the quantity");
//			quantity =Integer.parseInt(br.readLine());
//			System.out.println(productId+" "+bid+" "+ size +" "+ quantity);
//			if(!InventoryDAO.quantityavailable(productId, bid, size, quantity))
//			{
//				System.out.println("quantity not available in the store");
//				return;
//			}

			
		
		quantity = checkquantity(productId, bid, size, 0);
		if(quantity==0)
			return;


			System.out.println("Enter Product Cost");
			amount = Integer.parseInt(br.readLine());
			
			
			
			
			order.setOrderId(orderId);
			order.setCustomerId(customerId);
			order.setProductId(productId);
			order.setbranchId(branchId);
			order.setSize(size);
			order.setQuantity(quantity);
			order.setAmount(amount);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Calendar cal = Calendar.getInstance();
			String date=sdf.format(cal.getTime());
			//System.out.println(date);
			order.setOrderDate(date);
			
			
			
			//order.setOrderDate(orderDate);
			
			OrdersDAO.placeOrder(order);

			// order.display();

			

		} catch (Exception e) {
			System.out.println("Unable to place order");
			e.printStackTrace();
			
		}

	}

	public static void displayAllOrders() {
		OrdersDAO.displayAllOrdersD();
		return;
	}

	public static void displayBranchOrders(int bid) {
		OrdersDAO.displayByBranch(bid);

		return;
	}
	public  void generateBill() {
		System.out.println("Bill for your order");
		System.out.println( "Bill ["+ " customerId=" + customerId
				+ ", productId=" + productId );
		System.out.println(" branchId=" + branchId
				+ ", size=" + size + ", quantity=" + quantity + ",  product amount amount="
				+ amount +" Total price "+quantity*amount +" orderDate=" + orderDate  +"]");
		
	}
	public static int checksize(int pid,int bid,int size)
	{
		try {
		System.out.println("Enter Product Size");
		size = Integer.parseInt(br.readLine());
		
		//check for the available sizes
		if(!InventoryDAO.sizeavilable(pid, bid, size))
		{
			System.out.println(" the Entered size is not available");
			System.out.println(" If you want to select from the available sizes enter 1 or others to cancel the order");
			int i=Integer.parseInt(br.readLine());
			if(i==1)
			{
				System.out.println("enter from the available sizes");
				InventoryDAO.getProductbyIdAndBranch(pid, bid);
				size=checksize(pid,bid,size);
			}
			else
				return 0;

		}
		
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return size;
	}
	public static int checkquantity(int pid,int bid,int size,int quant) {

		
		try {
			System.out.println("Enter Product Quantity");
			  quant = Integer.parseInt(br.readLine());
			if(!InventoryDAO.quantityavailable(pid, bid, size, quant))
			{
				System.out.println("The Entered quantity is not availabe in the store");
				System.out.println(" If you want to select from the available quantity enter 1 or others to cancel the order");
				int i=Integer.parseInt(br.readLine());
				if(i==1) {
					System.out.println("enter from the available quantity");
					quant=checkquantity(pid, bid, size, quant);
				}
				else
					return 0;
				//System.out.println("tne entered quantity is "+quant);
				
			}
		 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return quant;
	}

	
//	public static void main(String[] args) {
//		Inventory.displayBranchStock(1);
//		placeOrder(1);
//		
//	}

}
