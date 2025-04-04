package com.WebApplication.ChatApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.WebApplication.ChatApplication.Model.Comment;
import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Service.CommentService;
import com.WebApplication.ChatApplication.Service.UserService;

@RestController
public class CommentConroller {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comment/post/{postId}")
	public Comment createComment(@RequestBody Comment comment, 
			@RequestHeader("Authorization") String jwt,
			@PathVariable("postId") Integer postId )
					throws Exception{
		User user=userService.finduserProfileByjwt(jwt);
		
		Comment comment1=commentService.createComment(comment ,user.getId(),postId);
		return comment1;
	}
	
	@PutMapping("api/comment/like/{commentId}")
	public Comment likeComment(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer commentId) throws Exception {
		
		User user=userService.finduserProfileByjwt(jwt);
		
		Comment createdCommented=commentService.likeComments(commentId, user.getId());
		
		
		return createdCommented ;
	}
	
	@GetMapping("/comment/{commentId}")
	public Comment findCommentbyid(@PathVariable Integer commentId) throws Exception {
			return commentService.findCommentbyid(commentId);	
	}
	
	

}
