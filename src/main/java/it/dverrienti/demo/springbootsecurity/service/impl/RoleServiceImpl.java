package it.dverrienti.demo.springbootsecurity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import it.dverrienti.demo.springbootsecurity.front.bean.RoleBean;
import it.dverrienti.demo.springbootsecurity.repository.IRoleRepository;
import it.dverrienti.demo.springbootsecurity.repository.domain.Role;
import it.dverrienti.demo.springbootsecurity.service.IRoleService;

public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private ConversionService converter;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	public RoleBean getRole(String roleName) {
		Role role = roleRepository.findRole(roleName);	
		
		RoleBean roleBean = converter.convert(role, RoleBean.class);
		return roleBean;
	}

}
