package com.websiteReview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Model.User;
import com.websiteReview.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<User>(this.userService.createUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/view")
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(this.userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/viewById/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId){
		return new ResponseEntity<User>(this.userService.getUserById(userId),HttpStatus.ACCEPTED);
	}

	@GetMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		return new ResponseEntity<String>("User Deleted Successfully !! ", HttpStatus.OK);
	}

}
