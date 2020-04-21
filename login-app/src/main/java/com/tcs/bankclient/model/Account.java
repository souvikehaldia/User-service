package com.tcs.bankclient.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
	@Column(name = "accountid")
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Getter @Setter
	private Integer accountid;
	
	@Column(name = "balance")
	@NotNull(message = "balance can not be null")
	@Getter @Setter
	private double balance;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid",referencedColumnName = "userid")
	@Getter @Setter
	private User user;

	
	
}
