package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.Orders;

public class OrdersDAO {
	
	
	Scanner scan=new Scanner(System.in);
	
	public static void displayAllOrdersD() {
		Connection con;
		 
		
		con = ConnectToDB.getConnection();
		String sql=" select * from orcluser.orders";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			if(rs.next()==false)
				System.out.println("No orders exist");
			else {
//					System.out.print("Order_ID  Customer_ID  Product_Id  Branch_Id  OrderDate  ");
//					System.out.println(" Size   Quantity   Amount ");
				do 
				{
					Orders o = new Orders();
				  	o.setOrderId(rs.getInt("orderid"));
				  	o.setCustomerId(rs.getInt("customerid"));
				  	o.setProductId(rs.getInt("productid"));
				  	o.setbranchId(rs.getInt("branchid"));
				  	o.setOrderDate(rs.getString("orderdate"));
				  	o.setSize(rs.getInt("sizes"));
				  	o.setQuantity(rs.getInt("quantity"));
				  	o.setAmount(rs.getDouble("amount"));
				  	o.display();
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(" place order in ordersDAO not working");
			e.printStackTrace();
		}
		ConnectToDB.closeConnection(con);
		
	}
	
	public static void displayByBranch(int bid)
	{
		Connection con= ConnectToDB.getConnection();
		String sql="select * from orcluser.orders where branchid = ?";
		try {
			
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, bid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()==false)
				System.out.println("No orders in your branch");
			else
			{
				do {
					Orders o = new Orders();
				  	o.setOrderId(rs.getInt("orderid"));
				  	o.setCustomerId(rs.getInt("customerid"));
				  	o.setProductId(rs.getInt("productid"));
				  	o.setbranchId(rs.getInt("branchid"));
				  	o.setOrderDate(rs.getString("orderdate"));
				  	o.setSize(rs.getInt("sizes"));
				  	o.setQuantity(rs.getInt("quantity"));
				  	o.setAmount(rs.getDouble("amount"));
				  	o.display();
					
				}while(rs.next());
			}
		} catch (SQLException e) {
			
			System.out.println(" place order in ordersDAO not working");
			e.printStackTrace();
		}
		ConnectToDB.closeConnection(con);
	}
	
	public static void placeOrder(Orders ord)
	{
		Connection con = ConnectToDB.getConnection();
		String sql="insert into orcluser.orders(customerid,productid,branchid,sizes,quantity,amount,orderdate) values(?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'))";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ord.getCustomerId());	
		  	ps.setInt(2, ord.getProductId());
		  	ps.setInt(3, ord.getbranchId());
		  	ps.setInt(4, ord.getSize());
		  	ps.setInt(5, ord.getQuantity());
		  	ps.setDouble(6, ord.getAmount());
		  	ps.setString(7, ord.getOrderDate());
			


		  	int res=ps.executeUpdate();
			if(res>0) {
				System.out.println(" order placed ");
				ord.generateBill();
				InventoryDAO.updateInventory(ord.getProductId(), ord.getbranchId(), ord.getSize(), ord.getQuantity());
			}
			else
				System.out.println("order can not be processed");
		} catch (SQLException e) {
			//System.out.println(" place order in ordersDAO not working");
			System.out.println("Customer ID not valid");
			e.printStackTrace();
		}
		ConnectToDB.closeConnection(con);
	}

}
