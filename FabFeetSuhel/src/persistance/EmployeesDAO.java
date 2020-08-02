package persistance;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employees;


public class EmployeesDAO{
    // TODO check and verify 
    public static void displayAll(){
        Connection con;
        con = ConnectToDB.getConnection();
		String sql=" select * from orcluser.employees ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			if(rs.next()==false)   
				System.out.println("No employees exist");
			else {
					Employees emp;
					int employeeId;
					String employeeName;
					String designation;
					int branchId;
				do 
				{
					emp= new Employees();
					emp.setEmployeeId(rs.getInt("employeeId"));
					emp.setEmployeeName(rs.getString("employeeName"));
					emp.setDesignation(rs.getString("designation"));
					emp.setBranchId(rs.getInt("branchId"));
					emp.display();
					
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("Error in EMPDAO while  displayAll");
			e.printStackTrace();
		}

        ConnectToDB.closeConnection(con);     
    }


	// TODO check and verify 
    public static void displayAllByBranchID(int BID){
        Connection con;
        con = ConnectToDB.getConnection();
		String sql=" select * from orcluser.employees where branchid = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, BID);
			ResultSet rs= ps.executeQuery();

			if(rs.next()==false)   
				System.out.println("No employees exist in "+BID);
			else {
					Employees emp= new Employees();
				do 
				{
					emp.setEmployeeId(rs.getInt("employeeId"));
					emp.setEmployeeName(rs.getString("employeeName"));
					emp.setDesignation(rs.getString("designation"));
					emp.setBranchId(rs.getInt("branchId"));
					emp.display();
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("Error in EMPDAO while  displayAllBranch");
			e.printStackTrace();
		}

        ConnectToDB.closeConnection(con);     
    }

    //TODO est using this
//    	public static void main(String[] args) {

// 	   EmployeesDAO.displayAll();
// 	   int BID =3;
// 	   EmployeesDAO.displayAllByBranchID(BID);
//    }
    
}