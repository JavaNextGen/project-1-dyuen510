package com.revature.repositories;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     * 
     */
	
	//############################################ GET ALL USERS ###################################################################
	public List<User> getUsers() {
		
		try(Connection conn = ConnectionFactory.getConnection()){
			
			ResultSet rs = null;
			
			String sql = "SELECT * FROM curr_users";
			
			try(Statement statement = conn.createStatement()){
				rs = statement.executeQuery(sql);
				
				List<User> userList = new ArrayList<>();
				
				while(rs.next()) {
					
					User u = new User(
							rs.getInt("user_id"),
							rs.getString("f_name"),
							rs.getString("l_name"),
							rs.getString("email"),
							rs.getInt("user_role_fkey")
							);
					
					userList.add(u);
							
				}
				
				return userList;
			}catch(SQLException e) {
				System.out.println("Failed to list employees");
				e.printStackTrace();
			}
		}catch(SQLException e) {
			System.out.println("Failed to get the list of employees");
			e.printStackTrace();
		}
		return null;
	}
	//############################################ GET BY USERNAME ###################################################################

    public Optional<User> getByUsername(String username) {
    	
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		
    		ResultSet rs = null;
    		
    		// might have to delete since it's menu -> services -> dao -> database
    		//only username here
    		String sql = "SELECT * FROM curr_users, roles WHERE username = ? AND user_role_fkey = role_id";
    		
    		PreparedStatement ps = conn.prepareStatement(sql);
    		
    		ps.setString(1, username);
//    		ps.executeQuery();
    		rs = ps.executeQuery();
    		
    	
    		Optional<User> userDetails = Optional.empty();

    		//try creating a new optional list instead
//    		List<User> userDetails = new ArrayList<>();
    		
    		Optional<String> opt = Optional.of(username);
    		
    		
//    		opt.ifPresent(System.out::println);
    		String inputUsername = opt.get();
//    		System.out.println(opt.get());
    		
    		if(opt.isPresent()) {
    		while(rs.next()) {
    		
    			
    			if(inputUsername.equals(rs.getString("username"))) {
    				
    				
    				//+++++DONT NEED+++++++++++
    				String userRole = rs.getString("user_role");
    				Role convertRole = null;
//    				System.out.println(userRole);
    				if(userRole.equals("Employee")) {
    					convertRole = Role.EMPLOYEE;
    					System.out.println(convertRole);
    				}else {
    					convertRole = Role.FINANCE_MANAGER;
    					System.out.println(convertRole);
    				}
    				
    				User u = new User(
    							rs.getInt("user_id"), 
    							rs.getString("username"), 
    							rs.getString("password"), 
//    							Role.valueOf(rs.getString("user_role")),  I think this is only for grabbing enum data types in db
    							convertRole,
    							rs.getString("f_name"), 
    							rs.getString("l_name"), 
    							rs.getString("email")
    						);
    				
//    				System.out.println(rs.getString("username"));
    				
    				userDetails = Optional.of(u);
    				

    			}
    			System.out.println(userDetails);
    			System.out.println("hello");
    			return userDetails;
    			
    			
    		}
    		}else{
    		return Optional.empty();
    				

    		}
    	
    	
    }catch (SQLException e) {
    	System.out.println("Something went wrong");
    	e.printStackTrace();
    }

    return null;

}
    		
    			

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
    
//    ########################################################## NEW USER ####################################################
    public User create(User userToBeRegistered) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		//sql statement using parameters to create a new user
    		String sql = "INSERT INTO curr_users (username, password, f_name, l_name, email, user_role_fkey) VALUES (?, ?, ?, ?, ?, ?)";
//    		String sql = "SELECT * FROM user";
    		
    		PreparedStatement ps = conn.prepareStatement(sql); //used with SQL commands with variables 
    		
//    		int enumRoleId = 0;
//    		if(userToBeRegistered.getRole().equals(Role.EMPLOYEE)) {
//    			enumRoleId = 1;
//    		}else if(userToBeRegistered.getRole().equals(Role.FINANCE_MANAGER)) {
//    			enumRoleId = 2;
//    		};
//    		if(role)
    		
    		System.out.println(userToBeRegistered.getRole());
    		//PreparedStatement objects methods to insert values into querys => '?'
    		ps.setString(1, userToBeRegistered.getUsername());
    		ps.setString(2, userToBeRegistered.getPassword());
    		ps.setString(3, userToBeRegistered.getF_name());
    		ps.setString(4, userToBeRegistered.getL_name());
    		ps.setString(5, userToBeRegistered.getEmail());
    		
    		//I used ordinal() + 1 and applied it as foreign key since ordinal() gives the position 
    		// enumeration constant starting at index 0. 
    		ps.setInt(6, userToBeRegistered.getUserRoleFkey()); 
    		System.out.println(userToBeRegistered.getUserRoleFkey());
    		//might need if statement with enumRole = Role.EMPLOYEE or Role.FINANCE_MANAGER
