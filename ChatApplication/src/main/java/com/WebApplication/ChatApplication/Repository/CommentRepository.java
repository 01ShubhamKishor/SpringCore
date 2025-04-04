package com.WebApplication.ChatApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebApplication.ChatApplication.Model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
