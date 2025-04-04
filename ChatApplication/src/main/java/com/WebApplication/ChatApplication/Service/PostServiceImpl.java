package com.WebApplication.ChatApplication.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.WebApplication.ChatApplication.Model.Post;
import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Repository.PostRepository;
import com.WebApplication.ChatApplication.Repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	
    @Autowired
	PostRepository postRepo;
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserRepository userRepo;
	
	
	@Override
	public Post createNewPost(Post post,Integer userId) throws Exception {
		 User user=userService.findById(userId);
		 
	    Post newpost=new Post();
	    newpost.setCaption(post.getCaption());
	   
	    newpost.setImage(post.getImage());
	   
	    newpost.setVideo(post.getVideo());
	   
	    newpost.setCreatedtime(LocalDateTime.now());
	    newpost.setUser(user); 
	    
	    
	    
		return postRepo.save(newpost);

	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		
		Optional<Post> post1=postRepo.findById(postId);
		if(post1.isEmpty())
		{
			throw new Exception("post not avilable with id"+postId);
		}
		else {
			return post1.get();
		}
	}
      
	@Override
	public String deletepost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId);
		User user=userService.findById(userId);
		
		if( post.getUser().getId()!=user.getId())
		{
			throw new Exception("you can't delete this post ");
		}
		else {
			postRepo.delete(post);
		}
		return "Deleted Suceesfully";
	}
	
	@Override
	public List<Post> findById(Integer userId) throws Exception {

		return postRepo.findPostByuserId(userId);
	}
	@Override
	public List<Post> findAllPost() {
		    
		return  postRepo.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		
		Post post=findPostById(postId);
		User user=userService.findById(userId);
		
		if(user.getSaved().contains(post))
		{
			user.getSaved().remove(post);
		}
		else {
			user.getSaved().add(post);
		}
		userRepo.save(user);
		return post;
	}

	@Override
	public Post likedPost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId);
		User user= userService.findById(userId);
		
		if(post.getLiked().contains(user))
		{
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		return postRepo.save(post);
	}

	

}
