package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
// ############################################# GET BY ID #################################################################
    public Optional<Reimbursement> getById(int id) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		
    		ResultSet rs = null;
    		
    		// might have to delete since it's menu -> services -> dao -> database
    		//only username here

    		String sql = "SELECT f_name, l_name, date_submitted, reimbursement_id, amount, curr_status, type_name"
    				+ " FROM reimbursements"
    				+ " LEFT JOIN curr_users"
    				+ " ON user_fkey_auth = user_id"
    				+ " LEFT JOIN status"
    				+ " ON status_fkey = status_id"
    				+ " LEFT JOIN reim_types"
    				+ " ON type_fkey = type_id"
    				+ " WHERE reimbursement_id = ?";
    		//might need to join
    		PreparedStatement ps = conn.prepareStatement(sql);
    		
    		ps.setInt(1, id);
    		rs = ps.executeQuery();
    		
    		
    		Optional<Reimbursement> reimburse = Optional.empty();
    		Optional<Integer> opt = Optional.of(id);
    		
    		Status enumStatus = null;
    		User u = new User();
//    	    public Reimbursement(int id, Status status, User author, Date date_submitted, double amount, String description) {
    	    
    		if(opt.isPresent()) {
    		while(rs.next()) {
    			System.out.println(rs.getInt("reimbursement_id"));
    			if(rs.getString("curr_status").equals("Approved")) {
    				enumStatus = Status.APPROVED;
    			}else if(rs.getString("curr_status").equals("Declined")){
    				enumStatus = Status.DENIED;
    			}else if(rs.getString("curr_status").equals("Pending")) {
    				enumStatus = Status.PENDING;
    			}
    			u.setF_name(rs.getString("f_name"));
    			u.setL_name(rs.getString("l_name"));
//    			String combineName = rs.getString("f_name") + rs.getString("l_name");
//    			System.out.println(combineName);
    			System.out.println(rs.getDate("date_submitted"));
    			
    			System.out.println(enumStatus);
    			
    			if(rs.getInt("reimbursement_id") == id) { //if statement not really needed here
    				Reimbursement r = new Reimbursement(
    						rs.getInt("reimbursement_id"),
    						enumStatus,
    						u, // this returns an USER object with f_name and l_name but other values are null 
    						rs.getDate("date_submitted"),
    						rs.getDouble("amount")
    						
    					);
//    				System.out.println(rs.getString("f_name"));
//    				System.out.println(u);
    				System.out.println(r.getId());
    				System.out.println(r.getAmount());
        			reimburse = Optional.ofNullable(r);
//        			System.out.println(reimburse);
        			}
    			}
    		System.out.println(reimburse);
    		
    		return reimburse;
//    			Reimbursement r = new Reimbursement(
//						id,
//						enumStatus,
//						u,
//						rs.getDate("date_submitted"),
//						rs.getDouble("amount")
//					);
//    			System.out.println(reimburse.isPresent());
//    			reimburse = Optional.of(r);
//    			System.out.println(reimburse.isPresent());
//    			System.out.println();
//    			System.out.println(reimburse);
//    			System.out.println(r);
//    			}
    		
    			
    		}
    }catch (SQLException e) {
    	System.out.println("Something went wrong");
    	e.printStackTrace();
    }
        return null;
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
// ############################################# GET BY STATUS #################################################################
    
    //------------------------------------NOT WORKING IT RETURNS NULL AND TWO ABSTRACT METHODS FOR SOME REASON-------------------------
    public List<Reimbursement> getByStatus(Status status) {
    	//***********ERROR OF : THE COLUMN INDEX IS OUT OF RANGE: 0, NUMBER OF COLUMNS: 4.**********************
    	
    	try(Connection conn = ConnectionFactory.getConnection()) {
//    		System.out.println(status);
    		ResultSet rs = null;
    		String sql = "SELECT reimbursement_id, amount, curr_status, type_name"
    				+ " FROM reimbursements"
    				+ " LEFT JOIN status"
    				+ " ON status_fkey = status_id"
    				+ " LEFT JOIN reim_types"
    				+ " ON type_fkey = type_id"
    				+ " WHERE curr_status = ?";
    			
    		
    		PreparedStatement ps = conn.prepareStatement(sql);
    		
//    		if(status.equals(Status.APPROVED)) {
    			String currentStatus = null;
    			Status enumStatus = null;
    			
    			if(Status.APPROVED.equals(status)) {
    				currentStatus = "Approved";
    				enumStatus = Status.APPROVED;
    			}else if(Status.DENIED.equals(status)) {
    				currentStatus = "Denied";
    				enumStatus = Status.DENIED;
    			}else {
    				currentStatus = "Pending";
    				enumStatus = Status.PENDING;
    			};
    			
    			ps.setString(1, currentStatus);
//    			System.out.println(currentStatus);
    			ps.executeQuery();
        		
        		rs = ps.executeQuery();
//        		System.out.println("hello world");
        		List<Reimbursement> reimbursementList = new ArrayList<>();
//        		int id, Status status, User author, User resolver, double amount
        		while(rs.next()) {

//        			System.out.println("hello worldklfjslkdfjslk");
//        			double dValue = rs.getDouble("amount");
//        			System.out.println(dValue);
//        			String check = rs.getString("amount");
//        			System.out.println(rs.getString("type_name"));
//        			System.out.println(rs.getDouble("amount"));
//        			
        			//convert enum into a string
//        			Status currentStatus = Status.valueOf(rs.getString("curr_status"));
//        			System.out.println(check);
        			Reimbursement e = new Reimbursement(
        						rs.getInt("reimbursement_id"),
        						rs.getString("type_name"),
        						enumStatus,
        						rs.getDouble("amount")
        					);
//        			System.out.println(rs.getString("type_name"));
//        			System.out.println(rs.getString("curr_user"));
//        			Reimbursement r = new Reimbursement(
//        					rs.getInt(0),
//        					rs.getString("type_name"),
//        					enumStatus,
//        					rs.getDouble("amount")
//        					);
        			reimbursementList.add(e);
        			System.out.println(reimbursementList);
//        		
    		}
//        	System.out.println(reimbursementList);
        	return reimbursementList;
//        	return null;
    		
//    		ps.executeQuery();
//    		
//    		rs = ps.executeQuery();
//    		System.out.println("hello world");
//    		List<Reimbursement> reimbursementList = new ArrayList<>();
////    		int id, Status status, User author, User resolver, double amount
//    		while(rs.next()) {
//    			Double ds = Double.parseDouble(rs.getString("amount"));
//    			System.out.println(ds);
    			
//    			Reimbursement r = new Reimbursement(
//    					rs.getInt(0),
//    					rs.getString("curr_status"),
//    					rs.getString("type_name"),
//    					rs.(Double.parseDouble("amount"))
//    					)
//    		}
    		
//    		System.out.println();
//    		}
    	}catch (SQLException e) {
    		System.out.println("No results found.");
    		e.printStackTrace();
    	}
        return Collections.emptyList();
    }



	/**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
	 * @param resolver 
	 * @param finalStatus 
     */
    
