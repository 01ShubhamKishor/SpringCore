package com.WebApplication.ChatApplication.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		
		User user=userRepo.findByEmail(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("user doesn't exist"+username);
		}
		List<GrantedAuthority> authorities =new ArrayList<>();
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	



	}
	
}
