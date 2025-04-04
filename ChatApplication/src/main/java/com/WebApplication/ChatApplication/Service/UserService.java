package com.WebApplication.ChatApplication.Service;

import java.util.List;


import com.WebApplication.ChatApplication.Model.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public List<User> getAllUser();
	
	public User findById(Integer userId) throws Exception;
	
	public User findByEmail(String email);
	
	public User followUser(Integer currentUser, Integer User2) throws Exception;
	
	public List<User> searchUser(String query);
	
	public User updateUser(User user,Integer id ) throws Exception;
	
	public User finduserProfileByjwt(String jwt);
	
	public void updatePassword(User user);
	
	public void sendPasswordResetEmail(User user);
	
	
	
	

}
