package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import com.revature.models.Menu;
import com.revature.util.ConnectionFactory;

import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {
    	
    	UserController uc = new UserController();
    	ReimbursementController rc = new ReimbursementController();
    	AuthController ac = new AuthController();
    	
    	//testing Database Connectivity from ConnectionFactory is successful
    	try(Connection conn = ConnectionFactory.getConnection()){
    		System.out.println("Connection Successful!");
    	}catch(SQLException e ) {
    		System.out.println("Connection Failed...");
    		e.printStackTrace();
    	}
    	
    	//implement Javalin
    	
    	Javalin app = Javalin.create(
    			//CORS
    				config -> {
    					config.enableCorsForAllOrigins();
    				}
    			).start(3000);
    	
    	app.get("/users", uc.getUsersHandler);
    	app.get("/username/{username}", uc.getByUsernameHandler);
    	app.post("/username", ac.registerNewUser);
    	app.post("/login", ac.loginHandler);
    	app.post("/reimbursement", rc.submitReimbursementHandler);
    	// I still need :
    	// app.put update reimbursement
    	app.put("/reimbursement", rc.updateReimbursementHandler );
    	app.get("/reimbursementsByStatus/{status}", rc.getByStatusHandler);
    	app.get("/reimbursementById/{reimbursement_id}", rc.getByIntHandler);
    	app.get("/reimbursements/history/{user_id}", rc.getPastReimbursementsHandler);
    Menu menu = new Menu();
    menu.displayMenu();
    

	};
    

};
