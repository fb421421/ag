package com.ag.register;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.jboss.logging.Logger;

import com.ag.domain.data.ResultMessage;
import com.ag.domain.entity.User;
import com.ag.domain.entity.Wallet;
import com.ag.domain.exception.InvalidCheckCodeException;
import com.ag.domain.exception.InvalidUserNameFormatException;
import com.ag.domain.exception.NullCheckCodeException;
import com.ag.domain.exception.NullPasswordException;
import com.ag.domain.exception.NullUserNameException;
import com.ag.domain.util.DateUtil;
import com.ag.domain.util.DigestUtil;
import com.ag.domain.util.IpUtil;
import com.ag.domain.util.RSAUtils;


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
			
			/*检查密码是否经过AES加密，如果有加密则解密*/
			user.setPassword(RSAUtils.checkAndDecrypt(user.getPassword()));
			
			/*用户注册信息验证*/
			RegisterRule.validteCheckCode(request, user.getCheckCode());
			RegisterRule.validteUserName(user.getUserName());
			RegisterRule.validtePassword(user.getPassword());
			
			/*加密密码*/
			user.setPassword(DigestUtil.sha256_base64(user.getPassword()));
			
			/*记录IP*/
			user.setRegisterIp(IpUtil.getIp(request));
			
			/*添加钱包*/
			Wallet wallet = new Wallet();
			user.setWallet(new Wallet());
			
			/*保存用户*/
			user.setCreateTime(DateUtil.getCurrentTimestamp());
			user.setUserType(User.CashUser);
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
	

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	public User  getUser( ){
		return entityManager.find(User.class, 1);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
}
