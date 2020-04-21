package com.tcs.bankclient.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.bankclient.dao.AccountRepository;
import com.tcs.bankclient.dao.Question_tableRepository;
import com.tcs.bankclient.dao.UserRepository;
import com.tcs.bankclient.model.User;
import com.tcs.bankclient.utitlities.AppUtilities;
import com.tcs.bankclient.model.Account;
import com.tcs.bankclient.model.Login;
import com.tcs.bankclient.model.Question_table;
import com.tcs.bankclient.model.Response;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository dao;
	
	@Autowired
	private AccountRepository accountdao;
	
	@Autowired
	private Question_tableRepository questionRepo;
	
	@Override
	public Response getAllRecords()throws Exception {
		
		List<User> record=dao.findAll()
				.stream().filter(user -> user.getType()==0)
				.peek(u -> u.setPassword(AppUtilities.stringDecription(u.getPassword())))
				.collect(Collectors.toList());
		/////////////////convert json to string//////////////
	        String jsonStr=AppUtilities.jsonToString(record);
	    /////////////////////////////////////////////////////
	       
	        return new Response(true, jsonStr);
	}


	@Override
	public Response saveUser( User user)throws Exception {
		///////check if user already exists//////////
		User checkEmail=dao.getUserWithEmail(user.getEmail());
		User checkMobile=dao.getUserWithMobile(user.getMobile());
		String errorMessage="";
		Response res=new Response();
		if(checkEmail!=null && checkMobile!=null)
		{
			errorMessage=errorMessage+"Email or Mobile No. already in use /";
		}
		else
		{
			Account account=new Account();
			String userid=AppUtilities.userEncrypt(user.getUsername());
			user.setUserid(userid);
			user.setStatus(1);
			
			//save current login date
			Date now=new Date();
			user.setLastlogin(now);
			
			String validationMessage=AppUtilities.validationParameters(user);
			if(validationMessage.equals("Verification Passed"))
			{
				
				//encript password
				String encrPassword=AppUtilities.stringEncription(user.getPassword());
			    user.setPassword(encrPassword);
				//////////////
			    
				if(user.getType()==0)
				{
					account.setBalance(5000.00);
					account.setUser(user);
					
					Account acc=accountdao.save(account);
					if(acc==null)  //break
					{
						errorMessage=errorMessage+"Data not saved try again /";
					}
				}
				
				else
				{
					User emp=dao.save(user);
					if(emp==null)
					{
						errorMessage=errorMessage+"Data not saved try again /";
					}
				}
			}
			
			else
			{
				errorMessage=errorMessage+validationMessage;
			}
		}
		
		if(errorMessage.equals(""))
		{
			res.setSuccess(true);
			res.setResult("Successfully Registered");
		}
		else
		{
			res.setSuccess(false);
			res.setResult(errorMessage);
		}
		return res;		
	}


	@Override
	public Response checkLogin(Login login) throws Exception {
		
		String encppassword=AppUtilities.stringEncription(login.getPassword());
		User user=dao.getUserWithEmailAndPassword(login.getEmail(), encppassword);
		if(user==null) {
			return new Response(false, "Email or Password is wrong");
		}
		else {
			//LocalDateTime now = LocalDateTime.now();
			Date now=new Date();
			user.setLastlogin(now);
		
			dao.save(user);
			String jsonStr=AppUtilities.jsonToString(user);
			return new Response(true, jsonStr);
			
		}
		
		
	}


	@Override
	public List<User> testgetAllRecords() {
		List<User> record=new ArrayList<User>();
		dao.findAll().stream().forEach(record::add);
		
		
		return record;
	}
	
	@Override
	public Response changePassword(Login login) throws Exception {
		
		User user=dao.getUserWithEmailAndSecurityAnswer(login.getEmail(), login.getSecurity_answer());
		if(user==null)
		{
			return new Response(false, "Not Updated");
		}
		
		
		else
		{
			User emp=null;
			String newPassword=login.getPassword();
			String passwordValidation=AppUtilities.validationPassword(newPassword);
			if(passwordValidation.equals("Passed"))
			{
				String encpPassword=AppUtilities.stringEncription(newPassword);
				user.setPassword(encpPassword);
				emp=dao.save(user);
			}
			
			if(emp!=null)
				return new Response(true, "Successfully updated password");
			else
				return new Response(false, "Data not saved try again");
		}
	}


	@Override
	public Response getSecurityQuestion(String email) {

		boolean success=false;
		Question_table testQuestionObj=new Question_table();
		String result="Email not found ";
	///////check if use already exists//////////
		User userObj=dao.getUserWithEmail(email);
		if(!(userObj==null))
		{
				success=true;
				Question_table que= questionRepo.findById(userObj.getSecurity_question_id()).orElse(testQuestionObj);
				result=que.getSecurity_question();
		}
		return new Response(success,result);
	}


	@Override
	public Response getAccountDetails(String userid) throws Exception {
		Account account=null;
		String jsonStr="Account not active for this user";
		boolean result=false;
		account=accountdao.getAccountWithUserid(userid);
		if(account!=null)
		{
			result=true;
			jsonStr=AppUtilities.jsonToString(account);
		}
		return new Response(result,jsonStr);
	}


	@Override
	public Response getUserById(String userid) throws Exception {
		User testUser=new User();
		String jsonString="";
		boolean success=false;
		User user=dao.findById(userid).orElse(testUser);
		if(user==null)
		{
			jsonString="User Not found";
		}
		else
		{
			user.setPassword(AppUtilities.stringDecription(user.getPassword()));
			jsonString=AppUtilities.jsonToString(user);
			success=true;
		}
		
		return new Response(success, jsonString);
	}


	@Override
	public Response getAllAccounts() throws Exception {
		List<Account> accountRecords=accountdao.findAll().stream().collect(Collectors.toList());
		String jsonString="";
		boolean success=false;
		if(accountRecords==null)
		{
			jsonString="No accounts to display";
		}
		else
		{
			jsonString=AppUtilities.jsonToString(accountRecords);
			success=true;
		}
		return new Response(success,jsonString);
	}


	@Override
	public Response getAccountById(Integer accountid) throws Exception {
		Account testAccount=new Account();
		String jsonString="";
		boolean success=false;
		Account account=accountdao.findById(accountid).orElse(testAccount);
		if(account==null)
		{
			jsonString="Account Not found";
		}
		else
		{
			jsonString=AppUtilities.jsonToString(account);
			success=true;
		}
		
		return new Response(success, jsonString);
	}


	@Override
	public Response updateUser(Login user) {
		boolean result=false;
		User testUser=new User();
		User newUser=dao.findById(user.getUserid()).orElse(testUser);
		newUser.setUsername(user.getUsername());
		newUser.setDob(user.getDob());
		newUser.setPassword(AppUtilities.stringEncription(user.getPassword()));
		newUser.setSecurity_answer(user.getSecurity_answer());
		newUser.setSecurity_question_id(user.getSecurity_question_id());
		newUser.setStatus(user.getStatus());
		if(dao.getUserWithEmail(user.getEmail())==null)
		{
			newUser.setEmail(user.getEmail());
		}
		if(dao.getUserWithMobile(user.getMobile())==null)
		{
			newUser.setMobile(user.getMobile());
		}
		User savedUser=dao.save(newUser);
		if(savedUser!=null)
		{
			result=true;
		}
		if(result)
		{
			return new Response(result,"Successfully updated");
		}
		else
		{
			return new Response(result,"Can't Update contact support");
		}
		
	}


	 

}
