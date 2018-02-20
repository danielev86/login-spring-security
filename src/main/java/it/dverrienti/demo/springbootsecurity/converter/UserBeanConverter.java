package it.dverrienti.demo.springbootsecurity.converter;

import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import it.dverrienti.demo.springbootsecurity.front.bean.RoleBean;
import it.dverrienti.demo.springbootsecurity.front.bean.UserBean;
import it.dverrienti.demo.springbootsecurity.repository.domain.Role;
import it.dverrienti.demo.springbootsecurity.repository.domain.User;

public class UserBeanConverter implements Converter<User, UserBean> {

	@Autowired
	private ConversionService conversion;
	
	@Override
	@SuppressWarnings("unchecked")
	public UserBean convert(User source) {
		UserBean target = new UserBean();
		target.setId(source.getId());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setUsername(source.getUsername());
		target.setPassword(source.getPassword());
		target.setEmail(source.getEmail());
		if (CollectionUtils.isNotEmpty(source.getRoles())) {
			target.setRoles( (Set<RoleBean>) conversion.convert(source.getRoles()
					, TypeDescriptor.collection(Collection.class, TypeDescriptor.valueOf(Role.class))
					, TypeDescriptor.collection(Set.class, TypeDescriptor.valueOf(RoleBean.class))));
		}

		return target;
	}

}
