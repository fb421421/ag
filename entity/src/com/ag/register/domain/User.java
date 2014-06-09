package com.ag.register.domain;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
	
	private Integer id;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
   
}
