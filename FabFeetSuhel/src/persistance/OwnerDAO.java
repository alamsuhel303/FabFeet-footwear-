package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerDAO {
		public static boolean validate(int id,String pass)
		{
			boolean flag=false;
			String sql ="select password from orcluser.owner where ownerId = ?";
			Connection con;
			con=ConnectToDB.getConnection();
			try {
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next()==false) {
					System.out.println("ID Does not exist");
				}
				else
				{
					String pass1=rs.getString("password");
					if(pass1.equalsIgnoreCase(pass))
						flag=true;
				}
			} catch (SQLException e) {
				System.out.println("Error in OWNER during  Validation");
				e.printStackTrace();
			}
			
			
			ConnectToDB.closeConnection(con);
			return flag;
			
		}
		

}
