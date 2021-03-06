package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {

	ReimbursementDAO rDAO = new ReimbursementDAO();
    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */
	
	//changed to void because we don't have to return anything?
    public void process(Reimbursement unprocessedReimbursement) {
//    	System.out.println("here");
//    	System.out.println(statusId); //to simplify I can use an if statement here to convert the statusId to finalStatus maybe
        rDAO.update(unprocessedReimbursement);
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
    	
    	List<Reimbursement> statusList = rDAO.getByStatus(status);
    	
        return statusList;
    }
    

//    
    //GET BY ID
	public Optional<Reimbursement> getById(int id) {
		
		Optional<Reimbursement> reimburse = rDAO.getById(id);
		
		return reimburse;
	}
	
	// Add a Reimbursement
	public void addReimbursement(Reimbursement newReimbursement) {
		
		rDAO.submitReimbursement(newReimbursement);
	}
	
	// VIEW PAST TICKETS WITH USERID
	public List<Reimbursement> viewReimbursements(int id) {
		
		List<Reimbursement> reimbursementList = rDAO.getPastReimbursements(id);
		
		return reimbursementList;
	}
}
