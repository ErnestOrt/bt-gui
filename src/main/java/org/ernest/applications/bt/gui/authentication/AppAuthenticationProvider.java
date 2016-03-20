package org.ernest.applications.bt.gui.authentication;

import java.util.ArrayList;
import java.util.List;

import org.ernest.applications.bt.gui.services.AuthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AppAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	AuthDataService authDataService;

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String name = auth.getName();
        String password = auth.getCredentials().toString();
        
        String id = authDataService.validate(name, password);
        if (id!=null) {
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication authret = new UsernamePasswordAuthenticationToken(id, password, grantedAuths);
            
            return authret;
        } else {
            return null;
        }
	}

	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}