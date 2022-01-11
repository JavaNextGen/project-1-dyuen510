package com.revature.services;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

import java.sql.SQLException;
import java.util.Optional;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {

	
	UserDAO uDAO = new UserDAO(); // to use the methods in UserDAO
	
    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     */
	
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
    public boolean login(String username, String password) {
    	//might need a statement to select queries to database and check if it's equal
    	//instead of uDAO.create is there another uDAO method that lets us communicate with db?
    	//somehow put this java logic into the menu.java with the Login() method...
    	// probably best to use an if statement
//    	System.out.println("#######################################################");
//    	String dbUsername = uDAO.verifyUser(username, password).getUsername();
//    	String dbPassword = uDAO.verifyUser(username, password).getPassword();
//    	//also verify Role here
//    	if(username.equals(dbUsername) && password.equals(dbPassword)) { //doesn't work b/c same data
//    		System.out.println("1");
//    		System.out.println("here one");
//    		System.out.println(dbPassword);
//    		System.out.println(password);
//    		return true;
//    	}else {
//    		System.out.println("2");
//    		System.out.println("here two");
//    		return false;
//    	}
    	
    	/* NOTE : validate username/password against some username/password in the DB
    			  need to use the DAO method that uses those for matching values in the DB
    			  need DAO method like:
    			  	"select * from users where username = ? and password = ? and then insert the values of the DTO for parameters
    			  	
    	*/
    	// TESTING
    	if(username.equals("david") && password.equals("password")) {
    		return true;
    	}
    	
    	return false;
    	
    	
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
//	public User Login(User userToBeLoggedIn) {
//		
//		uDAO.login(userToBeLoggedIn);
//		
//		return userToBeLoggedIn;
//	}
    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    public User register(User userToBeRegistered) {
    	
    	// try and catch block with the UserNameNotUniqueException 
    	
    	uDAO.create(userToBeRegistered);
    	System.out.println(userToBeRegistered);
        return userToBeRegistered;
    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser() {
        return Optional.empty();
    }
}
