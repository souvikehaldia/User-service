package com.tcs.bankclient.model;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Login {
	@Getter @Setter
	private String userid;
	@Getter @Setter
	private String email;
	@Getter @Setter
	private String username;
	@Getter @Setter
	private String password;
	@Getter @Setter
	private String mobile;
	@Getter @Setter
	private Date addedon;
	@Getter @Setter
	private Date lastmodified;
	@Getter @Setter
	private String dob;
	@Getter @Setter
	private int status;
	@Getter @Setter
	private int type;
	@Getter @Setter
	private LocalDateTime lastlogin;
	@Getter @Setter
	private int security_question_id;
	@Getter @Setter
	private String security_answer;
}