// ############################################# UPDATE #################################################################
    //finalStatus and resolver.....
    public Reimbursement update(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
    	//in menu I can probably allow user to getById and then process with this
    	// check if it's their own if it is then throw an error
    	String statusOutcome = null;
    	try(Connection conn = ConnectionFactory.getConnection()){
    		
    		String sql = "UPDATE reimbursements SET status_fkey = ?, user_fkey_resolved = ?, date_resolved = ? WHERE reimbursement_id = ?";
    		
    		PreparedStatement ps = conn.prepareStatement(sql);
    		// can declare these variables in the menu and have for loop to get the ints, not sure about the userfkeyresolved though.
    		//also need a if statement to check if the reimbursement belongs to the same user resolving
    		
    		// i can have if statements to convert the string inputs to numbers so that I can explicitly update the values on db so no need
    		// for select with update statements I assume. Not sure about the user_fkey_resolved b/c might need to grab the int?
    		System.out.println(unprocessedReimbursement.getStatusFkey());
    		ps.setInt(1, unprocessedReimbursement.getStatusFkey());
    		ps.setInt(2, unprocessedReimbursement.getUserFkeyResolved()); 
    		ps.setDate(3, unprocessedReimbursement.getDate_resolved()); //Date at the menu or automatically set a date when user submits?
    		ps.setInt(4, unprocessedReimbursement.getId());
    		System.out.println(unprocessedReimbursement);
    		System.out.println(unprocessedReimbursement.getId());
    		ps.executeUpdate();
    		//if statements for status.
    		if(unprocessedReimbursement.getStatusFkey() == 2){
    			statusOutcome = "Approved";
    		}else if(unprocessedReimbursement.getStatusFkey() == 3) {
    			statusOutcome = "Denied";
    		}
    		
    		System.out.println("Successfully " + statusOutcome + " reimbursement ticket " + unprocessedReimbursement.getId());
    	}catch(SQLException e){
    		System.out.println("Update Error");
    		e.printStackTrace();
    	}
    	return unprocessedReimbursement;
    }
}
