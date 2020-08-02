package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.Inventory;

public class InventoryDAO {
	
	static Scanner scan= new Scanner(System.in);
	public static void displayAllStock()
	{
		Connection con= ConnectToDB.getConnection();
		String sql ="select * from orcluser.inventory ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()==false)
				System.out.println(" no products in the inventory");
			else
			{
				do {
						Inventory item = new Inventory();
						item.setProductId(rs.getInt("productId"));
						item.setBranchId(rs.getInt("branchId"));
						item.setSize(rs.getInt("sizes"));
						item.setPrice(rs.getDouble("price"));
						item.setQuantity(rs.getInt("quantity"));
						item.display();
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("products cant be displayed in the inventoryDAO");
			e.printStackTrace();
		}
		ConnectToDB.closeConnection(con);
	}
	
	public static void displayStockById(int bid)
	{
		Connection con=ConnectToDB.getConnection();
		String sql="select * from orcluser.inventory where branchid = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()==false)
				System.out.println(" no products in the inventory of your branch");
			else
			{
				do {
						Inventory item = new Inventory();
						item.setProductId(rs.getInt("productId"));
						item.setBranchId(bid);
						item.setSize(rs.getInt("sizes"));
						item.setPrice(rs.getDouble("price"));
						item.setQuantity(rs.getInt("quantity"));
						item.display();
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("products cant be displayed in the inventoryDAO");
			e.printStackTrace();
		}
		ConnectToDB.closeConnection(con);
	}
	
	
	public static void insertIntoInventory(int bid)
	{
		Connection con=ConnectToDB.getConnection();
		System.out.println("enter the product id");
		int id=scan.nextInt();
		String sql="insert into orcluser.inventory values(?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, id);
			
//			System.out.println("enter the branch id");
//			ps.setInt(2, scan.nextInt());
			ps.setInt(2, bid);
			
			System.out.println("enter the size");
			ps.setInt(3, scan.nextInt());
			
			System.out.println("enter the quantity");
			ps.setInt(4, scan.nextInt());
			
			System.out.println("enter the price");
			ps.setDouble(5,scan.nextDouble() );
			
			ps.executeUpdate();
			System.out.println("Sucessfully inserted into Inventory");
		} catch (SQLException e) {
		
			System.out.println("productID does not exist in our store");
			System.out.println("enter 1 to enter the product into the store with different product id ");
			int i=scan.nextInt();
			if(i==1)
			{
				System.out.println("enter the name of the product");
				String name=scan.next();
				ProductsDAO.addProduct(name);
				
			}
				
		}
		ConnectToDB.closeConnection(con);
		
	}
	
	
	public static void getProductbyName(String name)
	{
		Connection con = ConnectToDB.getConnection();
		String sql="select i.productid,i.branchid,i.sizes,i.price,i.quantity,p.productname  from orcluser.inventory i, orcluser.products p where i.productid=p.productid and lower(productName) like '%"+name+"%'";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()==false)
				System.out.println(" no product found by the name :"+name);
			else
			{
				do {
					System.out.println("");
					System.out.println(rs.getString("productname"));
					Inventory item = new Inventory();
					item.setProductId(rs.getInt("productId"));
					item.setBranchId(rs.getInt("branchId"));
					item.setSize(rs.getInt("sizes"));
					item.setPrice(rs.getDouble("price"));
					item.setQuantity(rs.getInt("quantity"));
					item.display();
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(" cant display the iventory items by product name");
			e.printStackTrace();
		}
		
	}
	public static  boolean getProductbyIdAndBranch(int id,int bid)
	{
		Connection con = ConnectToDB.getConnection();
		String sql="select * from orcluser.inventory  where productid=? and branchId=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, bid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()==false) {
				System.out.println(" no product found by the product id :"+id+" in your branch");
				return false;
			}else
			{
				do {
					Inventory item = new Inventory();
					item.setProductId(rs.getInt("productId"));
					item.setBranchId(rs.getInt("branchId"));
					item.setSize(rs.getInt("sizes"));
					item.setPrice(rs.getDouble("price"));
					item.setQuantity(rs.getInt("quantity"));
					System.out.println(item.toString());
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println(" cant display the inventory items by productid and size");
			e.printStackTrace();
		}
		
		ConnectToDB.closeConnection(con);
		return true;
		
	}
	
	public static boolean sizeavilable(int pid,int bid,int size) {
		boolean flag=false;
		Connection con= ConnectToDB.getConnection();
		String sql="select sizes from orcluser.inventory where productid=? and branchid=? and sizes=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, pid);
			ps.setInt(2, bid);
			ps.setInt(3, size);
			ResultSet rs=ps.executeQuery();
			if(rs.next()!=false)
				flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConnectToDB.closeConnection(con);
		return flag;
	}
	public static boolean quantityavailable(int pid,int bid,int size,int quant) {
		boolean flag=false;
		Connection con= ConnectToDB.getConnection();
		String sql="select quantity from orcluser.inventory where productid=? and branchid=? and sizes=? ";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, pid);
			ps.setInt(2, bid);
			ps.setInt(3, size);
			//ps.setInt(4, quant);
			ResultSet rs=ps.executeQuery();
			if(rs.next()!=false) {
				//System.out.println("rs is valid");
				if(rs.getInt("quantity")>=quant) {
					//System.out.println("the quantity is "+rs.getInt("quantity"));
					flag=true;
					//System.out.println("flag value"+flag);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ConnectToDB.closeConnection(con);
		return flag;
		
	}
	public static void updateInventory(int pid,int bid,int size,int quantity){
		Connection con = ConnectToDB.getConnection();
		String sql="update orcluser.inventory set quantity = -? where  productId = ? and branchId= ? and sizes = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setInt(2, pid);
			ps.setInt(3, bid);
			ps.setInt(4, size);
			int row=ps.executeUpdate();
			if (row>0){
				System.out.println("Inventory updated ");
			}
			else{
				System.out.println("Error while updating Inventory");
			}
		} catch (SQLException e) {
			System.out.println(" cant display the iventory items by product name");
			
		}
		ConnectToDB.closeConnection(con);     
	}
//	public static void main(String[] args)
//	{
//		System.out.println(quantityavailable(1, 1, 20, 100));
//		System.out.println(quantityavailable(1, 1, 20, 10));
//	}
	


	
	
}
