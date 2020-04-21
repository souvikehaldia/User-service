package com.tcs.bankclient.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.bankclient.model.User;
import com.tcs.bankclient.model.Login;
import com.tcs.bankclient.model.Response;
import com.tcs.bankclient.service.UserServiceImpl;


@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userservice;
	
	@GetMapping("/getrecords")//s
	public Response getAllRecords() throws Exception
	{
		return userservice.getAllRecords();
	}
	
	@GetMapping("/testgetrecords")//s
	public List<User> testgetAllRecords() throws Exception
	{
		return userservice.testgetAllRecords();
	}
	
	@GetMapping("/getUserById")//s
	public Response getUserById(@RequestParam String userid) throws Exception
	{
		return userservice.getUserById(userid);
	}
	
	@GetMapping("/getAccountById")//s
	public Response getAccountById(@RequestParam Integer accountid) throws Exception
	{
		return userservice.getAccountById(accountid);
	}
	
	@GetMapping("/getAllAccounts")//s
	public Response getAllAccounts() throws Exception
	{
		return userservice.getAllAccounts();
	}
	
	@PostMapping("/updateUser")
	public Response updateUser(@RequestBody Login user)
	{
		return(userservice.updateUser(user));
	}
	
	@PostMapping("/saverecord")//s
	public Response saveUser(@Valid @RequestBody User user) throws Exception
	{
		return userservice.saveUser(user);
		
	}
	
	@GetMapping("/getSecurityQuestion")//s
	public Response getSecurityQuestion(@Valid @RequestParam String email) 
	{
		return userservice.getSecurityQuestion(email);
		
	}
		
	@PostMapping("/login")//s
	public Response checkLogin(@RequestBody Login login) throws Exception
	{
		Response emp= userservice.checkLogin(login);
			return emp;
			
	}
	
	@PostMapping("/changepassword")//s
	public Response changePassword(@RequestBody Login login) throws Exception
	{
		return userservice.changePassword(login);
	}
	
	@GetMapping("/getaccount")//s
	public Response getAccountDetails(@RequestParam String userid) throws Exception
	{
		return userservice.getAccountDetails(userid);
	}

}
