package org.ernest.applications.bt.gui.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthenticationProvider authenticationProvider;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
        http.authorizeRequests()
            	.antMatchers("/profile/**").authenticated()
            	.antMatchers("/members/**").authenticated()
            	.antMatchers("/member/**").authenticated()
            	.antMatchers("/stages**").authenticated()
            	.antMatchers("/stage/**").authenticated()
            	.antMatchers("/home/**").authenticated()
            	.antMatchers("/team/**").authenticated()
            	.antMatchers("/teams/**").authenticated()
                .and()
            .csrf().disable()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home")
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

}
