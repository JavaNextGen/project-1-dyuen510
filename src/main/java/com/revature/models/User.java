package com.revature.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {
	
	private String f_name;
	private String l_name;
	private String email;
	
	
	

	// created a constructor that has the abstract arguments [username, password, role] 
	// and then added the concrete arguments [ f_name, l_name, email ]
	
	//new constructor
	public User(int id, String f_name, String l_name) {
		super();
		this.f_name = f_name;
		this.l_name = l_name;
	}
	
	//need to see how to get the Role since the data type is int in db, but String? on DML?
    public User(int id, String username, String password, Role role, String f_name, String l_name, String email) {
		super(id, username, password, role);
		// TODO Auto-generated constructor stub

		this.f_name = f_name;
		this.l_name = l_name;
		this.email = email;
	}

	public User() {
        super();
    }

    public User(int i, String username) {
		// TODO Auto-generated constructor stub
    	super();
	}

	/**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
   
    

	//created a new constructor for the f_name, l_name and email.
//    public User(String f_name, String l_name, String email) {
//    	super(); // study on super()
//    	this.f_name = f_name;
//    	this.l_name = l_name;
//    	this.email = email;
//    	
//    	
//    }
    
    //returns our public employee information as a string, have to see if the abstract return is returned also
    //if not might have to add it here? 
    @Override
    public String toString() {
    	return "first name: " + f_name + " last name: " + l_name + " email: " + email + " password: " + getPassword()
    	+ " username: " + getUsername() + " role " + getRole();
    }
    
    //Getter and setter methods to access and change private variables above 
    //first Name
    public String getF_name() {
    	return f_name;
    }
    
    public void setF_name(String f_name) {
    	this.f_name = f_name;
    }
    
    //Last Name
    public String getL_name() {
    	return l_name;
    }
    
    public void setL_name(String l_name) {
    	this.l_name = l_name;
    }
    
    //Email
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    

    
}
