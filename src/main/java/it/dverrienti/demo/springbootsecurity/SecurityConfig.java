package it.dverrienti.demo.springbootsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private LoggingAccessDeniedHandler logHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	protected void configure(HttpSecurity http)throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/h2-console/**")
				.permitAll()
				.antMatchers("/secure/**")
				.hasAnyRole("ADMIN","USER")
			.and()
				.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/loginApp")
					.usernameParameter("username")
					.passwordParameter("password")
					.defaultSuccessUrl("/secure/admin")
			.and()
				.logout()
	            	.invalidateHttpSession(true)
	            	.clearAuthentication(true)
	            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            	.logoutSuccessUrl("/home")   	
	         .and()
	         	.httpBasic()
	         .and()
	         	.sessionManagement()
	         .and()
	         	.exceptionHandling()
	         		.accessDeniedHandler(logHandler)
	         .and()
	         	.csrf()
	         		.disable();
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userService)
		.passwordEncoder(passwordEncoder());
	}

}
