package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

import io.javalin.http.Handler;

import com.revature.models.User;
import com.revature.services.UserService;

public class UserController {
	
	UserService us = new UserService();
	
	public Handler getByUsernameHandler = (ctx) -> {
		if(ctx.req.getSession(true) != null) {
			
			String username = ctx.pathParam("username");
			
			Optional<User> getByUsername = us.getByUsername(username);
			
			Gson gson = new Gson();
			
			String JSONUser = gson.toJson(getByUsername);
			
			ctx.result(JSONUser);
			ctx.status(200);
		}else {
			ctx.result("oh no something went wrong again...");
			ctx.status(401);
		}
	};
	
	public Handler getUsersHandler = (ctx) -> {
		if(ctx.req.getSession(true) != null) {
			
			List<User> allUsers = us.getUsers();
			
			Gson gson = new Gson();
			
			String JSONUsers = gson.toJson(allUsers);
			
			ctx.result(JSONUsers);
			ctx.status(200);
		} else {
			ctx.result("Error - can't access users");
			ctx.status(401);
		}
	};
}
