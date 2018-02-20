package it.dverrienti.demo.springbootsecurity.converter;

import org.springframework.core.convert.converter.Converter;

import it.dverrienti.demo.springbootsecurity.front.bean.RoleBean;
import it.dverrienti.demo.springbootsecurity.repository.domain.Role;

public class RoleBeanConverter implements Converter<Role, RoleBean> {

	@Override
	public RoleBean convert(Role source) {
		RoleBean target = new RoleBean();
		target.setId(source.getId());
		target.setRoleName(source.getRoleName());
		return target;
	}

}
