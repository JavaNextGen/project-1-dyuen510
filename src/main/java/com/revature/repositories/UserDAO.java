package com.revature.repositories;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
	
//	public Optional<User> verifyPassword(String password) {
//		
//		try(Connection conn = ConnectionFactory.getConnection()){
//			ResultSet rs = null;
//			
//			String sql = "SELECT * FROM curr_users WHERE "
//		}
//	}
	
	
	
//	private boolean verifyUser(String username, String password) {
//		boolean login = false;
//		
//		try(Connection conn = ConnectionFactory.getConnection()) {
//			
//			String sql = "SELECT (username, password) FROM curr_users WHERE username=? AND password=?";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, username);
//			ps.setString(2, password);
//			ps.executeQuery();
//			ResultSet rs = ps.executeQuery();
//			
//			String verifyUser = rs.getString("username");
//			String verifyPassword = rs.getString("password");
//			
//			if((verifyUser.equals(username)) && verifyPassword.equals(password)) {
//				login = true;
//				System.out.println("successfully logged in");
//			}else {
//				login = false;
//				System.out.println("Sorry but username and password didn't match.");
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//			System.out.println("failed miserably");
//		}
//		return false;
//	}

	
    public Optional<User> getByUsername(String username) {
    	
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		
    		ResultSet rs = null;
    		
    		// might have to delete since it's menu -> services -> dao -> database
    		//only username here
    		String sql = "SELECT * FROM curr_users WHERE username = ?";
    		
    		PreparedStatement ps = conn.prepareStatement(sql);
    		
    		ps.setString(1, username);
//    		ps.executeQuery();
    		rs = ps.executeQuery();
    		
    		Optional<String> opt = Optional.of(username);
    		opt.ifPresent(System.out::println);
    		String inputUsername = opt.get();
//    		System.out.println(opt.get());
    		
    		if(opt.isPresent()) {
    		while(rs.next()) {
    		
    			if(inputUsername.equals(rs.getString("username"))) {
    				
    				System.out.println("Username Located:");
    				System.out.println("username: " + rs.getString("username"));
    				System.out.println("email: " + rs.getString("email"));
    				System.out.println("first name: " + rs.getString("f_name"));
    				System.out.println("last name: " + rs.getString("l_name"));
    			}
    			
    		}
    		}else{
    				return Optional.empty();
    				

    	    			
//    			System.out.println(rs.getString("username") + " " + rs.getString("password"));
    		}
 
//    		String verifyPassword = rs.getString("password");
//    		System.out.println(verifyPassword);
    		
//    		System.out.println(e);
        			
//    		System.out.println(username);
//    		System.out.println(password);
    		
//    		rs = ps.executeQuery();
    		
//    		Optional<String> opt = Optional.of(username);
    		
    	
    		
    		
    		
//    		opt.ifPresent(e -> System.out.println(username.length()));

    		
//    		while(rs.next()) {
//    			
//    			User e = new User(
//    			0, rs.getString("username"),
//    			rs.getString("password"), 
//    			null,
//    			rs.getString("f_name"),
//    			rs.getString("l_name"),
//    			rs.getString("email")
//    			);
//    			
//    			System.out.println(e.getUsername());
//    		};
//    		
//        		User verify = new User(
//        				rs.getInt("user_id"),
//        				rs.getString("username"),
//        				rs.getString("password"),
//        				null,
//        				rs.getString("f_name"),
//        				rs.getString("l_name"),
//        				rs.getString("email")
//        				
////        				0, rs.getString("username"),
////        				rs.getString("password"),
////        				null, rs.getString("f_name"), rs.getString("l_name"), rs.getString("email")
//        				);
        		
//        		opt2 = Optional.of(verify);
        		
//        		if(rs.next()) {
//        			if(opt.equals(opt2)) {
//            			System.out.println("they equal");
//            		}else {
//            			System.out.println("they don't");
//            		}
//        		}else {
//        			System.out.println("nothing");
//        		}
        		
    		
    		
    		
    		
//    		User e = new User(
//    				rs.getString("username"),
//    				rs.getString("password")
////    				rs.getString("f_name"),
////    				rs.getString("l_name"),
////    				rs.getString("email"),
////    				rs.getObject("role")
//    				);
//    		
    		
//    		verifyUser.add(e);
//    		return verifyUser;
//    	
//    	
    	
    	
    }catch (SQLException e) {
    	System.out.println("Something went wrong");
    	e.printStackTrace();
    }

    return null;

}
    		
    			
//    		rs = statement.executeQuery(sql);
//    		List<User> verifyUser = new ArrayList<>();
    		
    		
    		//set values using PreparedStatement
