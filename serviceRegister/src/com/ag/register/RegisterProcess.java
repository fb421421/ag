package com.ag.register;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.ag.register.data.ResultMessage;
import com.ag.register.domain.User;
import com.ag.register.exception.InvalidUserNameFormatException;

@Stateless
@LocalBean
@Path("/user")
public class RegisterProcess {
	
	@PersistenceContext(unitName="entity")
	private EntityManager entityManager;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/start")
	public ResultMessage startRegisterProcess( User user ){
		
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setStatus(ResultMessage.Error);
		resultMessage.setErrorMessage(ResultMessage.UNKNOWN_ERROR);
		
		try {
			
			/*用户注册信息验证*/
			RegisterRule.validteUserName(user.getUserName());
			
			/*创建用户*/
			entityManager.persist(user);
			
		} catch (InvalidUserNameFormatException e) {
			resultMessage.setStatus(ResultMessage.Error);
			resultMessage.setErrorMessage(e.getClass().getSimpleName());
		}
	
		
		return resultMessage;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	} 
	
	
}
