package it.dverrienti.demo.springbootsecurity.converter;

import org.springframework.core.convert.converter.Converter;

import it.dverrienti.demo.springbootsecurity.front.bean.UserBean;
import it.dverrienti.demo.springbootsecurity.front.dto.UserForm;

public class UserFormToBeanConverter implements Converter<UserForm, UserBean> {

	@Override
	public UserBean convert(UserForm source) {
		UserBean target = new UserBean();
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setUsername(source.getUsername());
		target.setPassword(source.getPassword());
		target.setEmail(source.getEmail());
		return target;
	}

}
