package com.tcs.bankclient.service;

import java.util.List;

import com.tcs.bankclient.model.Login;
import com.tcs.bankclient.model.Response;
import com.tcs.bankclient.model.User;

public interface UserService {
	public Response getAllRecords()throws Exception;
	public Response saveUser( User user)throws Exception;
	public Response checkLogin(Login login) throws Exception;
	public List<User> testgetAllRecords();
	public Response changePassword(Login login) throws Exception;
	public Response getSecurityQuestion(String email);
	public Response getAccountDetails(String userid)throws Exception;
	public Response getUserById(String userid) throws Exception;
	public Response getAllAccounts() throws Exception;
	public Response getAccountById(Integer accountid) throws Exception;
	public Response updateUser(Login user);

}
