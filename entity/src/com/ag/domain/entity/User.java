package com.ag.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public static final Integer CashUser = 10;

	
	private Integer id;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 用户类型
	 * 10现金会员
	 * 20信用会员
	 * 30试玩会员
	 */
	private Integer userType;
	
	private String registerIp;
	private Timestamp createTime;
	
	/**
	 * 会员钱包
	 * */
	private Wallet wallet;
	
	/*非数据库字段*/
	private String checkCode;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="walletID")
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Transient
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

   
}
