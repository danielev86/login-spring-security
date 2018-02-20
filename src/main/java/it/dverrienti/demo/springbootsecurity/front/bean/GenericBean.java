package it.dverrienti.demo.springbootsecurity.front.bean;

import java.io.Serializable;

public class GenericBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean checkResult;
	
	private String messageError;

	public Boolean getCheckResult() {
		return checkResult;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setCheckResult(Boolean checkResult) {
		this.checkResult = checkResult;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
}
