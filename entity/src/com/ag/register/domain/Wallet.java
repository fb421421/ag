package com.ag.register.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Wallet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5918588165810616189L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * ’Àªß”‡∂Ó
	 */
	private BigDecimal balance;
	
	private User user;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@OneToOne
	@PrimaryKeyJoinColumn
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
