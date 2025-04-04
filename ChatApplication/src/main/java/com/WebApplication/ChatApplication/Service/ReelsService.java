package com.WebApplication.ChatApplication.Service;

import java.util.List;

import com.WebApplication.ChatApplication.Model.Reels;
import com.WebApplication.ChatApplication.Model.User;

public interface ReelsService {
	
	public Reels createReel(Reels reels,User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(Integer userId) throws Exception;

}
