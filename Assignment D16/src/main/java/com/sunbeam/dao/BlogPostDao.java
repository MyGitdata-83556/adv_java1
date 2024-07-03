package com.sunbeam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.BlogPost;

public interface BlogPostDao extends JpaRepository<BlogPost, Long> {

}
