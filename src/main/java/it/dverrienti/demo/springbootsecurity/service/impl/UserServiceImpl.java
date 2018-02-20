package it.dverrienti.demo.springbootsecurity.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.dverrienti.demo.springbootsecurity.constants.IConstants;
import it.dverrienti.demo.springbootsecurity.front.bean.GenericBean;
import it.dverrienti.demo.springbootsecurity.front.bean.UserBean;
import it.dverrienti.demo.springbootsecurity.repository.IRoleRepository;
import it.dverrienti.demo.springbootsecurity.repository.IUserRepository;
import it.dverrienti.demo.springbootsecurity.repository.domain.Role;
import it.dverrienti.demo.springbootsecurity.repository.domain.User;
import it.dverrienti.demo.springbootsecurity.service.IUserService;
import it.dverrienti.demo.springbootsecurity.utility.GenericUtility;

@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	@Qualifier("conversionService")
	private ConversionService converter;
	
	@Autowired
	private GenericUtility genericUtility;
	
	@Autowired
	private BCryptPasswordEncoder passwdEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			logger.info("There is no user with this username");
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername()
				, user.getPassword()
				, mapRolesToAuthority(user));
	}
	
	public GenericBean saveUser(UserBean userBean) {
		User user = converter.convert(userBean, User.class);
		GenericBean genericBean = new GenericBean();
		try {
			user.setPassword(passwdEncoder.encode(user.getPassword()));
			List<Role> roles = new ArrayList<>();
			roles.add(getRoleByRoleName(IConstants.ROLE_USER_CODE));
			user.setRoles(roles);
			userRepository.save(user);
			genericUtility.addCheckResultAndMessage(genericBean, Boolean.TRUE, null);
		}catch(Exception e) {
			logger.error("Cannot save user data "+e.getMessage(),e);
			genericUtility.addCheckResultAndMessage(genericBean, Boolean.FALSE, "error.user.save.registration");
		}
		return genericBean;
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthority(User user){
		return user.getRoles()
				.stream()
				.map(role-> new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toList());
	}
	
	private Role getRoleByRoleName(String roleName) {
		return roleRepository.findRole(roleName);
	}
	

}
