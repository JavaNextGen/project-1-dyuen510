package com.revature.models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;


//placed here temporarily 
public class Menu {
    //static for now since on the same file
	
	UserDAO uDAO = new UserDAO();
	ReimbursementDAO rDAO = new ReimbursementDAO();
	ReimbursementService rs = new ReimbursementService();
	AuthService as = new AuthService();
	UserService us = new UserService();
	Scanner scan = new Scanner(System.in);
	Role role;
	
//	Role Role; might need not sure yet
	
    public void displayMenu() {
    	
    	
    	boolean displayMenu = true;
    	

  
    	while(displayMenu) {
		System.out.println("******************************************");
		System.out.println("********WELCOME TO JAVA ISLANDS***********");
		System.out.println("******************************************");
		System.out.println("1 => Login for existing users");
		System.out.println("2 => Login for new users");
		System.out.println("3 checking");
		String input = scan.nextLine();
		
		//.equals compares the values of strings, while == compares the memory location or primitives 
		if(input.equals("1")) {
			System.out.println("existing users");
			Login();
			//put methods for logging in as an existing user
		}else if(input.equals("2")) {
			System.out.println("new users");
			NewUser();
		}else if(input.equals("4")) {
			System.out.println("enter an id");
			int r_id = scan.nextInt();
			scan.nextLine();
			rDAO.getById(r_id);
		}else if(input.equals("3")) {
			System.out.println("enter status");
			System.out.println("1 -> pending");
			System.out.println("2 -> approved");
			System.out.println("3 -> declined");
			String statusInput = scan.nextLine();
			Status status = null;
			if(statusInput.equals("1")) {
				status = Status.PENDING;
			}else if(statusInput.equals("2")) {
				status = Status.APPROVED;
			}else if(statusInput.equals("3")) {
				status = Status.DENIED;
			}else {
				System.out.println("invalid option, please please please try again or hope that it works.");
			}
			rDAO.getByStatus(status);
//			System.out.println(status);
			//put methods for registering a new user
		}else {
			System.out.println("input not valid");
		}
    	}	
    }
    
    //login method for existing users that I will place in the if statement
    public void Login() {
    	
    	Scanner scan = new Scanner(System.in);
    	System.out.println("*******Please enter your username*******");
    	String username = scan.nextLine();
    	System.out.println("*******Please enter your password*******");
    	String password = scan.nextLine();
    		// ctx.cookieStore(1, newUser);
    		// User newUser = ctx.cookieStore<User>("newUser")
    		// newUser.getUsername(); etc......
    	System.out.println("hello world");
    	as.login(username, password);
    	uDAO.verifyUser(username, password);
    	 Optional<User> preResolve = uDAO.getByUsername(username);
//    	#####################################TESTING RETRIEVE PAST RECORDS BY USER_ID###################################
    	System.out.println("What would you like to do today?");
    	System.out.println("Viewing past records");
    	int userId = preResolve.get().getId();
    	System.out.println(userId);
    	rDAO.getPastReimbursements(userId);
    	//###################################TESTING SUBMIT#############################################################
    	submitReimburse(username);
    	//###################################TESTING UPDATE#############################################################
    	System.out.println("update a reimbursement ticket?");
    	System.out.println("enter ticket ID");
        int id = scan.nextInt();
        System.out.println(id);
        scan.nextLine();
//        System.out.println("Approve or Deny");
        String status = scan.nextLine(); // might need an if statement
        Status finalStatus = null;
        int status_fkey = 1;
//        int user_fkey_resolved = 0;
        
        if(status.equals("Approve") || status.equals("Approved")){
        	finalStatus = Status.APPROVED;
        	status_fkey = 2;
        }else if(status.equals("Deny") || status.equals("Denied")) {
        	finalStatus = Status.DENIED;
        	status_fkey = 3;
        }
        
        //adding the getByUsername here to grab the data from curr_user table
//        Optional<User> preResolve = uDAO.getByUsername(username);
//        User resolve = new User();
        System.out.println(preResolve);
        System.out.println();
        System.out.println("edoeksjdlfkj");
//        System.out.println(uDAO.getByUsername(username).get());
//        System.out.println(uDAO.getByUsername(username).getF_name());
        System.out.println(preResolve.get().getF_name());
        System.out.println("here e here e");
        User resolver = new User();
        int resolver_fkey = preResolve.get().getId();
        System.out.println(preResolve.get().getId());
        resolver.setF_name(preResolve.get().getF_name());
        resolver.setL_name(preResolve.get().getL_name());
        System.out.println(resolver);
        
        
        Date date_resolved = null;
        System.out.println(id);
        System.out.println(id);
        
        Reimbursement unprocessedReimbursement = new Reimbursement(id, status_fkey, resolver_fkey , date_resolved);
        
//        rs.process(unprocessedReimbursement, finalStatus, resolver);
        
        //update or process?
    	rs.process(unprocessedReimbursement);
//    	uDAO.getByUsername(username);
    	
    	//this only returns a STRING, cannot iterate to grab the values
//    	List<User> newDetails = new ArrayList<>();
//    	newDetails.add(us.getByUsername(username).get());
//    	
//    	for(User n : newDetails) {
//    	System.out.println(n);
//    	System.out.println(n.getEmail()); // alright this works!
//    	System.out.println("hello");
//    	if(n.getRole() == Role.EMPLOYEE) {
//    		//method here
//    		System.out.println("Employee Options");
//    		System.out.println("############################################");
//    		System.out.println("What would you like to do today?");
//    		System.out.println("1 => submit reimbursements");
//    		System.out.println("2 => View past tickets"); //can do a sql method which grabs the id of this specific user and view tickets
//    	}else {
//    		//method here
//    	}	System.out.println("Finance Manager Options");
//    		System.out.println("############################################");
//    		System.out.println("What would you like to do today?");
//    		System.out.println("1 -> reimbursements for all employees");
//    		System.out.println("SHOW REIMBURSEMENTS");
//    		System.out.println("1 -> Filter request by status");
//    		System.out.println("2 -> Approve/Deny reimbursements");
//    	
//    	}
    
        
        //#####################################dont need##############################################
//    	User loggedUser = new User( 0, username, password, role, f_name, l_name, email);
    	
    	
//    	System.out.println("Successfully Logged In!");
//    	System.out.println(role);
    	
    }
    
