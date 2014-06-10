package com.ag.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
	
	/**
	 * �û���
	 */
	private String userName;
	
	/**
	 * ����
	 */
	private String password;
	
	/**
	 * �û�����
	 * 10���ֽ��Ա
	 * 20�����û�Ա
	 * 30�������Ա
	 */
	private Integer userType;
	
	private String registerIp;
	private Timestamp createTime;
	
	/**
	 * �û�Ǯ��
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

	@OneToOne
	@PrimaryKeyJoinColumn
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
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

	@Transient
	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
   
}
