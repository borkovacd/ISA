package com.ftn.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftn.model.Korisnik;
import com.ftn.repository.UserRepository;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Korisnik user = repository.findOneByEmail(username);
		
		if(user != null) {
				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		                	.commaSeparatedStringToAuthorityList("ROLE_" + user.getUloga());
				
				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
				// And used by auth manager to verify and check user authentication.
				return new User(user.getEmail(), user.getLozinka(), grantedAuthorities);
		}
		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
}