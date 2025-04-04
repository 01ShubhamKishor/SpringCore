package com.WebApplication.ChatApplication.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.WebApplication.ChatApplication.Model.User;
import com.WebApplication.ChatApplication.Repository.UserRepository;
import com.WebApplication.ChatApplication.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/getall")
	public List<User> getAllUser()
	{
		return userService.getAllUser();
	}
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {
		
		return userService.findById(id);
	}
	
	@GetMapping("/api/user/{Email}")
    public User findByEmail(@PathVariable("Email") String email) {
		
		return userService.findByEmail(email);
	}
	
	@PutMapping("/api/user")
    public User updateUser(@RequestHeader("Authorization") String jwt,@RequestBody User user) throws Exception {
		
		User currentuser=userService.finduserProfileByjwt(jwt);
		return userService.updateUser(user, currentuser.getId());
	}
	
	@PutMapping("/api/user/follow/{user2}")
	public User followUser(@RequestHeader("Authorization") String jwt,@PathVariable Integer user2) throws Exception 
	{
		User currentuser=userService.finduserProfileByjwt(jwt);
		return userService.followUser(currentuser.getId(), user2);
	}
	
	@GetMapping("api/user/search")
	public List<User> searchUser(@RequestParam String query)
	{
		return userService.searchUser(query);
	}
	
	@GetMapping("/api/user/profile")
	public User getUserfromToken(@RequestHeader("Authorization") String jwt)
	{
	   User user=userService.finduserProfileByjwt(jwt);
	   user.setPassword(null);
		return user;
	}
	

}
