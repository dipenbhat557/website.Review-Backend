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

import com.websiteReview.Helper.UserDto;
import com.websiteReview.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto usertDto){
		return new ResponseEntity<UserDto>(this.userService.createUser(usertDto), HttpStatus.CREATED);
	}

	@GetMapping("/view")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/viewById/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId){
		return new ResponseEntity<UserDto>(this.userService.getUserById(userId),HttpStatus.ACCEPTED);
	}

	@GetMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		return new ResponseEntity<String>("User Deleted Successfully !! ", HttpStatus.OK);
	}

}
