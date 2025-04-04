package com.WebApplication.ChatApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WebApplication.ChatApplication.Model.Reels;
import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Repository.ReelsRepository;

@Service
public class ReelsServiceImpl implements ReelsService {

	@Autowired
	private ReelsRepository reelsRepo;
	
	@Autowired
	private UserService userService;
	
	public Reels createReel(Reels reels, User user) {
		
		Reels createReel=new Reels();
		createReel.setTitle(reels.getTitle());
		createReel.setVideo(reels.getVideo());
		createReel.setUser(user);
		
		return reelsRepo.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
		
		return null;
	}

	@Override
	public List<Reels> findUserReels(Integer userId) throws Exception {
		userService.findById(userId);
		
		return reelsRepo.findByuserId(userId);
	}

}
