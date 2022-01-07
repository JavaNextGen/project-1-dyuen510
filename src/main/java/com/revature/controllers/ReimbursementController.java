package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.revature.services.ReimbursementService;
import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.Status;

import io.javalin.http.Handler;

public class ReimbursementController {

	ReimbursementService rs = new ReimbursementService();
	
	
	public Handler getByStatusHandler = (ctx) -> {
		if(ctx.req.getSession(false) != null) {
			
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
		if(ctx.req.getSession() != null) { //false getSession
			
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
}
