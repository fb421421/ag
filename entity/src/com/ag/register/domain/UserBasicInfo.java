package com.ag.register.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserBasicInfo
 *
 */
@Entity

public class UserBasicInfo implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public UserBasicInfo() {
		super();
	}
	
	@Id
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
   
}
