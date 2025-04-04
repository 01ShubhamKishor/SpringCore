package com.WebApplication.ChatApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebApplication.ChatApplication.Model.Reels;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {
	
	public List<Reels> findByuserId(Integer UserId);

}
