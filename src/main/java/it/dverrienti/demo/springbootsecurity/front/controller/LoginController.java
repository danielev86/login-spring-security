package it.dverrienti.demo.springbootsecurity.front.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.dverrienti.demo.springbootsecurity.front.bean.GenericBean;
import it.dverrienti.demo.springbootsecurity.front.bean.UserBean;
import it.dverrienti.demo.springbootsecurity.front.dto.UserForm;
import it.dverrienti.demo.springbootsecurity.front.validator.UserValidator;
import it.dverrienti.demo.springbootsecurity.service.IUserService;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	@Qualifier("conversionService")
	private ConversionService converter;

	@GetMapping(value="/secure/admin")
	public String admin() {
		return "/secure/admin";
	}
	
	@GetMapping(value="/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping(value="/registration")
	public String showRegistration(ModelMap model) {
		model.put("userForm", new UserForm() );
		return "/registration";
	}
	
	@PostMapping("/saveUser")
	public String registration(@Valid @ModelAttribute("userForm") UserForm userForm
			, BindingResult bindingResult
			, ModelMap model) {
		model.put("userForm", userForm);
		if (bindingResult.hasErrors()) {
			return "/registration";
		}
		
			
		UserBean userBean = converter.convert(userForm, UserBean.class);
		GenericBean genericBean = userService.saveUser(userBean);
		
		if (!genericBean.getCheckResult()) {
			bindingResult.reject(genericBean.getMessageError());
		}
		return "/login";
		
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

}
