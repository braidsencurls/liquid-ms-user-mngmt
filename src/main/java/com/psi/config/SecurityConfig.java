package com.psi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableAsync
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {"com.psi"})
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private AppConfig appConfig;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring()
				.antMatchers("/resources/**", "/api/**", "/events/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable().headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
		http.authorizeRequests()
				.antMatchers("/resources/**", "/user-management/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.permitAll()
				.and()
				.httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		//#SuccinctPassword
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
				.withUser("Administrator").password(appConfig.getAdministratorPassword()).roles("ADMINISTRATOR");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
