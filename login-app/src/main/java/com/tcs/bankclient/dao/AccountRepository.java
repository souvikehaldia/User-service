package com.tcs.bankclient.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tcs.bankclient.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	@Query(nativeQuery = true,value = "select * from account where userid=?1")
    public Account getAccountWithUserid(String userid);

}
