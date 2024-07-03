package com.sunbeam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
