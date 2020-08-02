package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductsDAO {

	public static void addProduct(String name)
	{
		Connection con = ConnectToDB.getConnection();
		String sql="insert into orcluser.products(productname) values(?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			int row= ps.executeUpdate();
			if(row<0)
				System.out.println("didnt inserted into the products table");
			else
				System.out.println("inserted successful into the products table");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectToDB.closeConnection(con);
	}
	public static void showProducts() {
		Connection con=ConnectToDB.getConnection();
		String sql="select productname from orcluser.products";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()==false)
				System.out.println("No products exist");
			else
			{		System.out.println("Name of the products");
				do {
					System.out.println(rs.getString("productname"));
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectToDB.closeConnection(con);
	}
	
	
}
