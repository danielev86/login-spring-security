package it.dverrienti.demo.springbootsecurity.service;

import it.dverrienti.demo.springbootsecurity.front.bean.RoleBean;

public interface IRoleService {
	
	RoleBean getRole(String roleName);

}
