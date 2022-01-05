package com.revature.models;

import java.sql.Date;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {

    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        super(id, status, author, resolver, amount);
    }
    
    //method overloading changing the parameters; this one is for user when they submit request
    public Reimbursement(int id, Status status, User author, Date date_submitted, double amount, String description) {
    	
    	
    	
    }
    
    //method overloading changing parameters for manager roles
    public Reimbursement(int id, Status status, User resolver, Date date_resolved, double amount) {
    	
    }

	public Reimbursement(int id, String type, Status status, double amount) {
		// TODO Auto-generated constructor stub
	}
}
