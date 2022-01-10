package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

public class AuthController {

	AuthService as = new AuthService();
	
	public Handler registerNewUser = (ctx) -> {
		if(ctx.req.getSession() != null) {
			String body = ctx.body();
			
			Gson gson = new Gson();
			
			User userToBeRegistered = gson.fromJson(body, User.class);
			
			as.register(userToBeRegistered);
			
			ctx.result("user successfully added!");
			ctx.status(200);
		} else {
			ctx.result("failed to insert user");
			ctx.status(400);
		}

	};
}
