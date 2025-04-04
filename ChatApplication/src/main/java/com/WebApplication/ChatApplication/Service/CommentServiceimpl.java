package com.WebApplication.ChatApplication.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WebApplication.ChatApplication.Model.Comment;
import com.WebApplication.ChatApplication.Model.Post;
import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Repository.CommentRepository;
import com.WebApplication.ChatApplication.Repository.PostRepository;

@Service
public class CommentServiceimpl implements CommentService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Override
	public Comment createComment(Comment comment, Integer userId, Integer postId) throws Exception {
		 
		
		User user=userService.findById(userId);
		Post post=postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		
	    Comment newComment=commentRepo.save(comment);
	    
	    post.getComment().add(newComment);
	    
	     postRepo.save(post);
		
		return newComment;
	}

	@Override
	public Comment findCommentbyid(Integer commentId) throws Exception {
		Optional<Comment> opt=commentRepo.findById(commentId);
		
		if(opt.isEmpty())
		{
			throw new Exception("Comment Not Exit In this ID"+commentId);
		}
	
			return opt.get();
	
		
	}

	@Override
	public Comment likeComments(Integer commentId, Integer userId) throws Exception {
	  Comment comment= findCommentbyid(commentId);
	   
	  User user= userService.findById(userId);
	  
	  if(!comment.getLiked().contains(user))
	  {
		  comment.getLiked().add(user);
	  }
	  else {
		  comment.getLiked().remove(user);
	  }
	   
		return commentRepo.save(comment);
	}

}
