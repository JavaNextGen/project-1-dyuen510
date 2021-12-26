package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>This ConnectionFactory class follows the Singleton Design Pattern and facilitates obtaining a connection to a Database for the ERS application.</p>
 * <p>Following the Singleton Design Pattern, the provided Constructor is private, and you obtain an instance via the {@link ConnectionFactory#getInstance()} method.</p>
 */
public class ConnectionFactory {

    private static ConnectionFactory instance;

    ConnectionFactory() {
        super();
    }

    /**
     * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
     * <p>It is invoked via:</p>
     *
     * {@code ConnectionFactory.getInstance()}
     */
    public static ConnectionFactory getInstance() {
        if(instance == null) {
            instance = new ConnectionFactory();
        }

        return instance;
    }

    /**
     * <p>The {@link ConnectionFactory#getConnection()} method is responsible for leveraging a specific Database Driver to obtain an instance of the {@link java.sql.Connection} interface.</p>
     * <p>Typically, this is accomplished via the use of the {@link java.sql.DriverManager} class.</p>
     */
    public static Connection getConnection() throws SQLException {
    	
    	try {
    		Class.forName("org.postgresql.Driver");
    	}catch(ClassNotFoundException e) {
    		System.out.println("Class Not Found");
    		e.printStackTrace();
    	}
    	
    	// Database Credentials 
    	//url to my database schema
    	String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=ers";
    	String username = "postgres";
    	String password = "password";
    	//what actually returns our Connection object. This method has a return type of Connection
        return DriverManager.getConnection(url, username, password);
    }
}
