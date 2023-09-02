package com.websiteReview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Helper.UserRequest;
import com.websiteReview.Security.CurrentUser;
import com.websiteReview.Security.UserPrincipal;
import com.websiteReview.ServiceImpl.FileUploadServiceImpl;
import com.websiteReview.ServiceImpl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private FileUploadServiceImpl fileUploadService;

	private String path = "src/user/profiles";

	// @PostMapping("/create")
	// public ResponseEntity<UserDto> createUser(@RequestBody UserRequest
	// userRequest) {
	// return new ResponseEntity<UserDto>(this.userService.create(userRequest),
	// HttpStatus.CREATED);
	// }

	@GetMapping
	@Operation(security = { @SecurityRequirement(name = "BearerJWT") })
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return new ResponseEntity<List<UserDto>>(this.userService.viewAll(), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	@Operation(security = { @SecurityRequirement(name = "BearerJWT") })
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId) {
		return new ResponseEntity<UserDto>(this.userService.viewById(userId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{userId}")
	@Operation(security = { @SecurityRequirement(name = "BearerJWT") })
	public ResponseEntity<String> deleteUser(@PathVariable int userId) {
		UserDto userDto = this.userService.viewById(userId);
		this.userService.delete(userDto.getEmail());
		return new ResponseEntity<String>("User Deleted Successfully !! ", HttpStatus.OK);
	}

	@GetMapping("/me")
	// @PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserDto> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

		return new ResponseEntity<UserDto>(this.userService.viewById(userPrincipal.getId()), HttpStatus.OK);
	}

	@PostMapping("/profile/{userId}")
	public ResponseEntity<UserDto> changeProfile(@PathVariable int userId, @RequestBody MultipartFile file) {
		UserDto user = this.userService.viewById(userId);

		String fileName = this.fileUploadService.uploadImage(path, file);
		user.setImageUrl(fileName);

		return new ResponseEntity<UserDto>(this.userService.update(user, userId), HttpStatus.OK);
	}

}
