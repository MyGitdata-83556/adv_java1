package com.sunbeam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.CommentRequest;
import com.sunbeam.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	/*
	 * Desc - Post a comment
	 URL - http://host:port/comments
	 Method - POST
	 payload - Comment request DTO - comment text , rating , commenter id , blog post id
	 Successful Resp - SC 201 , Comment Resp DTO - comment id , text , rating , created on , updated on 
	 Error resp -SC 400  , ApiResponse with error message.
	 */
	@PostMapping
	public ResponseEntity<?> addComment(@RequestBody 
			CommentRequest request)
	{
		System.out.println("in add comment "+request);
		try {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(commentService.postNewComment(request));
		} catch (RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage()));
		}
	}

}
