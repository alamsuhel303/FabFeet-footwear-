package persistance;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Reviews;


public class ReviewsDAO{

    public static void insertToReviews(Reviews rev){
        Connection con;
        con = ConnectToDB.getConnection();
		String sql=" insert into orcluser.reviews (customerName, productName ,review) values (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, rev.getCustomerName());
            ps.setString(2, rev.getProductName());
            ps.setString(3, rev.getReview());

			int row= ps.executeUpdate();
			if(row<0)   
				System.out.println("Review insert failed");
			else {
				System.out.println("Review Inserted Sucessfully");
			}
		} catch (SQLException e) {
			System.out.println("Error while insertingToReviews");
			e.printStackTrace();
		}

        ConnectToDB.closeConnection(con);
    }


    // TODO check and verify  review display all
    public static void displayAll(){
        Connection con;
        con = ConnectToDB.getConnection();
		String sql=" select * from orcluser.reviews ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			if(rs.next()==false)   
				System.out.println("No reviews exist");
			else {
					Reviews rev= new Reviews();
				do 
				{
					rev.setReviewId(rs.getInt("reviewId"));
                    rev.setProductName(rs.getString("productName"));
                    rev.setCustomerName(rs.getString("customerName"));
                    rev.setReview(rs.getString("review"));
                    rev.display();

				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("Error while  displayAll");
			e.printStackTrace();
		}

        ConnectToDB.closeConnection(con);     
    }


    // TODO check and verify  review displayAllByProductName 
    public static void displayAllByProductName(String Pname){
        Connection con;
        con = ConnectToDB.getConnection();
		String sql=" select * from orcluser.reviews where lower(productName) like ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%"+Pname+"%");
			ResultSet rs= ps.executeQuery();
			if(rs.next()==false)   
				System.out.println("No reviews exist for product "+Pname);
			else {
					Reviews rev= new Reviews();
				do 
				{
					rev.setReviewId(rs.getInt("reviewId"));
                    rev.setProductName(rs.getString("productName"));
                    rev.setCustomerName(rs.getString("customerName"));
                    rev.setReview(rs.getString("review"));
                    rev.display();

				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("Error while  displayAllByProductName");
			e.printStackTrace();
		}

        ConnectToDB.closeConnection(con);     
    }

    // TODO Test using this
    	public static void main(String[] args) {

 	   //ReviewsDAO.displayAll();
 	   String Pname ="pasta";
 	   ReviewsDAO.displayAllByProductName(Pname);
    }
    
}