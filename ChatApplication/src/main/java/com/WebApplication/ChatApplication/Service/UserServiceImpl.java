package com.WebApplication.ChatApplication.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WebApplication.ChatApplication.Config.JwtProvider;
import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	 UserRepository userRepo;

	@Override
	public User registerUser(User user) {
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setGender(user.getGender());
		
		
		return userRepo.save(newUser);
	}
	
	@Override
	public List<User> getAllUser() {
		List<User> user=userRepo.findAll();
		return user;
	}


	@Override
	public User findById(Integer userId) throws Exception {
	
		Optional<User> user=userRepo.findById(userId);
		
		if(user.isPresent())
		{
		  return user.get();
		}
		
		throw new Exception("User does not exist"); 
		
	}
	
	@Override
	public User updateUser(User user, Integer id) throws Exception {
		
		Optional<User> user1=userRepo.findById(id);//database user
		
		if(user1.isEmpty())
		{
			throw new Exception("user does not exist"+id);
		}
		
		User oldUser=user1.get();	
		
		if(user.getFirstName()!=null)
		{
			oldUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null)
		{
			oldUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null)
		{
			oldUser.setEmail(user.getEmail());
		}
		if(user.getPassword()!=null)
		{
			oldUser.setPassword(user.getPassword());
		}
		
		User updatedUser=userRepo.save(oldUser);
		
		return updatedUser;
	}
	

	@Override
	public User findByEmail(String email) {
		
		User user=userRepo.findByEmail(email);
		return user;
		
	
	}

	@Override
	public User followUser(Integer currentUser, Integer user2) throws Exception 
	{
		User currentUser1=findById(currentUser);
		
	    User sample2=findById(user2);
	    sample2. getFollowers().add(currentUser1.getId());
	    currentUser1. getFollowing().add(sample2.getId());
	    
		userRepo.save(currentUser1);
		userRepo.save(sample2);
		
		return currentUser1;
	}

	@Override
	public List<User> searchUser(String query) {
		
		
		return userRepo.SearchUser(query);
	}

	

	@Override
	public User finduserProfileByjwt(String jwt) {
		
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		User user=userRepo.findByEmail(email);
		return user;
	}

	@Override
	public void updatePassword(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPasswordResetEmail(User user) {
		// TODO Auto-generated method stub
		
	}

	
	

}
