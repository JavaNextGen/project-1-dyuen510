package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Menu;
import com.revature.util.ConnectionFactory;

public class Driver {

    public static void main(String[] args) {
    	
    	//testing Database Connectivity from ConnectionFactory is successful
    	try(Connection conn = ConnectionFactory.getConnection()){
    		System.out.println("Connection Successful!");
    	}catch(SQLException e ) {
    		System.out.println("Connection Failed...");
    		e.printStackTrace();
    	}
    	
    Menu menu = new Menu();
    menu.displayMenu();
    

	};
    

};
