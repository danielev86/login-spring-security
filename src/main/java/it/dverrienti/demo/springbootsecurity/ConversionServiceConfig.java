package it.dverrienti.demo.springbootsecurity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import it.dverrienti.demo.springbootsecurity.converter.RoleBeanConverter;
import it.dverrienti.demo.springbootsecurity.converter.UserConverter;
import it.dverrienti.demo.springbootsecurity.converter.UserFormToBeanConverter;

@Configuration
public class ConversionServiceConfig {
	
	@Bean
	public ConversionService conversionService() {
		MyConversionServiceFactoryBean bean = new MyConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();
		ConversionService object = bean.getObject();
		return object;
	}
	
	private Set<Converter> getConverters(){
		Set<Converter> converters = new HashSet<>();
		converters.add(new RoleBeanConverter());
		converters.add(new UserFormToBeanConverter());
		converters.add(new UserConverter());
		return converters;
	}

}
