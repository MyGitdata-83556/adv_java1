package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.AuthRequest;
import com.sunbeam.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired //byType
	private UserService userService;

	public UserController() {
		System.out.println("in ctor " + getClass());
	}

	/*
	 * Desc - User signin 
	 * URL - http://host:port/users/signin 
	 * Method - POST 
	 * payload - dto (email n pwd) 
	 * Successful Resp - SC 200, user details - all (dto) 
	 * Error resp - SC 400 , error mesg -wrapped in DTO(ApiResponse)
	 * 
	 */
	@PostMapping("/signin") //@RequestMapping(method=POST)
	public ResponseEntity<?> signInUser(
			@RequestBody AuthRequest request) {
		//@RequestBody => Json -> Java (un marshalling | de ser)
		System.out.println("in signin " + request);
		try {
			return ResponseEntity.ok(
					userService.authenticateUser(request));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.
					status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}

}
