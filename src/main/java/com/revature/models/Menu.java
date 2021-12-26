package com.revature.models;

import java.util.Scanner;

import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;


//placed here temporarily 
public class Menu {
    //static for now since on the same file
	
	UserDAO uDAO = new UserDAO();
	AuthService as = new AuthService();
	Scanner scan = new Scanner(System.in);
	
	
	
    public void displayMenu() {
    	
    	
    	boolean displayMenu = true;
    	

  
    	while(displayMenu) {
		System.out.println("******************************************");
		System.out.println("********WELCOME TO JAVA ISLANDS***********");
		System.out.println("******************************************");
		System.out.println("1 => Login for existing users");
		System.out.println("2 => Login for new users");
		String input = scan.nextLine();
		
		//.equals compares the values of strings, while == compares the memory location or primitives 
		if(input.equals("1")) {
			System.out.println("existing users");
			Login();
			//put methods for logging in as an existing user
		}else if(input.equals("2")) {
			System.out.println("new users");
			NewUser();
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
    	System.out.println("Successfully Logged In!");
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
//    	System.out.println("Lastly what is your role?");
//    	System.out.println("1 => Employee");
//    	System.out.println("2 => Finance Manageer"); // might need to add additional to finance manager 
//    	String roleId = scan.nextLine();
//    	switch case to see if user typed in employee or finance manager
    	
//    	String roleId = scan.nextLine();											// like a code 
//    	scan.nextLine();
//    	System.out.println("Successfully Created");
    	
    	//at the moment only works for first and last name plus email // testing
    	User newUser = new User( 0, username, password, null, f_name, l_name, email);
//    	AbstractUser newUser = new AbstractUser(username, password, email, role);
    	
    	as.register(newUser);
    	
    }
}

//while(displayMenu) {
//System.out.println("Please enter your username");
//String userName = scan.nextLine();
//System.out.println("********************************************************");
//System.out.println("Hello " + userName);
//System.out.println("*********************************************************");
//System.out.println("Please enter password");
//String password = scan.nextLine();
//System.out.println("Successfully logged in");
//System.out.println("*********************************************************");
//// for employee | need another one for finance manager
//
////call method here ()
//
////new method that encapsulates these 
//System.out.println("How can we help you today?");
//System.out.println("1 => View past tickets");
//System.out.println("2 => Add reimbursement request");
//System.out.println("exit => exit the program");
//System.out.println("*********************************************************");
//String input = scan.nextLine();
//
//switch(input) {
//
//case "1" : {
//	System.out.println("This would show the past tickets");
//	System.out.println("What would you like to do next?");
//	String answer = scan.nextLine();
//	if(answer.toLowerCase() == 'yes' ) {
//		
//	}
//	break;
//}
//
//case "2" : {
//	System.out.println("Add a new reimbursement request here");
//	break;
//}
//
//case "exit" : {
//	System.out.println("Sucessfully exited the program");
////	scan.close(); // placed close here that would throw an exception, but mainly for end loop
//	break;
//}
//
//default : {
//	System.out.println("Invalid selection please try again");
//	break;
//}
//}
