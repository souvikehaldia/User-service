package com.tcs.bankclient.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcs.bankclient.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	@Query(nativeQuery = true,value = "select * from user where email=?1 and security_answer=?2")
    public User getUserWithEmailAndSecurityAnswer(String email,String security_answer);
	
	@Query(nativeQuery = true,value = "select * from user where email=?1 and password=?2")
    public User getUserWithEmailAndPassword(String email,String password);
	
	@Query(nativeQuery = true,value = "select * from user where email=?1")
    public User getUserWithEmail(String email);
	
	@Query(nativeQuery = true,value = "select * from user where mobile=?1")
    public User getUserWithMobile(String mobile);
	
}
