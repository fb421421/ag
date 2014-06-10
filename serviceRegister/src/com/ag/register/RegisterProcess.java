package com.ag.register;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.jboss.logging.Logger;

import com.ag.domain.data.ResultMessage;
import com.ag.domain.entity.User;
import com.ag.domain.exception.InvalidCheckCodeException;
import com.ag.domain.exception.InvalidUserNameFormatException;
import com.ag.domain.exception.NullCheckCodeException;
import com.ag.domain.exception.NullPasswordException;
import com.ag.domain.exception.NullUserNameException;


@Stateless
@LocalBean
@Path("/user")
public class RegisterProcess {
	
	private static final Logger logger = Logger.getLogger(ResultMessage.class);
	
	@PersistenceContext(unitName="entity")
	private EntityManager entityManager;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public ResultMessage startRegisterProcess(@Context HttpServletRequest request, User user ){
		
		
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setStatus(ResultMessage.SUCCESS);
		
		try {
			
			/*用户注册信息验证*/
			RegisterRule.validteCheckCode(request, user.getCheckCode());
			RegisterRule.validteUserName(user.getUserName());
			RegisterRule.validtePassword(user.getPassword());
			
			/*保存用户*/
			entityManager.persist(user);
			
		} catch (NullCheckCodeException e) {
			logger.error(e);
			resultMessage.setStatus(ResultMessage.Error);
			resultMessage.setErrorMessage(e.getClass().getSimpleName());
		} catch (InvalidCheckCodeException e) {
			logger.error(e);
			resultMessage.setStatus(ResultMessage.Error);
			resultMessage.setErrorMessage(e.getClass().getSimpleName());
		} catch (NullUserNameException e) {
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
	

		

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
}
