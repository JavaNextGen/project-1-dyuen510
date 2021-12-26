package com.revature.repositories;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		
    		ResultSet rs = null;
    		
    		// might have to delete since it's menu -> services -> dao -> database
    		//only username here
    		String sql = "SELECT * FROM users";
    		
    		//set values using PreparedStatement
    		PreparedStatement ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery(sql);
    		
    		//condition if user inputs didn't match
    		if(!rs.next()) {
    			System.out.println("Not Found.");
    		}else {
    			//method for matching...
    		}
    	}catch(SQLException e) {
    		System.out.println(e);
    		e.printStackTrace();
    	}
        return Optional.empty();
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
    public User create(User userToBeRegistered) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		//sql statement using parameters to create a new user
    		String sql = "INSERT INTO curr_users (username, password, f_name, l_name, email) VALUES (?, ?, ?, ?, ?);";
//    		String sql = "SELECT * FROM user";
    		
    		PreparedStatement ps = conn.prepareStatement(sql); //used with SQL commands with variables 
    		
    		//PreparedStatement objects methods to insert values into querys => '?'
    		ps.setString(1, userToBeRegistered.getUsername());
    		ps.setString(2, userToBeRegistered.getPassword());
    		ps.setString(3, userToBeRegistered.getF_name());
    		ps.setString(4, userToBeRegistered.getL_name());
    		ps.setString(5, userToBeRegistered.getEmail());
//    		ps.setInt(6, userToBeRegistered.getRole());
//    		ps.setString(6, userToBeRegistered.getRole_id_fkey());
    		//not sure if I want to set role id here?
    		
    		//ps.executeUpdate() method will send and execute the SQL commands we built
    		//executeUpdate() is for inserts, updates and deletes 
    		ps.executeUpdate();
    		
//    		ps.executeQuery();
    		
    		System.out.println("Successfully created. Welcome " + userToBeRegistered.getF_name());
    	}catch(SQLException e) {
    		System.out.println("Failed to create new user");
    		e.printStackTrace();
    	}
        return userToBeRegistered;
    }
}
