package com.ag.domain.data;

import java.io.Serializable;

/**
 * 
 * @author fb421
 * process调用返回结果，包含状态码 和错误信息
 */
public class ResultMessage implements Serializable {
	
	public static Integer Error=0;
	public static Integer SUCCESS=1;
	
	public static String UNKNOWN_ERROR="UNKNOWN_ERROR";

	/**
	 * 
	 */
	private static final long serialVersionUID = 3716636876495303111L;

	/**
	 * 工作流状态
	 */
	private Integer status;
	
	/**
	 * 错误信息
	 */
	private String errorMessage;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
