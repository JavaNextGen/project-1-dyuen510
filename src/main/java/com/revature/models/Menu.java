package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
			System.out.println(status);
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
    
    	System.out.println("hello world");
    	uDAO.getByUsername(username);
    	
    	//this only returns a STRING, cannot iterate to grab the values
    	List<User> newDetails = new ArrayList<>();
    	newDetails.add(us.getByUsername(username).get());
    	
    	for(User n : newDetails) {
    	System.out.println(n);
    	System.out.println(n.getEmail()); // alright this works!
    	System.out.println("hello");
    	if(n.getRole() == Role.EMPLOYEE) {
    		//method here
    		System.out.println("Employee Options");
    		System.out.println("############################################");
    		System.out.println("What would you like to do today?");
    		System.out.println("1 => submit reimbursements");
    		System.out.println("2 => View past tickets"); //can do a sql method which grabs the id of this specific user and view tickets
    	}else {
    		//method here
    	}	System.out.println("Finance Manager Options");
    		System.out.println("############################################");
    		System.out.println("What would you like to do today?");
    		System.out.println("1 -> reimbursements for all employees");
    		System.out.println("SHOW REIMBURSEMENTS");
    		System.out.println("1 -> Filter request by status");
    		System.out.println("2 -> Approve/Deny reimbursements");
    	
    	}
    
//    	User loggedUser = new User( 0, username, password, role, f_name, l_name, email);
    	
    	
//    	System.out.println("Successfully Logged In!");
//    	System.out.println(role);
    	
    }
    
    public void Reimburse() {
    	rDAO.getByStatus(status);
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
    	
//    	switch case to see if user typed in employee or finance manager
//    	need to figure out a way to convert it to a int foreign key since db has roles in separate table 
    	switch(roleId) {
    	case "1" : 
    		
//    		System.out.println(Role.EMPLOYEE);
    		role = Role.EMPLOYEE;
    		break;
    	case "2" : 
//    		System.out.println(Role.FINANCE_MANAGER);
    		role = Role.FINANCE_MANAGER;
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
    	User newUser = new User( 0, username, password, role, f_name, l_name, email);
//    	AbstractUser newUser = new AbstractUser(username, password, email, role);
    	
    	as.register(newUser);
    	
    	Login();
    }
}
