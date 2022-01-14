package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.revature.services.ReimbursementService;
import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

import io.javalin.http.Handler;

public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();
	
	
	public Handler getByStatusHandler = (ctx) -> {
		if(ctx.req.getSession(true) != null) {
			
			String statusStr = ctx.pathParam("status");
			
			Status statusEnum = Status.valueOf(statusStr);
			
			
			List<Reimbursement> getByStatus = rs.getReimbursementsByStatus(statusEnum);
			
			Gson gson = new Gson();
			
			String JSONReimbursements = gson.toJson(getByStatus);
			
			ctx.result(JSONReimbursements);
			ctx.status(200);
			
		}else {
			ctx.result("something failed again...typical");
			ctx.status(400);
		}
	};
	
	public Handler getByIntHandler = (ctx) -> {
		if(ctx.req.getSession(true) != null) { //false getSession
			
			int reimbursement_id = Integer.parseInt(ctx.pathParam("reimbursement_id"));
			
			Optional<Reimbursement> getReimbursementById = rs.getById(reimbursement_id);
			
			Gson gson = new Gson();
			
			String JSONReimbursements = gson.toJson(getReimbursementById);
			
			ctx.result(JSONReimbursements);
			ctx.status(200);
		}else {
			ctx.result("not working.");
			ctx.status(400);
		}
	};
	
	public Handler getPastReimbursementsHandler = (ctx) -> {
		if(ctx.req.getSession(true) != null) {
			
			int id = Integer.parseInt(ctx.pathParam("user_id"));
			
			List<Reimbursement> viewById = rs.viewReimbursements(id);
			
			Gson gson = new Gson();
			
			String JSONPastReimbursements = gson.toJson(viewById);
			
			ctx.result(JSONPastReimbursements);
			ctx.status(200);
		} else {
			ctx.result("failed");
			ctx.status(400);
		}
	};
	
	// add reimbursement
	public Handler submitReimbursementHandler = (ctx) -> {
		if(ctx.req.getSession(true) != null) {
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			Reimbursement submitReimbursement = gson.fromJson(body, Reimbursement.class);
			
			rs.addReimbursement(submitReimbursement);
			
			ctx.result("user successfully added!");
			ctx.status(200);
		} else {
			ctx.result("failed to insert user");
			ctx.status(400);
		}
		};
		
	public Handler updateReimbursementHandler = (ctx) -> {
		if(ctx.req.getSession(true) != null) {
			String body = ctx.body();
			System.out.println("testsss + "  + body);
			Gson gson = new Gson();
			
			Reimbursement updateReimbursement = gson.fromJson(body, Reimbursement.class);
			
			rs.process(updateReimbursement);    
			
			
			ctx.result("Successfully updated.");
			ctx.status(200);
			
		} else {
			ctx.result("Error in updating");
			ctx.status(400);
		}
	};
	
}
