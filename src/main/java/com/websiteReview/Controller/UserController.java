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

import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto usertDto){
		return new ResponseEntity<UserDto>(this.userService.create(usertDto), HttpStatus.CREATED);
	}

	@GetMapping("/viewAll")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return new ResponseEntity<List<UserDto>>(this.userService.viewAll(), HttpStatus.OK);
	}

	@GetMapping("/viewById/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId){
		return new ResponseEntity<UserDto>(this.userService.viewById(userId),HttpStatus.ACCEPTED);
	}

	@GetMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId){
		UserDto userDto = this.userService.viewById(userId);
		this.userService.delete(userDto.getEmail());
		return new ResponseEntity<String>("User Deleted Successfully !! ", HttpStatus.OK);
	}

}
