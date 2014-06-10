package com.ag.register;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.logging.Logger;

import com.ag.domain.data.ResultMessage;
import com.ag.domain.entity.User;
import com.ag.domain.exception.InvalidUserNameFormatException;
import com.ag.domain.exception.NullPasswordException;
import com.ag.domain.exception.NullUserNameException;


@Stateful
@LocalBean
@Path("/user")
public class RegisterProcess {
	
	private static final Logger logger = Logger.getLogger(ResultMessage.class);
	
	@PersistenceContext(unitName="entity")
	private EntityManager entityManager;
	
	private String checkCode;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public ResultMessage startRegisterProcess( User user ){
		
		
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setStatus(ResultMessage.SUCCESS);
		
		try {
			
			/*用户注册信息验证*/
			RegisterRule.validteUserName(user.getUserName());
			RegisterRule.validtePassword(user.getPassword());
			
			/*创建用户*/
			entityManager.persist(user);
			
		}catch (NullUserNameException e) {
			logger.error(e);
			resultMessage.setStatus(ResultMessage.Error);
			resultMessage.setErrorMessage(e.getClass().getSimpleName());
		}catch (InvalidUserNameFormatException e) {
			logger.error(e);
			resultMessage.setStatus(ResultMessage.Error);
			resultMessage.setErrorMessage(e.getClass().getSimpleName());
		} catch (NullPasswordException e) {
			logger.error(e);
			resultMessage.setStatus(ResultMessage.Error);
			resultMessage.setErrorMessage(e.getClass().getSimpleName());
		} 
	
		
		return resultMessage;
	}
	

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public ResultMessage getCheckCode( ){
		this.checkCode=r
	}
		

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	} 
	
	
}
