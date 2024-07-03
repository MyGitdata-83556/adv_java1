package com.sunbeam.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class BlogPost extends BaseEntity {
	@Column(length = 30, unique = true)
	private String title;
	private String description;
	private String content;
	// BlogPost *--->1 Category
	@ManyToOne
	//to customize FK col name n not null constraint
	@JoinColumn(name="category_id",nullable = false)
	private Category selectedCategory;
	//BlogPost *---->1 User(Blogger) : uni dir many->one association
	@ManyToOne(fetch=FetchType.LAZY )	
	@JoinColumn(name="author_id",nullable = false)
	private User blogger;

	public BlogPost() {
		// TODO Auto-generated constructor stub
	}

	public BlogPost(String title, String description, String content) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	

	public User getBlogger() {
		return blogger;
	}

	public void setBlogger(User blogger) {
		this.blogger = blogger;
	}
	
	

	@Override
	public String toString() {
		return "BlogPost ID " + getId() + " [title=" + title + ", description=" + description + ", content=" + content
				+ "]";
	}
	//override hashCode n equals as per the contract
	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogPost other = (BlogPost) obj;
		return Objects.equals(title, other.title);
	}

}
