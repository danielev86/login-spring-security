package it.dverrienti.demo.springbootsecurity.converter;

import org.springframework.core.convert.converter.Converter;

import it.dverrienti.demo.springbootsecurity.front.bean.UserBean;
import it.dverrienti.demo.springbootsecurity.repository.domain.User;

public class UserConverter implements Converter<UserBean, User> {

	@Override
	public User convert(UserBean source) {
		User target = new User();
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setUsername(source.getUsername());
		target.setPassword(source.getPassword());
		target.setEmail(source.getEmail());
		return target;
	}



}
