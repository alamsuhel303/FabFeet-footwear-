package persistance;
import java.sql.*;

public class ConnectToDB{
    
	public static Connection getConnection(){
		
		Connection connect=null;
		
		try {
			connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/pdborcl","sys as sysdba","Welcome123");
			
			if(connect!=null) {
				//System.out.println("Connected to database");
				connect.setAutoCommit(true);
			}
			else
				System.out.println("Connection failed");
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connect;
    	
        
    }
	
	public static void closeConnection(Connection c)
	{
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
//    public static void main(){
//    	getConnection();
//
//    }
}