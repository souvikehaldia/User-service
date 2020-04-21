package com.tcs.bankclient.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "question_table")
@NoArgsConstructor
@AllArgsConstructor
public class Question_table {
	
	
		
		@Id
		@Column(name = "security_question_id")
		@Getter @Setter
		private Integer security_question_id;
		
		@Column(name="security_question")
		@Getter @Setter
		private String security_question;
		
}
