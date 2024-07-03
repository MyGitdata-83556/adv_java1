package com.sunbeam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostRespDTO extends BaseDTO{
	private String title;
	private String description;
	private String content;
}