    public void submitReimburse(String username) {
//    	rDAO.getByStatus(status);
//    	Scanner scan = new Scanner(System.in);
    	System.out.println("******************#Submit a New Reimbursement****************************");
    	System.out.println("Choose Reimbursement Type");
    	System.out.println("1 -> Lodging");
    	System.out.println("2 -> Travel");
    	System.out.println("3 -> Food");
    	System.out.println("4 -> Other");
    	int reimType = scan.nextInt();
    	scan.nextLine();
    	System.out.println("Enter amount");
    	double amount = scan.nextDouble();
    	scan.nextLine();
    	System.out.println("Enter a brief description");
    	String description = scan.nextLine(); 
    	//BYTE RECEIPT
    	Random rd = new Random();
    	byte[] receipt = new byte[7];
    	rd.nextBytes(receipt);
    	System.out.println(receipt); //testing
    	LocalDate localD = LocalDate.now();
		java.sql.Date date_submit = java.sql.Date.valueOf(localD);
    	//getting the auth_id
    	
    	Optional<User> grabAuthId = uDAO.getByUsername(username);
    	int authId = grabAuthId.get().getId();
    	
//    	System.out.println(grabAuthId);
    	
//    	int id, Status status, double amount, int user_fkey_author, Date date_submitted, String description, int type_id, byte receipt
    	Reimbursement newReimbursement = new Reimbursement(0, 1, amount, authId, date_submit, description, reimType, receipt);
    	
    	rs.addReimbursement(newReimbursement);
    	System.out.println(newReimbursement);
    	
    }
    
    //create new user method that I will place in the if statement
    public void NewUser() {
    	Scanner scan = new Scanner(System.in);
    	System.out.println("***************Create New User*****************");
    	System.out.println("Create a username");
    	String username = scan.nextLine();
    	//check if username already exists 
    	System.out.println("Enter password");
    	String password = scan.nextLine();
    	//exceptions cannot be null 
    	System.out.println("Enter your first name");
    	String f_name = scan.nextLine();
    	System.out.println("Enter your last name");
    	String l_name = scan.nextLine();
    	System.out.println("Enter your email");
    	String email = scan.nextLine();
    	//check if it is email format?
    	//maybe put this elsewhere? [ role ]
    	System.out.println("Lastly what is your role?");
    	System.out.println("1 => Employee");
    	System.out.println("2 => Finance Manageer"); // might need to add additional to finance manager 
    	String roleId = scan.nextLine();
    	int roleFkey = 1;
//    	switch case to see if user typed in employee or finance manager
//    	need to figure out a way to convert it to a int foreign key since db has roles in separate table 
    	switch(roleId) {
    	case "1" : 
//    		System.out.println(Role.EMPLOYEE);
    		role = Role.EMPLOYEE;
    		roleFkey = 1;
    		break;
    	case "2" : 
//    		System.out.println(Role.FINANCE_MANAGER);
    		role = Role.FINANCE_MANAGER;
    		roleFkey =2;
    		//if statement for them to input a code that we can do == operator 
    		//if correct then continue if not create account fail
    		break;
    	default : 
    		System.out.println("Not Applicable");
    		break;
    	}
    	
    	//MAYBE REDIRECT USER BACK TO THE LOGIN INTERFACE | TO LOGIN THEIR NEWLY CREATED ACCOUNT
    	switch(role) {
    	case EMPLOYEE:
    		//new method
    		System.out.println("Hi employee");
    		break;
    	case FINANCE_MANAGER: 
    		//new method
    		System.out.println("Hi finance manager");
    		break;
    	}
//    	 role = scan.nextLine();
//    	String roleId = scan.nextLine();											// like a code 
//    	scan.nextLine();
//    	System.out.println("Successfully Created");
    	
  
		//at the moment only works for first and last name plus email // testing
    	User newUser = new User( 1, username, password, role, f_name, l_name, email, roleFkey);
//    	AbstractUser newUser = new AbstractUser(username, password, email, role);
    	
    	as.register(newUser);
    	//ctx.cookie(some,something)
    	
    	Login();
    }
}