//    		System.out.println(userToBeRegistered.getRole());
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
    		
    		System.out.println("Successfully created. Welcome " + userToBeRegistered.getUserRoleFkey() + 
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
   
    
   
    
    
    

// public User login(User userToBeLoggedIn) {
//    	
//    	try(Connection conn = ConnectionFactory.getConnection()){
//    		//sql statement using parameters to create a new user
//    		String sql = "SELECT * FROM curr_users, roles WHERE username=? AND  user_role_fkey = role_id";
//    		ResultSet rs = null;
//    		
//    		// use the userToBeLoggedIn
//    		PreparedStatement ps = conn.prepareStatement(sql); //used with SQL commands with variables 
//    		
//    		//PreparedStatement objects methods to insert values into querys => '?'
//    		ps.setString(1, userToBeLoggedIn.getUsername());
//
//    		//ps.executeUpdate() method will send and execute the SQL commands we built
//    		//executeUpdate() is for inserts, updates and deletes 
//    		ps.executeQuery();
//    		rs = ps.executeQuery();
//    		
//    		//verify username and password
//    		while(rs.next()) {
//    			String user = rs.getString("username");
//    			String pass = rs.getString("password");
//    			System.out.println(user);
//    			System.out.println(pass);
//    		}
//    		
//    
//    		
//    	}catch(SQLException e) {
//    		System.out.println("Failed to create new user");
//    		e.printStackTrace();
//    	}
//        return null;
//    }
//    
    
    
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // this is almost the same as getByUsername but with password in the parameter
    public User verifyUser(String username, String password) {
    	
    	Role role = null; String f_name = null; String l_name = null; String email = null;
		
		User loggedUser = new User(0, username, password, role, f_name, l_name, email);
		
    	try(Connection conn = ConnectionFactory.getConnection()){
    		
    		ResultSet rs = null;
//    		System.out.println(username);
//    		System.out.println(password);
//    		String sql = "SELECT * FROM curr_users WHERE username = ?";
    		String sql = "SELECT * FROM curr_users, roles WHERE username=? AND  user_role_fkey = role_id;";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		
    		
    		ps.setString(1, username);
    		rs = ps.executeQuery();

    		
    		while(rs.next()) {
    			
    		String verifyUsername = rs.getString("username");
    		String verifyPassword = rs.getString("password");
    		System.out.println(verifyPassword);
    		
//    		String verifyRole = rs.getString("user_role");
//    		int roleNumber;
    		
//    		System.out.println(rs.getString("user_role"));
//    		
//    		
//    		System.out.println("hi");
//    		System.out.println(verifyUsername);
    		
    		// verifying username and password here
    		if(username.equals(verifyUsername) && password.equals(verifyPassword)) {
    			System.out.println("Login is successful");
//    			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    			f_name = rs.getString("f_name");
    			l_name = rs.getString("l_name");
    			email = rs.getString("email");
    			
    			loggedUser.setUsername(verifyUsername);
    			loggedUser.setPassword(verifyPassword);
    			loggedUser.setPassword(rs.getString("password"));
    			loggedUser.setF_name(rs.getString("f_name"));
    			loggedUser.setL_name(rs.getString("l_name"));
    			loggedUser.setEmail(rs.getString("email"));
    			
    			
    			//verifying if employee or finance manager here
    			if(rs.getString("user_role").equals("Employee")) {
    				role = Role.EMPLOYEE;
    				loggedUser.setRole(role);
    			}else {
    				role = Role.FINANCE_MANAGER;
    				loggedUser.setRole(role);
    			}
    			
    			
    			return loggedUser;
    			
    		}else {
    			System.out.println("Username or Password is incorrect.");
    		}
    		
    		}
    		
    		
    	}catch(SQLException e) {
    		System.out.println("Not valid login credentials.");
    		e.printStackTrace();
    	}
    	return loggedUser;
    }
//    
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
