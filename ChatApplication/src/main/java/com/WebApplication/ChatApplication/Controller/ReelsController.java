package com.WebApplication.ChatApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.WebApplication.ChatApplication.Model.Reels;
import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Service.ReelsService;
import com.WebApplication.ChatApplication.Service.UserService;

@RestController
public class ReelsController {
	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reels ,
			@RequestHeader("Authorization") String jwt)
	{
		User jwtuser=userService.finduserProfileByjwt(jwt);
		Reels createReels=reelsService.createReel(reels,jwtuser);
		
		return createReels;
	}
	
	@GetMapping("/api/reels/all")
	public List<Reels> findAllReels()
	{
		return reelsService.findAllReels();
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReels(Integer userId) throws Exception
	{
		return reelsService.findUserReels(userId);
	}
	


}
