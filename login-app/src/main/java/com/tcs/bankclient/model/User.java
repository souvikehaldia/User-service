package com.tcs.bankclient.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@Column(name = "userid")
	@Getter @Setter
	private String userid;
	@Column(name = "email")
	@NotNull(message = "email can not be null")
	//@Email(message = "Enter a valid email")
	@Getter @Setter
	private String email;
	@Column(name = "username")
	@Size(min = 3,max= 20, message = "Not a valid uername in range of 3-20 letters")
	@NotNull(message = "username can not be null")
	@Getter @Setter
	private String username;
	@Column(name = "password")
	@NotNull(message = "password number can not be null")
	@Getter @Setter
	private String password;
	@Column(name = "mobile")
	//@Size(max = 10, min = 10)
	@NotNull(message = "phone number can not be null")
	@Getter @Setter
	private String mobile;
	@Column(name = "addedon")
	@CreationTimestamp
	@Getter @Setter
	private Date addedon;
	@Column(name = "lastmodified")
	@UpdateTimestamp
	@Getter @Setter
	private Date lastmodified;
	@Column(name = "dob")
	@NotNull(message = "DOB can not be null")
	@Getter @Setter
	private String dob;
	@Column(name = "status")
	@NotNull(message = "status can not be null")
	@Getter @Setter
	private int status;
	@Column(name = "type")
	@NotNull(message = "type can not be null")
	@Getter @Setter
	private int type;
	@Column(name = "lastlogin")
	@Getter @Setter
	private Date lastlogin;
	@Column(name = "security_question_id")
	@NotNull(message = "security question id can not be null")
	@Getter @Setter
	private int security_question_id;
	@Column(name = "security_answer")
	@NotNull(message = "security answer can not be null")
	@Size(min = 3,max= 20, message = "Not a valid answer")
	@Getter @Setter
	private String security_answer;
	
	
}
