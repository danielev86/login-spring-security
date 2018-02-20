package it.dverrienti.demo.springbootsecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import it.dverrienti.demo.springbootsecurity.front.bean.GenericBean;
import it.dverrienti.demo.springbootsecurity.front.bean.UserBean;

public interface IUserService extends UserDetailsService {

	GenericBean saveUser(UserBean userBean);
	
}
