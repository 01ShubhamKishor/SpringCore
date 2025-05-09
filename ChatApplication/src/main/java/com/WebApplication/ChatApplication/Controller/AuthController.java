package com.WebApplication.ChatApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.WebApplication.ChatApplication.Config.JwtProvider;
import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Repository.UserRepository;
import com.WebApplication.ChatApplication.Service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetails;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist=userRepo.findByEmail(user.getEmail());
		if(isExist !=null)
		{
			throw new Exception("User already exist");
		}
		
		User newUser=new User();
		
		
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setGender(user.getGender());
		
		
		User saveuser=userRepo.save(newUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(saveuser.getEmail(),saveuser.getPassword());
		String token=JwtProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token, "Register Succesfull");
		return res;
	}
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest)
	{
		Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		String token=JwtProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token, "Login Success");
		return res;
	}
     
	
	private Authentication authenticate(String email, String password) {
		UserDetails userDetails=customUserDetails.loadUserByUsername(email);
		if(userDetails==null)
		{
			throw new BadCredentialsException("invalid username...");
		}
		if(!passwordEncoder.matches(password,userDetails.getPassword())){
			{
				throw new BadCredentialsException("Password not match");
			}
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
