package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Handler;
import javax.servlet.http.HttpSession;
import org.eclipse.jetty.server.session.Session;

public class AuthController {

	AuthService as = new AuthService();
	
	public Handler registerNewUser = (ctx) -> {
		if(ctx.req.getSession(true) != null) {
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
	
	public Handler loginHandler = (ctx) -> {
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);
		
		if(as.login(LDTO.getUsername(), LDTO.getPassword()) == 1) {
			
			ctx.req.getSession();
			
			ctx.res.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=None; Secure");
			
			ctx.status(201);
			ctx.result("Login Success Employee");
		} else if(as.login(LDTO.getUsername(), LDTO.getPassword()) == 2){
			ctx.req.getSession();
		
			ctx.res.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=None; Secure");
			
			ctx.status(202);
			ctx.result("Login Success Hiring Manager");

		}else {
			ctx.status(401);
			ctx.result("Login Failed!");
			System.out.println(as.login(LDTO.getUsername(), LDTO.getPassword()));
		}
	};
}
