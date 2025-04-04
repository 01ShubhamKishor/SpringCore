package com.WebApplication.ChatApplication.Service;

import java.util.List;

import com.WebApplication.ChatApplication.Model.Post;

public interface PostService {
	
	Post createNewPost(Post post, Integer userId) throws Exception;
	
	String deletepost(Integer postId, Integer userId) throws Exception;
	
	Post findPostById(Integer postId) throws Exception;
	
	List<Post> findById(Integer userId) throws Exception;
	
	List<Post> findAllPost();
	
	Post savedPost(Integer postId,Integer userId) throws Exception;
	
	Post likedPost(Integer postId, Integer userId) throws Exception;
	

}
