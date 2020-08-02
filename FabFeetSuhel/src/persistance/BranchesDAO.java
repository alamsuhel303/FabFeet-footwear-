package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchesDAO {
	
	
	public boolean validate(int bid,String pass)
	{
		boolean flag=false;
		Connection con;
		String sql="select managerPassword from orcluser.Branches where branchid = ?";
		con= ConnectToDB.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,bid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()==false)
				System.out.println("Id does not exist");
			else
			{
				String pass1=rs.getString("managerPassword");
				if(pass.equalsIgnoreCase(pass1))
					flag=true;
			}
				
		} catch (SQLException e) {
			System.out.println("Error in BranchDAO validate");
			e.printStackTrace();
		}
		
		ConnectToDB.closeConnection(con);
		return flag;
	}
	public static void main(String[] args)
	{
		BranchesDAO obj = new BranchesDAO();
		 
		
		System.out.println(obj.validate(1, "apple"));
		System.out.println(obj.validate(1, "appple"));
		System.out.println(obj.validate(100, "appple"));
	}
	
}
