package com.WebApplication.ChatApplication.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.WebApplication.ChatApplication.Model.Post;
import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Service.PostService;
import com.WebApplication.ChatApplication.Service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/post/user")
	public ResponseEntity<Post> createPost(@RequestHeader("Authorization")String jwt,@org.springframework.web.bind.annotation.RequestBody Post post) throws Exception
	{
		User Currentuser= userService.finduserProfileByjwt(jwt);
		Post createdPost=postService.createNewPost(post, Currentuser.getId());

		return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<String> deletePost(@RequestHeader("Authorization")String jwt,@PathVariable Integer postId) throws Exception
	{      
		   User Currentuser= userService.finduserProfileByjwt(jwt);
	       String msg= postService.deletepost(postId,Currentuser.getId());
           
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public  ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception
	{
	       Post post = postService.findPostById(postId);

		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@GetMapping("/user/post/{userId}")
	 public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Integer userId) throws Exception
	{
		
		List<Post> post=postService.findById(userId);
		return new ResponseEntity<>(post,HttpStatus.OK);	
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost()
	{
		
	     List<Post> post=postService.findAllPost();
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@PutMapping("post/user/{postId}")
	public ResponseEntity<Post> savedPost(@RequestHeader("Authorization")String jwt, @PathVariable Integer postId ) throws Exception{
		
		User Currentuser= userService.finduserProfileByjwt(jwt);
		Post post=postService.savedPost(postId, Currentuser.getId());
		
		return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("post/like/{postId}")
	public ResponseEntity<Post> likedPost(@RequestHeader("Authorization")String jwt, @PathVariable Integer postId ) throws Exception{
		User Currentuser= userService.finduserProfileByjwt(jwt);
		Post post=postService.likedPost(postId, Currentuser.getId());
		
		return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
	}

}
