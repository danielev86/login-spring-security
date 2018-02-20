package it.dverrienti.demo.springbootsecurity.utility;

import org.springframework.stereotype.Component;

import it.dverrienti.demo.springbootsecurity.front.bean.GenericBean;

@Component
public class GenericUtility {
	
	public <T extends GenericBean> void addCheckResultAndMessage(T item, Boolean check, String message) {
		item.setCheckResult(check);
		item.setMessageError(message);
	}

}
