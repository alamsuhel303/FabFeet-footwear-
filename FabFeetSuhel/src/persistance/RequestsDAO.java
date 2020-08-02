package persistance;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Requests;


public class RequestsDAO{

    public static void insertToRequests(Requests req){
        Connection con;
        con = ConnectToDB.getConnection();
		String sql=" insert into orcluser.requests (branchId, requestMsg) values (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, req.getBranchId());
            ps.setString(2, req.getRequestMsg());
            

			int row= ps.executeUpdate();
			if(row<0)   
				System.out.println("request insert failed");
			else {
				System.out.println("request Inserted Sucessfully");
			}
		} catch (SQLException e) {
			System.out.println("Error while insertingToRequest");
			e.printStackTrace();
		}

        ConnectToDB.closeConnection(con);
    }


    // TODO check and verify  requests display all
    public static void displayAll(){
        Connection con;
        con = ConnectToDB.getConnection();
		String sql=" select * from orcluser.requests ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			if(rs.next()==false)   
				System.out.println("No requests exist");
			else {
					Requests req= new Requests();
				do 
				{
                  	
					req.setRequestId(rs.getInt("requestId"));
                    req.setBranchId(rs.getInt("branchId"));
                    req.setRequestMsg(rs.getString("requestMsg"));
                    req.display();

				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("Error while  displayAll requests");
			e.printStackTrace();
		}

        ConnectToDB.closeConnection(con);     
    }


    // TODO check and verify  requests displayAllByProductName 
    public static void displayAllByBranchID(int BID){
        Connection con;
        con = ConnectToDB.getConnection();
		String sql=" select * from orcluser.requests where branchid = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, BID);
			ResultSet rs= ps.executeQuery();
			if(rs.next()==false)   
				System.out.println("No requests exist from branch "+BID);
			else {
					Requests req= new Requests();
				do 
				{
					req.setRequestId(rs.getInt("requestId"));
                    req.setBranchId(rs.getInt("branchId"));
                    req.setRequestMsg(rs.getString("requestMsg"));
                    req.display();

				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("Error while  displayAllbyBranchId requests");
			e.printStackTrace();
		}

        ConnectToDB.closeConnection(con);     
    }

    // TODO Test using this
    // 	public static void main(String[] args) {

 	//    ReviewsDAO.displayAll();
 	//    String Pname ="Shoe";
 	//    ReviewsDAO.displayAllByProductName(Pname);
    // }
    
}