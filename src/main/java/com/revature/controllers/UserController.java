package com.revature.controllers;

import java.util.Optional;

import com.google.gson.Gson;

import io.javalin.http.Handler;

import com.revature.models.User;
import com.revature.services.UserService;

public class UserController {
	
	UserService us = new UserService();
	
	public Handler getByUsernameHandler = (ctx) -> {
		if(ctx.req.getSession() != null) {
			
			String username = ctx.pathParam("username");
			
			Optional<User> getByUsername = us.getByUsername(username);
			
			Gson gson = new Gson();
			
			String JSONUser = gson.toJson(getByUsername);
			
			ctx.result(JSONUser);
			ctx.status(200);
		}else {
			ctx.result("oh no something went wrong again...");
			ctx.status(200);
		}
	};
}
