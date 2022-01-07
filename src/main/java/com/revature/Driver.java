package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.models.Menu;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {
    	
    	UserController uc = new UserController();
    	ReimbursementController rc = new ReimbursementController();
    	//testing Database Connectivity from ConnectionFactory is successful
    	try(Connection conn = ConnectionFactory.getConnection()){
    		System.out.println("Connection Successful!");
    	}catch(SQLException e ) {
    		System.out.println("Connection Failed...");
    		e.printStackTrace();
    	}
    	
    	//implement Javalin
    	
//    	Javalin app = Javalin.create(
//    			//CORS
//    				config -> {
//    					config.enableCorsForAllOrigins();
//    				}
//    			).start(3000);
//    	
//    	
//    	app.get("/username/{username}", uc.getByUsernameHandler);
//    	app.get("/reimbursements/{status}", rc.getByStatusHandler);
//    	app.get("/getById/{reimbursement_id}", rc.getByIntHandler);
    Menu menu = new Menu();
    menu.displayMenu();
    

	};
    

};
