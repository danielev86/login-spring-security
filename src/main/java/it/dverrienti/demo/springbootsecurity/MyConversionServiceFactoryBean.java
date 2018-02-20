package it.dverrienti.demo.springbootsecurity;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

import it.dverrienti.demo.springbootsecurity.converter.UserBeanConverter;

public class MyConversionServiceFactoryBean extends ConversionServiceFactoryBean {
	
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		ConversionService conversionService = getObject();
		ConverterRegistry converterRegistry = (ConverterRegistry) conversionService;
		//Add converter with nested converter
		//converterRegistry.addConverter(arg0);
		converterRegistry.addConverter(new UserBeanConverter());
	}

}
