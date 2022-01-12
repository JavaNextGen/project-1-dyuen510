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
	private int user_role_fkey;
	
	
	

	// created a constructor that has the abstract arguments [username, password, role] 
	// and then added the concrete arguments [ f_name, l_name, email ]
	
	//new constructor
	public User(int id, String f_name, String l_name) {
		super();
		this.f_name = f_name;
		this.l_name = l_name;
	}
	
	public User(int id, String f_name, String l_name, String email, int user_role_fkey) {
		super(id);
		this.f_name = f_name;
		this.l_name = l_name;
		this.email = email;
		this.user_role_fkey = user_role_fkey;
	}
	
	public User(int id, String username, String password, Role role, String f_name, String l_name, String email, int user_role_fkey) {
		super(id, username, password, role);
		this.f_name = f_name;
		this.l_name = l_name;
		this.email = email;
		this.user_role_fkey = user_role_fkey;
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

//	@Override
//	public String toString() {
//		return "User [" + (f_name != null ? "f_name=" + f_name + ", " : "")
//				+ (l_name != null ? "l_name=" + l_name + ", " : "") + (email != null ? "email=" + email + ", " : "")
//				+ "user_role_fkey=" + user_role_fkey + "]";
//	}
//    
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

	public int getUserRoleFkey() {
		return user_role_fkey;
	}

	public void setUserRoleFkey(int user_role_fkey) {
		this.user_role_fkey = user_role_fkey;
	}

	@Override
	public String toString() {
		return "User [" + (f_name != null ? "f_name=" + f_name + ", " : "")
				+ (l_name != null ? "l_name=" + l_name + ", " : "") + (email != null ? "email=" + email + ", " : "")
				+ "user_role_fkey=" + user_role_fkey + ", getId()=" + getId() + ", "
				+ (getUsername() != null ? "getUsername()=" + getUsername() + ", " : "")
				+ (getPassword() != null ? "getPassword()=" + getPassword() + ", " : "")
				+ (getRole() != null ? "getRole()=" + getRole() + ", " : "") + "hashCode()=" + hashCode() + ", "
				+ (super.toString() != null ? "toString()=" + super.toString() + ", " : "")
				+ (getClass() != null ? "getClass()=" + getClass() : "") + "]";
	}
    

    
}