//    		PreparedStatement ps = conn.prepareStatement(sql);
//    		rs = ps.executeQuery();
    		
//    		condition if user inputs didn't match
//    		if(!rs.next()) {
//    			System.out.println("Not Found.");
//    			System.out.println(rs);
//    		}else {
//    			//method for matching...
//    			System.out.println(rs);
//    		}
//    	}catch(SQLException e) {
//    		System.out.println(e);
//    		e.printStackTrace();
//    	}
//        return Optional.empty();
//    
//    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		//sql statement using parameters to create a new user
    		String sql = "INSERT INTO curr_users (username, password, f_name, l_name, email, user_role_fkey) VALUES (?, ?, ?, ?, ?, ?);";
//    		String sql = "SELECT * FROM user";
    		
    		PreparedStatement ps = conn.prepareStatement(sql); //used with SQL commands with variables 
    		
    		//PreparedStatement objects methods to insert values into querys => '?'
    		ps.setString(1, userToBeRegistered.getUsername());
    		ps.setString(2, userToBeRegistered.getPassword());
    		ps.setString(3, userToBeRegistered.getF_name());
    		ps.setString(4, userToBeRegistered.getL_name());
    		ps.setString(5, userToBeRegistered.getEmail());
    		//I used ordinal() + 1 and applied it as foreign key since ordinal() gives the position 
    		// enumeration constant starting at index 0. 
    		ps.setObject(6, userToBeRegistered.getRole().ordinal()+1);
    		//Role is not a String so can't setString
//    		ps.setString(6, userToBeRegistered.getRole());
    		
    		//if statement to check and then setRole to either 1 or 2 INT
    		
//    		ps.setInt(6, userToBeRegistered.getRole());
//    		ps.setString(6, userToBeRegistered.getRole_id_fkey());
    		//not sure if I want to set role id here?
    		
    		//ps.executeUpdate() method will send and execute the SQL commands we built
    		//executeUpdate() is for inserts, updates and deletes 
    		ps.executeUpdate();
    		
//    		ps.executeQuery();
    		
    		System.out.println("Successfully created. Welcome " + userToBeRegistered.getRole() + 
    				". Please login with newly created credentials.");
    		
//    		String strRole = "SELECT * FROM curr_users, roles WHERE user_role_fkey = role_id"; 
    	}catch(SQLException e) {
    		System.out.println("Failed to create new user");
    		e.printStackTrace();
    	}
        return userToBeRegistered;
    }
    
    //string username and password? and then compare with SQL
    // NEVERMIND there is a method above `````````````````````````````````````````````
    
    public User verifyUser(String username, String password) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		
    		ResultSet rs = null;
    		System.out.println(username);
    		System.out.println(password);
    		String sql = "SELECT * FROM curr_users WHERE username = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		
    		ps.setString(1, username);
    		rs = ps.executeQuery();

    		
    		while(rs.next()) {
    		String verifyUsername = rs.getString("username");
    		String verifyPassword = rs.getString("password");
    		
    		
    		System.out.println(verifyUsername);
    		if(username.equals(verifyUsername) && password.equals(verifyPassword)) {
    			System.out.println("Login is successful");
    			//other methods here?
    		}else {
    			System.out.println("Username or Password is incorrect.");
    		}
    		}
    	
    		
    	}catch(SQLException e) {
    		System.out.println("Not valid login credentials.");
    		e.printStackTrace();
    	}
    	return null;
    }
}
