package com.WebApplication.ChatApplication.Service;

import com.WebApplication.ChatApplication.Model.Comment;

public interface CommentService {
	
	public Comment createComment(Comment comment,Integer userId, Integer postId) throws Exception;
	
	public Comment findCommentbyid(Integer commentId) throws Exception;
	
    public Comment likeComments(Integer CommentId,Integer userId) throws Exception;	
}
