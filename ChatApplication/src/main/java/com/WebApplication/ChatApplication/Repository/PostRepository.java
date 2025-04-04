package com.WebApplication.ChatApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.WebApplication.ChatApplication.Model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query("select p from Post p where p.user.id=:userId")
	List<Post> findPostByuserId(Integer userId);
}
