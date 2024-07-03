package com.sunbeam.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogPostReqDTO extends BaseDTO{
	private Long categoryId;
	private Long authorId;
	private String title;
	private String description;
	private String content;
}
