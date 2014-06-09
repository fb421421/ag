package com.ag.register;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ag.register.domain.User;

@Stateless
@LocalBean
@Path("/user")
public class RegisterProcess {
	
	@PersistenceContext(unitName="entity")
	private EntityManager entityManager;
	
	@POST
	@GET
	@Produces("application/json")
	@Path("/create")
	public String createUser(){
		
		User user = new User();
		entityManager.persist(user);
		
		return "success";
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	} 
	
	
}
